package ua.upc.conferenceplanning.adaptors.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceDto {

    @NotBlank
    private String name;

    @Min(101)
    private Integer participantNumber;

    @NotBlank
    private String subject;

    @NotNull
    private LocalDate date;
}
