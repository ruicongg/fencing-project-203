package org.fencing.demo.tournament;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class TournamentServiceImpl implements TournamentService {

    private final TournamentRepository tournamentRepository;

    public TournamentServiceImpl(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    @Transactional
    public Tournament addTournament(Tournament tournament) {
        if (tournament == null) {
            throw new IllegalArgumentException("Tournament cannot be null");
        }
        return tournamentRepository.save(tournament);
    }

    @Override
    public List<Tournament> listTournaments() {
        return StreamSupport.stream(tournamentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Tournament getTournament(Long tournamentId) {
        if (tournamentId == null){
            throw new IllegalArgumentException("Tournament ID cannot be null");
        }
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new TournamentNotFoundException(tournamentId));
    }

    @Override
    @Transactional
    public Tournament updateTournament(Long tournamentId, Tournament newTournament) {
        if (tournamentId == null || newTournament == null) {
            throw new TournamentNotFoundException(tournamentId);
        }
        return tournamentRepository.findById(tournamentId).map(existingTournament -> {
            existingTournament.setName(newTournament.getName());
            existingTournament.setTournamentStartDate(newTournament.getTournamentStartDate());
            existingTournament.setTournamentEndDate(newTournament.getTournamentEndDate());
            existingTournament.setRegistrationStartDate(newTournament.getRegistrationStartDate());
            existingTournament.setRegistrationEndDate(newTournament.getRegistrationEndDate());
            existingTournament.setVenue(newTournament.getVenue());
            return tournamentRepository.save(existingTournament);
        }).orElseThrow(() -> new TournamentNotFoundException(tournamentId));
    }

    @Override
    @Transactional
    public void deleteTournament(Long tournamentId) {
        if (tournamentId == null) {
            throw new TournamentNotFoundException(tournamentId);
        }
        tournamentRepository.deleteById(tournamentId);
    }

    @Override
    public List<Tournament> findByAvailability(LocalDate startDate, LocalDate endDate){
        LocalDate currentDate = LocalDate.now();
        if (!startDate.isAfter(currentDate) || !endDate.isAfter(currentDate)){
            throw new IllegalArgumentException("Error: Date entered must be after today!");
        }

        if(startDate.isAfter(endDate)){
            throw new IllegalArgumentException("Error: start date is after end date");
        }

        return tournamentRepository.findByTournamentStartDateLessThanEqualAndTournamentEndDateGreaterThanEqual(endDate, startDate);
    }

}