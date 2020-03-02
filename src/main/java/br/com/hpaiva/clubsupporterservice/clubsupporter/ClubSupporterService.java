package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionDTO;

import java.util.List;

public interface ClubSupporterService {

    List<ClubSupporter> findClubSupporterByEmail(final String email);

    List<CampaignDTO> createClubSupporter(final ClubSupporterDTO clubSupporterDTO);

    List<CampaignSubscriptionDTO> findSubscriptionsByClubSupporter(final Long idClubSupporter);

}
