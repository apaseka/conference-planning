package ua.upc.conferenceplanning.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.upc.conferenceplanning.adaptors.api.dto.ConferenceDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table
@AllArgsConstructor
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conference_id_seq")
    @SequenceGenerator(name = "conference_id_seq", sequenceName = "conference_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String subject;

    @Min(101)
    private Integer participantNumber;

    @NotNull
    private LocalDate date;

    @OneToMany
    @JoinTable(name = "planned_talks",
            joinColumns = {@JoinColumn(name = "conference_id")},
            inverseJoinColumns = {@JoinColumn(name = "talk_id")})
    private Set<Talk> talks;

    public Conference(ConferenceDto conferenceDto) {
        this.name = conferenceDto.getName();
        this.subject = conferenceDto.getSubject();
        this.participantNumber = conferenceDto.getParticipantNumber();
        this.date = conferenceDto.getDate();
    }

    public void updateConference(ConferenceDto newConf){
        this.name = newConf.getName();
        this.subject = newConf.getSubject();
        this.participantNumber = newConf.getParticipantNumber();
        this.date = newConf.getDate();
    }

}
