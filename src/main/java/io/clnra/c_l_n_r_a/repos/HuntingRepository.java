package io.clnra.c_l_n_r_a.repos;

import io.clnra.c_l_n_r_a.domain.Hunting;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HuntingRepository extends JpaRepository<Hunting, Long> {

    boolean existsByHuntingLog(UUID huntingLog);

}
