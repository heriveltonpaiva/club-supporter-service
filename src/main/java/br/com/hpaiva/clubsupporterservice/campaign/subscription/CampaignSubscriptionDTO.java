package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignSubscriptionDTO {

    private Long id;

    private CampaignDTO campaignDTO;

    private ClubSupporterDTO clubSupporterDTO;

}
