package ua.upc.conferenceplanning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.upc.conferenceplanning.persistence.entity.Conference;

public interface ConferenceDao extends JpaRepository<Conference, Long> {
    Conference getByName(String name);
}
