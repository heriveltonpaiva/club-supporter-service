package br.com.hpaiva.clubsupporterservice.campaign;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CampaignClientFallbackFactory implements FallbackFactory<CampaignClient> {

    @Override
    public CampaignClient create(Throwable cause) {
        return new CampaignClientFallback(cause);
    }
}
