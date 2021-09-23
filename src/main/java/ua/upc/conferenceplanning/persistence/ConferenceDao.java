package ua.upc.conferenceplanning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.upc.conferenceplanning.persistence.entity.Conference;

import java.time.LocalDate;
import java.util.List;

public interface ConferenceDao extends JpaRepository<Conference, Long> {

    Conference getByName(String name);

    List<Conference> findAllByNameOrDate(String name, LocalDate date);
}
