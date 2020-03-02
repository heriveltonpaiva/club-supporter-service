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

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class ClubSupporterServiceImpl implements ClubSupporterService {

    private CampaignClient campaignClient;

    private CampaignSubscriptionClient campaignSubscriptionClient;

    private TeamService teamService;

    private ClubSupporterRepository repository;

    @Override
    public List<CampaignDTO> createClubSupporter(ClubSupporterRequest req) {

        final var allRegister = findClubSupporterByEmail(req.getEmail());
        final var clubSupporter = getActiveClubSupporter(allRegister);

        if(clubSupporter.isPresent()) {
            log.warn("Você já tem um cadastro ativo! sócio torcedor de email={}",req.getEmail());
        }else{

            final var team = teamService.findById(req.getIdHeartTeam());
            if(team.isEmpty()) {
                log.error("Não existe time do coração para o id informado idHeartTeam={}", req.getIdHeartTeam());
                return null;
            }
            final var newClubSupporter = ClubSupporter.builder()
                    .name(req.getName())
                    .email(req.getEmail())
                    .birthDate(req.getBirthDate())
                    .team(team.get())
                    .active(true)
                    .build();
            repository.save(newClubSupporter);
            log.info("Sócio torcedor cadastrado com sucesso.");
            //publish queue
            return null;
        }

        if(isNotAssociatedCampaigns(clubSupporter.get().getId())){
            log.warn("Não há campanhas associadas para o sócio torcedor de email: "+req.getEmail());

            //publish queue

           return findCampaignsByIdHeartTeam(clubSupporter.get().getTeam().getId());
        }
        return null;
    }

    @Override
    public List<ClubSupporter> findClubSupporterByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<CampaignDTO> findCampaignsByIdHeartTeam(Long idHeartTeam) {
        return campaignClient.findCampaignsByIdHeartTeam(idHeartTeam);
    }

    public List<CampaignSubscriptionDTO> findSubscriptionsByClubSupporter(final Long idClubSupporter){
        return campaignSubscriptionClient.findCampaignSubscriptionsByClubSupporter(idClubSupporter);
    }

    private Optional<ClubSupporter> getActiveClubSupporter(final List<ClubSupporter> clubSupporters) {
        return clubSupporters.stream().filter(x -> x.isActive()).findFirst();
    }

    private boolean isNotAssociatedCampaigns(final Long idClubSupporter){
        return !findSubscriptionsByClubSupporter(idClubSupporter).isEmpty();
    }
}
