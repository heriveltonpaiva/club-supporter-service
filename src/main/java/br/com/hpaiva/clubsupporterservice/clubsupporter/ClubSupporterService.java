package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;

import java.util.List;

public interface ClubSupporterService {

    List<CampaignDTO> createClubSupporter(final ClubSupporterRequest request);

    ClubSupporter createNewClubSupporter(final ClubSupporterDTO dto);

    List<CampaignDTO> subscription(ClubSupporterDTO dto, Long clubSupporterId);

}
