package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    name = "campaign-subscription",
    url = "${feign.url.campaign-subscription}",
    fallbackFactory = CampaignSubscriptionClientFallbackFactory.class)
public interface CampaignSubscriptionClient {

    @RequestMapping(method = RequestMethod.GET)
    List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(@RequestParam("idClubSupporter") Long idClubSupporter);

}
