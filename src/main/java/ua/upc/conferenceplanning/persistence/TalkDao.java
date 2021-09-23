package ua.upc.conferenceplanning.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.upc.conferenceplanning.persistence.entity.Talk;

import java.util.List;

public interface TalkDao extends JpaRepository<Talk, Long> {

    List<Talk> findAllByName(String name);

    List<Talk> findAllByOrator(String orator);

    @Query(value = "SELECT * FROM talk t inner join planned_talks p on t.id=p.talk_id where p.conference_id=:id",
            nativeQuery = true)
    List<Talk> findAllByConferenceId(@Param("id") Long id);
}
