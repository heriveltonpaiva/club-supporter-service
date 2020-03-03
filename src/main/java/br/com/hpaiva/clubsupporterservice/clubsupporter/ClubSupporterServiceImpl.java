package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignClient;
import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionClient;
import br.com.hpaiva.clubsupporterservice.campaign.subscription.CampaignSubscriptionDTO;
import br.com.hpaiva.clubsupporterservice.team.Team;
import br.com.hpaiva.clubsupporterservice.team.TeamService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ClubSupporterServiceImpl implements ClubSupporterService {

    private CampaignClient campaignClient;

    private CampaignSubscriptionClient subscriptionClient;

    private TeamService teamService;

    private ClubSupporterRepository repository;

    @Override
    public List<CampaignDTO> createClubSupporter(ClubSupporterDTO dto) {

        final var allRegister = findClubSupporterByEmail(dto.getEmail());
        final var clubSupporter = getActiveClubSupporter(allRegister);

        //para torcedores já cadastrados
        if (clubSupporter.isPresent()) {
            log.warn("Você já tem um cadastro ativo! sócio torcedor de email={}", dto.getEmail());

            if(findSubscriptionsByClubSupporter(clubSupporter.get().getId()).isEmpty()){
                log.warn(("Não há campanhas associadas para o sócio torcedor de email: " + dto.getEmail()));

                var campaigns = campaignClient.findCampaignsByIdHeartTeam(dto.getIdHeartTeam());

                return campaigns;
            }
        }

        //cadastramento do sócio torcedor
        final var team = isTeamExist(dto.getIdHeartTeam());
        final var newClubSupporter = createNewClubSupporter(dto, team);
        log.info(newClubSupporter.toString());

        //associar sócio as campanhas do time do coração
        var campaigns = campaignClient.findCampaignsByIdHeartTeam(dto.getIdHeartTeam());

        return null;
}

    private Team isTeamExist(Long idTeam) {
        return teamService.findById(idTeam).orElseThrow(() -> new EntityNotFoundException("Não há time cadastrado para o idHeartTeam informado."));
    }

    private ClubSupporter createNewClubSupporter(ClubSupporterDTO dto, Team team) {

        final var newClubSupporter = ClubSupporter.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .team(team)
                .active(true)
                .build();
        repository.save(newClubSupporter);

        log.info("Sócio torcedor cadastrado com sucesso.");

        return newClubSupporter;
    }

    @Override
    public List<ClubSupporter> findClubSupporterByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<CampaignSubscriptionDTO> findSubscriptionsByClubSupporter(final Long idClubSupporter) {
        return subscriptionClient.findCampaignSubscriptionsByClubSupporter(idClubSupporter);
    }

    private Optional<ClubSupporter> getActiveClubSupporter(final List<ClubSupporter> clubSupporters) {
        return clubSupporters.stream().filter(x -> x.isActive()).findFirst();
    }

}
