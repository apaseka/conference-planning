package ua.upc.conferenceplanning.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ua.upc.conferenceplanning.domain.TalkType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "talk_id_seq")
    @SequenceGenerator(name = "talk_id_seq", sequenceName = "talk_id_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotBlank
    private String orator;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TalkType type;

}
