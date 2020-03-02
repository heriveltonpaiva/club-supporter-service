package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.clubsupporterservice.team.Team;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignSubscriptionDTO {

    private CampaignDTO campaignDTO;

    private Team team;

    private ClubSupporter clubSupporter;

}
