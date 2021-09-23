package ua.upc.conferenceplanning.adaptors.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        return conferenceService.addConference();
    }

    @GetMapping()
    List<Conference> getAllConferences() {
        return conferenceService.findAllConferences();
    }
}
