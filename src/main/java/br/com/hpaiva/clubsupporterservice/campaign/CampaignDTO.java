package br.com.hpaiva.clubsupporterservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampaignDTO implements Serializable {

    private Long id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime startEffectiveDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDateTime endEffectiveDate;

    private Long idHeartTeam;

}
