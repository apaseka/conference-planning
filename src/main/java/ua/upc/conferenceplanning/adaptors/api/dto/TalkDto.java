package ua.upc.conferenceplanning.adaptors.api.dto;

import lombok.Getter;
import lombok.Setter;
import ua.upc.conferenceplanning.domain.TalkType;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class TalkDto {

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
