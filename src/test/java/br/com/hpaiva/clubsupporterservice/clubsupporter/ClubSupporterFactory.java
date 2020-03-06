package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionDTO;
import br.com.hpaiva.clubsupporterservice.team.Team;
import org.hamcrest.Factory;

import java.time.LocalDate;

public class ClubSupporterFactory {

    public static final Long DEFAULT_ID = 1L;

    public static final Long DEFAULT_ID_TEAM = 1L;

    public static final String NAME = "HERIVELTON";

    public static final LocalDate BIRTH_DATE = LocalDate.of(1994, 03, 05);

    public static final String EMAIL = "hpaiva@teste.com";

    public static final LocalDate START_DATE = LocalDate.now();

    public static final LocalDate END_DATE = LocalDate.now().plusDays(20L);

    @Factory
    public static ClubSupporterRequest clubSupporterRequest() {
        return ClubSupporterRequest.builder().
                name(NAME).
                email(EMAIL).
                birthDate(BIRTH_DATE).
                idHeartTeam(DEFAULT_ID_TEAM).
                build();
    }

    @Factory
    public static ClubSupporterDTO clubSupporterDTO() {
        return ClubSupporterDTO.builder().
                name(NAME).
                email(EMAIL).
                birthDate(BIRTH_DATE).
                idHeartTeam(DEFAULT_ID_TEAM).
                active(Boolean.TRUE).
                build();
    }

    @Factory
    public static ClubSupporter clubSupporter() {
        return ClubSupporter.builder().
                name(NAME).
                email(EMAIL).
                birthDate(BIRTH_DATE).
                team(team()).
                active(Boolean.TRUE).
                build();
    }

    @Factory
    public static CampaignSubscriptionDTO campaignSubscriptionDTO() {
        return CampaignSubscriptionDTO.builder()
                .campaignDTO(campaignDTO())
                .clubSupporterDTO(clubSupporterDTO())
                .build();
    }

    @Factory
    public static CampaignDTO campaignDTO() {
        final var campaign = CampaignDTO.builder().
                id(DEFAULT_ID).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                idHeartTeam(DEFAULT_ID_TEAM).
                build();
        return campaign;
    }

    @Factory
    public static Team team() {
        return Team.builder().id(DEFAULT_ID_TEAM).name("Flamengo").build();
    }
}
