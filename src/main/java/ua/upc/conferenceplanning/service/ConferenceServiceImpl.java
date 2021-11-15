package ua.upc.conferenceplanning.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
import ua.upc.conferenceplanning.exception.DuplicateException;
import ua.upc.conferenceplanning.exception.ViolatedRestrictionsException;
import ua.upc.conferenceplanning.persistence.ConferenceDao;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
@Endpoint(id = "conferences")
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceDao conferenceDao;

    @ReadOperation
    @Override
    public List<Conference> findAllConferences() {
        LOG.info("Getting all conferences info");
        LOG.debug("Getting all conferences debug");
        return conferenceDao.findAll();
    }

    @Override
    public Long addConference(ConferenceDto conferenceDto) {

        List<Conference> duplicates = conferenceDao.findAllByNameOrDate(conferenceDto.getName(), conferenceDto.getDate());

        if (!duplicates.isEmpty()) {
            if (Objects.equals(conferenceDto.getDate(), duplicates.get(0).getDate())) {
                String msg = String.format("Conference date %s is not unique!", conferenceDto.getDate().toString());
                LOG.error(msg);
                throw new ViolatedRestrictionsException(msg);
            } else if (Objects.equals(conferenceDto.getName(), duplicates.get(0).getName())) {
                String msg = String.format("Conference name %s is not unique!", conferenceDto.getName());
                LOG.error(msg);
                throw new DuplicateException(msg);
            }
        }

        Conference conference = conferenceDao.save(new Conference(conferenceDto));
        LOG.info("Conference saved in db, id: {}, name: {}", conference.getId(), conference.getName());
        return conference.getId();
    }

    @Override
    public Conference updateConf(Long id, ConferenceDto conf) {
        Optional<Conference> oldConf = conferenceDao.findById(id);

        if (oldConf.isEmpty()) {
            throw new ViolatedRestrictionsException("conference is not found");
        }

        checkDuplicateConf(oldConf.get(), conf);

        oldConf.get().updateConference(conf);

        return conferenceDao.save(oldConf.get());
    }

    private void checkDuplicateConf(Conference oldConf, ConferenceDto newConf) {
        if (!oldConf.getName().equals(newConf.getName())) {
            Conference conf = conferenceDao.getByName(newConf.getName());
            if (Objects.nonNull(conf)) {
                throw new ViolatedRestrictionsException("conference with same name is already in storage");
            }
        }
    }

}
