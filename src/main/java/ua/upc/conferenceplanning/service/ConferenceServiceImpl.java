package ua.upc.conferenceplanning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
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
    public Long addConference(ConferenceDto conferenceDto) {
        Conference conference;
        List<Conference> duplicates = conferenceDao.findAllByNameOrDate(conferenceDto.getName(), conferenceDto.getDate());
        if (duplicates.isEmpty()) {
            conference = conferenceDao.save(new Conference(conferenceDto));
            log.info("Conference saved in db, id: {}, name: {}", conference.getId(), conference.getName());
            return conference.getId();
        } else {
            return null;
        }

    }

    @Override
    public Conference updateConf(Long id, ConferenceDto conf) {
        Optional<Conference> oldConf = conferenceDao.findById(id);

        if (!oldConf.isPresent()) {
            throw new ConferenceException("conference is not found");
        }

        checkDuplicateConf(oldConf.get(), conf);

        oldConf.get().updateConference(conf);

        return conferenceDao.save(oldConf.get());
    }

    private void checkDuplicateConf(Conference oldConf, ConferenceDto newConf) {
        if (!oldConf.getName().equals(newConf.getName())) {
            Conference conf = conferenceDao.getByName(newConf.getName());
            if (Objects.nonNull(conf)) {
                throw new ConferenceException("conference with same name is already in storage");
            }
        }
    }

}
