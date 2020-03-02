package br.com.hpaiva.clubsupporterservice.team;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TeamServiceImpl implements TeamService{

    private TeamRepository repository;

    @Override
    public Optional<Team> findById(Long id) {
        return repository.findById(id);
    }
}
