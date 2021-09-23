package ua.upc.conferenceplanning.adaptors.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
import ua.upc.conferenceplanning.exception.NameException;
import ua.upc.conferenceplanning.exception.ViolatedRestrictionsException;
import ua.upc.conferenceplanning.persistence.entity.Conference;
import ua.upc.conferenceplanning.service.ConferenceService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/conferences")
public class ConferenceRestController {

    private final ConferenceService conferenceService;

    @PostMapping()
    Long addConference(@RequestBody @Valid ConferenceDto conferenceDto) {
        return conferenceService.addConference(conferenceDto);
    }

    @GetMapping()
    List<Conference> getAllConferences() {
        return conferenceService.findAllConferences();
    }

    @PutMapping("/{conference_id}")
    Conference updateConference(@PathVariable("conference_id") Long confId, @RequestBody @Valid ConferenceDto conf) {
        return conferenceService.updateConf(confId, conf);
    }

    @ExceptionHandler(ViolatedRestrictionsException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")
    void onRestrictionsViolated() {
    }

    @ExceptionHandler(NameException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "bad request")
    void onDuplicates() {
    }

}
