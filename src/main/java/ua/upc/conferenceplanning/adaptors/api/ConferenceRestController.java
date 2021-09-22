package ua.upc.conferenceplanning.adaptors.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.upc.conferenceplanning.persistence.entity.Conference;
import ua.upc.conferenceplanning.service.ConferenceService;

import java.util.List;

@RestController
@AllArgsConstructor
public class ConferenceRestController {

    private final ConferenceService conferenceService;

    @GetMapping(path = "/conferences", produces = MediaType.APPLICATION_JSON_VALUE)
    List<Conference> getAllConferences() {
        return conferenceService.findAllConferences();
    }
}
