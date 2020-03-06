package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporterDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@AllArgsConstructor
public class CampaignSubscriptionClientFallback implements CampaignSubscriptionClient{

    private final Throwable error;

    @Override
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter) {
        log.error("m=findCampaignSubscriptionsByClubSupporter error={}"+getError());
        return Collections.emptyList();
    }

    @Override
    public Optional<Void> subscription(ClubSupporterDTO clubSupporterDTO) {
        log.error("m=subscription error={}"+getError().getMessage());
        return Optional.empty();
    }
}
