package org.fencing.demo.stages;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class KnockoutStageController {
    private final KnockoutStageService knockoutStageService;

    public KnockoutStageController(KnockoutStageService knockoutStageService) {
        this.knockoutStageService = knockoutStageService;
    }

    @PostMapping("/tournaments/{tournamentId}/events/{eventId}/knockoutStage")
    @ResponseStatus(HttpStatus.CREATED)
    public KnockoutStage addKnockoutStage(@PathVariable Long eventId, @RequestBody KnockoutStage knockoutStage) {
        return knockoutStageService.addKnockoutStage(eventId, knockoutStage);
    }

    @GetMapping("/tournaments/{tournamentId}/events/{eventId}/knockoutStage")
    @ResponseStatus(HttpStatus.OK)
    public KnockoutStage getKnockoutStage(@PathVariable Long knockoutStageId) {
        return knockoutStageService.getKnockoutStage(knockoutStageId);
    }

    @PutMapping("/tournaments/{tournamentId}/events/{eventId}/KnockoutStage")
    @ResponseStatus(HttpStatus.OK)
    public KnockoutStage updateKnockoutStage(@PathVariable Long eventId, @PathVariable Long knockoutStageId, @RequestBody KnockoutStage knockoutStage) {
        return knockoutStageService.updateKnockoutStage(eventId, knockoutStageId, knockoutStage);
    }

    @DeleteMapping("/tournaments/{tournamentId}/events/{eventId}/KnockoutStage")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteKnockoutStage(@PathVariable Long eventId, @PathVariable Long knockoutStageId) {
        knockoutStageService.deleteKnockoutStage(eventId, knockoutStageId);
    }
}