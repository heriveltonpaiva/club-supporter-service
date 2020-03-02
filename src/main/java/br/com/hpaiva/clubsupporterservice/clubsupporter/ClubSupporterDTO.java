package br.com.hpaiva.clubsupporterservice.clubsupporter;

import lombok.*;

import javax.persistence.Entity;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClubSupporterDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private Long idHeartTeam;

    private boolean active;

}
