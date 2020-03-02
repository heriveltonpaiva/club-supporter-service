package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignClient;
import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class CampaignSubscriptionClientFallback implements CampaignSubscriptionClient{

    private final Throwable error;

    @Override
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(final Long idClubSupporter) {
        log.error("m=findCampaignSubscriptionsByClubSupporter error={}"+getError());
        return null;
    }
}
