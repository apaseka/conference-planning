package ua.upc.conferenceplanning.adaptors.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ua.upc.conferenceplanning.exception.ConferenceException;
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

    @PutMapping("/{conference_id}")
    Conference updateConference(@PathVariable Long confId, @RequestBody @Valid Conference conf){
        return conferenceService.updateConf(confId, conf);
    }

    @ExceptionHandler(ConferenceException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="bad request")
    void onError(){}


}
