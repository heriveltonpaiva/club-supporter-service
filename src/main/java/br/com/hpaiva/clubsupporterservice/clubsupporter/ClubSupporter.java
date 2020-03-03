package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.team.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "club_supporter")
public class ClubSupporter {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    private boolean active;

}
