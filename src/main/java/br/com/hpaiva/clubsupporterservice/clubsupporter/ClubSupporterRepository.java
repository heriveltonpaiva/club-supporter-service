package br.com.hpaiva.clubsupporterservice.clubsupporter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubSupporterRepository extends CrudRepository<ClubSupporter, Long> {

    List<ClubSupporter> findByEmail(final String email);

}
