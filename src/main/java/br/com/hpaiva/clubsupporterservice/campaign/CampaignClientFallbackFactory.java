package br.com.hpaiva.clubsupporterservice.campaign;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CampaignClientFallbackFactory implements FallbackFactory<CampaignClient> {

    @Override
    public CampaignClient create(Throwable cause) {
        return new CampaignClientFallback(cause);
    }
}
