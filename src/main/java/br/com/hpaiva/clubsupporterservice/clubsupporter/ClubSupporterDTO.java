package br.com.hpaiva.clubsupporterservice.clubsupporter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClubSupporterDTO {

    @JsonIgnore
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private Long idHeartTeam;

    @JsonIgnore
    private Boolean active;

}
