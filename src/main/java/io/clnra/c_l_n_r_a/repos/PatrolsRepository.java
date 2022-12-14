package io.clnra.c_l_n_r_a.repos;

import io.clnra.c_l_n_r_a.domain.Patrols;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PatrolsRepository extends JpaRepository<Patrols, Long> {
}
