package br.com.hpaiva.clubsupporterservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
public class CampaignClientFallback implements CampaignClient{

    private final Throwable error;

    @Override
    public List<CampaignDTO> findCampaignsByIdHeartTeam(final Long idHeartTeam) {
        log.error("m=findCampaignsByIdHeartTeam error={}"+getError());
        return null;
    }
}
