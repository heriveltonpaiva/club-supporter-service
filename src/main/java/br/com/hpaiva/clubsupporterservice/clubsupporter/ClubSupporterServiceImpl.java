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
import javax.transaction.Transactional;
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
    @Transactional
    public List<CampaignDTO> createClubSupporter(final ClubSupporterRequest request) {

        final var dto = toDto(request);
        final var allRegister = findClubSupporterByEmail(dto.getEmail());
        final var clubSupporter = getActiveClubSupporter(allRegister);

        if (clubSupporter.isPresent()) {
            log.warn("Você já tem um cadastro ativo! sócio torcedor de email={}", dto.getEmail());

            if (findSubscriptionsByClubSupporter(clubSupporter.get().getId()).isEmpty()) {
                log.warn(("Não há campanhas associadas para o sócio torcedor de email: " + dto.getEmail()));
            }
            return subscription(dto, clubSupporter.get().getId());
        }

        final var newClubSupporter = createNewClubSupporter(dto);
        return subscription(dto, newClubSupporter.getId());
    }

    @Override
    public List<CampaignDTO> subscription(ClubSupporterDTO dto, Long clubSupporterId) {
        dto.setId(clubSupporterId);
        subscriptionClient.subscription(dto);
        return campaignClient.findCampaignsByIdHeartTeam(dto.getIdHeartTeam());
    }

    @Override
    public ClubSupporter createNewClubSupporter(final ClubSupporterDTO dto) {
        final var team = isTeamExist(dto.getIdHeartTeam());

        final var newClubSupporter = ClubSupporter.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .team(team)
                .active(Boolean.TRUE)
                .build();
        repository.saveAndFlush(newClubSupporter);

        log.info("Sócio torcedor cadastrado com sucesso.");
        log.info(newClubSupporter.toString());
        return newClubSupporter;
    }

    private Team isTeamExist(final Long idTeam) {
        return teamService.findById(idTeam).orElseThrow(() ->
                new EntityNotFoundException("Não há time cadastrado para o idHeartTeam informado."));
    }

    private List<ClubSupporter> findClubSupporterByEmail(final String email) {
        return repository.findByEmail(email);
    }

    private List<CampaignSubscriptionDTO> findSubscriptionsByClubSupporter(final Long idClubSupporter) {
        return subscriptionClient.findCampaignSubscriptionsByClubSupporter(idClubSupporter);
    }

    private Optional<ClubSupporter> getActiveClubSupporter(final List<ClubSupporter> clubSupporters) {
        return clubSupporters.stream().filter(x -> x.isActive()).findFirst();
    }

    private ClubSupporterDTO toDto(final ClubSupporterRequest req) {
        return ClubSupporterDTO.builder()
                .name(req.getName())
                .birthDate(req.getBirthDate())
                .email(req.getEmail())
                .idHeartTeam(req.getIdHeartTeam())
                .build();
    }

}
