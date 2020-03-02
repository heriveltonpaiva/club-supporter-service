package br.com.hpaiva.clubsupporterservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO {

    private String name;

    private LocalDateTime startEffectiveDate;

    private LocalDateTime endEffectiveDate;

    private Long idHeartTeam;

}
