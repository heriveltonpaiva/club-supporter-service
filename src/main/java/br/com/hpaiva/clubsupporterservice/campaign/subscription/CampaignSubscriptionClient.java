package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporterDTO;
import br.com.hpaiva.clubsupporterservice.feign.FeignConfig;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Optional;

@FeignClient(
        name = "campaign-subscription",
        url = "${feign.url.campaign-subscription}",
        fallbackFactory = CampaignSubscriptionClientFallbackFactory.class)
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface CampaignSubscriptionClient {

    @RequestLine("GET /campaign-subscriptions/club-supporter/{idClubSupporter}")
    List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(@Param("idClubSupporter") Long idClubSupporter);

    @RequestLine("POST")
    Optional<Void> subscription(ClubSupporterDTO clubSupporterDTO);

}
