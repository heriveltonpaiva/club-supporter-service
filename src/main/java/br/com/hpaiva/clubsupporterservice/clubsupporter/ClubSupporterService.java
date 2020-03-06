package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import javassist.NotFoundException;

import java.util.List;

public interface ClubSupporterService {

    List<CampaignDTO> createClubSupporter(final ClubSupporterRequest request) throws Exception;

    ClubSupporter createNewClubSupporter(final ClubSupporterDTO dto) throws NotFoundException;

    List<CampaignDTO> subscription(ClubSupporterDTO dto, Long clubSupporterId) throws Exception;

}
