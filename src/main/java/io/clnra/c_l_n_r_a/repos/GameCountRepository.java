package io.clnra.c_l_n_r_a.repos;

import io.clnra.c_l_n_r_a.domain.GameCount;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GameCountRepository extends JpaRepository<GameCount, Long> {
}
