package br.com.hpaiva.clubsupporterservice.campaign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
    name = "campaign",
    url = "${feign.url.campaign}",
    fallbackFactory = CampaignClientFallbackFactory.class)
public interface CampaignClient {

    @RequestMapping(method = RequestMethod.GET)
    List<CampaignDTO> findCampaignsByIdHeartTeam(@RequestParam("idHeartTeam")  final Long idHeartTeam);


}
