package br.com.hpaiva.clubsupporterservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

    private Long id;

    private String name;

    private LocalDate startEffectiveDate;

    private LocalDate endEffectiveDate;

    private Long idHeartTeam;

}
