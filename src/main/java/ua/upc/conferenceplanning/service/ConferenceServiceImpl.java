package ua.upc.conferenceplanning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.upc.conferenceplanning.exception.ConferenceException;
import ua.upc.conferenceplanning.persistence.ConferenceDao;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Long addConference() {
        return null;
    }

    @Override
    public Conference editConference(Long id, Conference conf) {
        Optional<Conference> oldConf = conferenceDao.findById(id);

        if(!oldConf.isPresent()){
            throw new ConferenceException("conference is not found");
        }



        return null;
    }
}
