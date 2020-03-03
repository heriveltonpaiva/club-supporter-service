package br.com.hpaiva.clubsupporterservice.clubsupporter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubSupporterRepository extends JpaRepository<ClubSupporter, Long> {

    List<ClubSupporter> findByEmail(final String email);

}
