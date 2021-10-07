package ua.upc.conferenceplanning.service;

import ua.upc.conferenceplanning.adaptors.api.dto.TalkDto;
import ua.upc.conferenceplanning.persistence.entity.Talk;

import java.util.List;

public interface TalkService {

    Long addTalk(Long confId, TalkDto talkDto);

    List<Talk> getTalks(Long confId);
}
