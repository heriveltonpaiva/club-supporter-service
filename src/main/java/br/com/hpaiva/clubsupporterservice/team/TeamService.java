package br.com.hpaiva.clubsupporterservice.team;

import java.util.Optional;

public interface TeamService {

    Optional<Team> findById(Long id);
}
