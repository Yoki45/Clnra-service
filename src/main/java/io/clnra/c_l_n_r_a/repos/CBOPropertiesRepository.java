package io.clnra.c_l_n_r_a.repos;

import io.clnra.c_l_n_r_a.domain.CBOProperties;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CBOPropertiesRepository extends JpaRepository<CBOProperties, Long> {

    boolean existsByRegistrationTagIgnoreCase(String registrationTag);

}
