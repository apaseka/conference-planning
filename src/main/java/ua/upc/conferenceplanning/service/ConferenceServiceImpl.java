package ua.upc.conferenceplanning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.upc.conferenceplanning.persistence.ConferenceDao;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceDao conferenceDao;

    @Override
    public List<Conference> findAllConferences() {
        log.info("Finding all conferences");
        return conferenceDao.findAll();
    }
}
