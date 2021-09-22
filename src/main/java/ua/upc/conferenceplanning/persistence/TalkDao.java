package ua.upc.conferenceplanning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.upc.conferenceplanning.persistence.entity.Talk;

public interface TalkDao extends JpaRepository<Talk, Long> {
}
