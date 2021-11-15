package ua.upc.conferenceplanning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.upc.conferenceplanning.adaptors.api.dto.TalkDto;
import ua.upc.conferenceplanning.exception.DuplicateException;
import ua.upc.conferenceplanning.exception.ViolatedRestrictionsException;
import ua.upc.conferenceplanning.persistence.ConferenceDao;
import ua.upc.conferenceplanning.persistence.TalkDao;
import ua.upc.conferenceplanning.persistence.entity.Conference;
import ua.upc.conferenceplanning.persistence.entity.Talk;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TalksServiceImpl implements TalkService {

    private final TalkDao talkDao;
    private final ConferenceDao conferenceDao;

    @Override
    @Transactional
    public Long addTalk(Long confId, TalkDto talkDto) {

        Conference conference = conferenceDao.findById(confId)
                .orElseThrow(() -> new RuntimeException(String.format("no such conference id=%d", confId)));

        LocalDate conferenceDate = conference.getDate();

        if (LocalDate.now().plus(1L, ChronoUnit.MONTHS).isAfter(conferenceDate)) {
            String msg = String.format("Talk date is too late! Conference started %s", conferenceDate);
            LOG.error(msg);
            throw new ViolatedRestrictionsException(String.format("Talk date is too late! Conference started %s", conferenceDate));
        }

        if (!talkDao.findAllByName(talkDto.getName()).isEmpty()) {
            String msg = String.format("Talk name %s is not unique!", talkDto.getName());
            LOG.error(msg);
            throw new DuplicateException(msg);
        }

        if (talkDao.findAllByOrator(talkDto.getOrator()).size() > 3) {
            String msg = String.format("Talks limit for orator %s is being reached!", talkDto.getOrator());
            LOG.error(msg);
            throw new ViolatedRestrictionsException(msg);
        }

        Talk talk = new Talk(talkDto);

        if (conference.getTalks().isEmpty()) {
            conference.setTalks(Set.of(talk));
        } else {
            conference.getTalks().add(talk);
        }

        talkDao.save(talk);

        return talk.getId();
    }

    @Override
    public List<Talk> getTalks(Long confId) {
        return talkDao.findAllByConferenceId(confId);
    }

}
