package br.com.hpaiva.clubsupporterservice.clubsupporter;

import br.com.hpaiva.clubsupporterservice.campaign.CampaignDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/club-supporter")
@Api(value = "Sócios Torcedores")
public class ClubSupporterResource {

    private ClubSupporterService service;

    @PostMapping
    @ApiOperation(
            value = "Cadastra o sócio torcedor",
            notes = "Retorna as campanhas aderida pelo sócio torcedor.")
    public ResponseEntity<List<CampaignDTO>> save(@RequestBody ClubSupporterRequest request){
        return ResponseEntity.ok(service.createClubSupporter(request));
    }

}
