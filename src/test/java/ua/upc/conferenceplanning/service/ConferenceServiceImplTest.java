package ua.upc.conferenceplanning.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;
import ua.upc.conferenceplanning.exception.DuplicateException;
import ua.upc.conferenceplanning.exception.ViolatedRestrictionsException;
import ua.upc.conferenceplanning.persistence.ConferenceDao;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ConferenceServiceImplTest {

    private ConferenceService service;

    private Conference conference = new Conference("name", "subject", 123, LocalDate.of(2021, 11, 30));

    @Mock
    private ConferenceDao conferenceDao;

    @Before
    public void init() {
        service = new ConferenceServiceImpl(conferenceDao);
    }

    @Test
    public void findAllConferences() {
        when(conferenceDao.findAll()).thenReturn(Collections.singletonList(conference));

        List<Conference> conferences = service.findAllConferences();

        verify(conferenceDao, only()).findAll();
        assertFalse(conferences.isEmpty());
    }

    @Test
    public void addConferenceSuccess() {
        ConferenceDto conferenceDto = new ConferenceDto("name", 132, "subject", LocalDate.of(2021, 11, 30));
        Conference conferenceNew = new Conference(conferenceDto);

        when(conferenceDao.findAllByNameOrDate(this.conference.getName(), this.conference.getDate()))
                .thenReturn(Collections.emptyList());
        conference.setId(1L);
        when(conferenceDao.save(conferenceNew)).thenReturn(conference);

        Long id = service.addConference(conferenceDto);

        assertEquals(1L, (long) id);
    }

    @Test(expected = ViolatedRestrictionsException.class)
    public void addConferenceDateNotUnique() {
        ConferenceDto conferenceDto = new ConferenceDto("name", 132, "subject", LocalDate.of(2021, 11, 30));
        Conference conferenceNew = new Conference(conferenceDto);

        when(conferenceDao.findAllByNameOrDate(conferenceDto.getName(), conferenceDto.getDate()))
                .thenReturn(Collections.singletonList(conferenceNew));

        service.addConference(conferenceDto);

    }

    @Test(expected = DuplicateException.class)
    public void addConferenceNameNotUnique() {
        ConferenceDto conferenceDto = new ConferenceDto("name", 132, "subject", LocalDate.of(2021, 11, 30));

        when(conferenceDao.findAllByNameOrDate(conferenceDto.getName(), conferenceDto.getDate()))
                .thenReturn(Collections.singletonList(new Conference(
                        "name",
                        "subject",
                        123, LocalDate.of(2021, 12, 30))
                ))
        ;

        service.addConference(conferenceDto);

    }

    @Test
    public void updateConf() {

    }
}