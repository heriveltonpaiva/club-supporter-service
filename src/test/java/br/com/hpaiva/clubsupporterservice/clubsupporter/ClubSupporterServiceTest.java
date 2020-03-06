package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignClient;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionClient;
import br.com.hpaiva.clubsupporterservice.team.TeamService;
import javassist.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static br.com.hpaiva.clubsupporterservice.clubsupporter.ClubSupporterFactory.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClubSupporterServiceTest {

    @InjectMocks
    private ClubSupporterServiceImpl service;

    @Mock
    private ClubSupporterRepository repository;

    @Mock
    private TeamService teamService;

    @Mock
    private CampaignSubscriptionClient subscriptionClient;

    @Mock
    private CampaignClient campaignClient;

    @Test
    public void shouldCreateNewClubSupporter() throws NotFoundException {
        when(teamService.findById(DEFAULT_ID_TEAM)).thenReturn(Optional.of(team()));
        service.createNewClubSupporter(clubSupporterDTO());

        verify(repository, times(1)).saveAndFlush(clubSupporter());
    }

    @Test
    public void shouldNotSaveNewClubSupporterWhenExist() throws Exception {

        final var clubSupporter = clubSupporter();
        clubSupporter.setId(DEFAULT_ID);
        final var clubSupporterDTO = clubSupporterDTO();
        clubSupporterDTO.setId(DEFAULT_ID);

        when(repository.findByEmail(EMAIL)).thenReturn(Collections.singletonList(clubSupporter));
        when(subscriptionClient.findCampaignSubscriptionsByClubSupporter(DEFAULT_ID)).
                thenReturn(Collections.singletonList(campaignSubscriptionDTO()));
        when(subscriptionClient.subscription(clubSupporterDTO)).thenReturn(any());
        when(campaignClient.findCampaignsByIdHeartTeam(DEFAULT_ID_TEAM)).thenReturn(Collections.singletonList(campaignDTO()));

        service.createClubSupporter(clubSupporterRequest());

        verify(repository, times(0)).saveAndFlush(clubSupporter());

    }

    @Test
    public void shouldSaveNewClubSupporterWhenNotExistActive() throws Exception {

        final var clubSupporter = clubSupporter();
        clubSupporter.setActive(false);
        final var clubSupporterDTO = clubSupporterDTO();
        clubSupporter.setActive(false);

        when(repository.findByEmail(EMAIL)).thenReturn(Collections.singletonList(clubSupporter));
        when(campaignClient.findCampaignsByIdHeartTeam(DEFAULT_ID_TEAM)).thenReturn(Collections.singletonList(campaignDTO()));
        when(subscriptionClient.subscription(clubSupporterDTO)).thenReturn(any());
        when(teamService.findById(DEFAULT_ID_TEAM)).thenReturn(Optional.of(team()));

        service.createClubSupporter(clubSupporterRequest());

        verify(repository, times(1)).saveAndFlush(clubSupporter());

    }


    @Test
    public void shouldThrowExceptionWhenTrySaveNewClubSupporterWhenTeamNotExist() {
        final var clubSupporter = clubSupporter();
        clubSupporter.setId(DEFAULT_ID);
        clubSupporter.setActive(false);

        when(repository.findByEmail(EMAIL)).thenReturn(Collections.singletonList(clubSupporter));

        Throwable thrown = assertThrows(NotFoundException.class, () -> service.createClubSupporter(clubSupporterRequest()));
        assertThat(thrown.getMessage(), Matchers.is("Não há time cadastrado para o idHeartTeam informado."));

    }

}
