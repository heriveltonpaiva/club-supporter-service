package br.com.hpaiva.clubsupporterservice.campaign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(
        name = "campaign",
        url = "${feign.url.campaign}",
        fallbackFactory = CampaignClientFallbackFactory.class)
@Headers({"Content-Type: application/json", "Accept: application/json"})
public interface CampaignClient {

    @RequestLine("GET")
    List<CampaignDTO> findCampaignsByIdHeartTeam(@Param("idHeartTeam") final Long idHeartTeam);

}
