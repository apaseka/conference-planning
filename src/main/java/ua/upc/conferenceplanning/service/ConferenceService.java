package ua.upc.conferenceplanning.service;

import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
import ua.upc.conferenceplanning.adaptors.api.dto.TalkDto;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;

public interface ConferenceService {

    List<Conference> findAllConferences();

    Long addConference(ConferenceDto conferenceDto);

    Conference updateConf(Long id, ConferenceDto conf);

}
