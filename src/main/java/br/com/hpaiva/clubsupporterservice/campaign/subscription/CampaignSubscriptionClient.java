package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporterDTO;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(
    name = "campaign-subscription",
    url = "${feign.url.campaign-subscription}",
    fallbackFactory = CampaignSubscriptionClientFallbackFactory.class)
public interface CampaignSubscriptionClient {

    @RequestMapping(method = RequestMethod.GET)
    List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(@RequestParam("idClubSupporter") Long idClubSupporter);

    @RequestMapping(method = RequestMethod.POST)
    Optional<Void> subscription(ClubSupporterDTO clubSupporterDTO);

}
