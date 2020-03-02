package br.com.hpaiva.clubsupporterservice.clubsupporter;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClubSupporterDTO implements Serializable {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private Long idHeartTeam;

    private boolean active;

}
