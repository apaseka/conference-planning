package ua.upc.conferenceplanning.service;

import ua.upc.conferenceplanning.adaptors.api.ConferenceDto;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;

public interface ConferenceService {

    List<Conference> findAllConferences();

    Long addConference();

    Conference updateConf(Long id, ConferenceDto conf);
}
