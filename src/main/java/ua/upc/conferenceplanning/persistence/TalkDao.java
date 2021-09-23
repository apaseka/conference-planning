package ua.upc.conferenceplanning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.upc.conferenceplanning.persistence.entity.Talk;

import java.util.List;

public interface TalkDao extends JpaRepository<Talk, Long> {

    List<Talk> findAllByName(String name);

    List<Talk> findAllByOrator(String orator);
}
