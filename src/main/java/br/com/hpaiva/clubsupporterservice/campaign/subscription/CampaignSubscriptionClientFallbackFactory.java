package br.com.hpaiva.clubsupporterservice.campaign.subscription;

import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CampaignSubscriptionClientFallbackFactory implements FallbackFactory<CampaignSubscriptionClient> {

    @Override
    public CampaignSubscriptionClient create(Throwable cause) {
        return new CampaignSubscriptionClientFallback(cause);
    }
}
