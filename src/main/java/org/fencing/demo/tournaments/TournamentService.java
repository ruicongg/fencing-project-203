package org.fencing.demo.tournaments;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface TournamentService {

    Tournament updateTournament(Tournament tournament);

    List<Tournament> listTournaments();

    Optional<Tournament> getTournament(Long id);

    // ! might want to remove
    boolean doesTournamentExist(Long id);

    Tournament updateTournament(Long id, Tournament tournament);

    void deleteTournament(Long id);

    List<Tournament> findByDateTournament(LocalDate date);
}