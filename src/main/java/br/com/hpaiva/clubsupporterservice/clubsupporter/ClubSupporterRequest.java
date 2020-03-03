package br.com.hpaiva.clubsupporterservice.clubsupporter;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClubSupporterRequest {

    private String name;

    private String email;

    private LocalDate birthDate;

    private Long idHeartTeam;

}
