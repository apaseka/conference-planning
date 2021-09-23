package ua.upc.conferenceplanning.service;

import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
import ua.upc.conferenceplanning.adaptors.api.dto.TalkDto;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;

public interface TalkService {

    Long addTalk(Long confId, TalkDto talkDto);

    List<TalkDto> getTalks(Long confId);
}
