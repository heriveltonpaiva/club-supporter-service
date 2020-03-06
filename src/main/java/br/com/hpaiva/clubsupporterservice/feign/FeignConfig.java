package br.com.hpaiva.clubsupporterservice.feign;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignClientFallbackFactory;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionClientFallbackFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import feign.Contract;
import feign.Response;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Optional;


@Configuration
public class FeignConfig {

    private final ObjectMapper mapper =
            new ObjectMapper()
                    .registerModules(new Jdk8Module(), new JavaTimeModule())
                    .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    private final JacksonDecoder jacksonDecoder = new JacksonDecoder(mapper);
    private final JacksonEncoder jacksonEncoder = new JacksonEncoder(mapper);

    @Bean
    public Contract contract() {
        return new Contract.Default();
    }

    @Bean
    public Decoder decoder() {
        return new ClientDecoder();
    }

    @Bean
    public Encoder encoder() {
        return jacksonEncoder;
    }

    @Bean
    public CampaignSubscriptionClientFallbackFactory campaignSubscriptionClientFallbackFactory() {
        return new CampaignSubscriptionClientFallbackFactory();
    }

    @Bean
    public CampaignClientFallbackFactory campaignClientFallbackFactory() {
        return new CampaignClientFallbackFactory();
    }

    class ClientDecoder extends JacksonDecoder {

        private static final String OPTIONAL_VOID_NAME = "java.util.Optional<java.lang.Void>";

        @Override
        public Object decode(Response response, Type type) throws IOException {
            final boolean isOptionalVoidType = type.getTypeName().equals(OPTIONAL_VOID_NAME);
            final boolean successfulStatus = HttpStatus.valueOf(response.status()).is2xxSuccessful();
            if (successfulStatus && isOptionalVoidType) {
                return Optional.of(response.body());
            }
            return jacksonDecoder.decode(response, type);
        }
    }
}
