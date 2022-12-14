package io.clnra.c_l_n_r_a.repos;

import io.clnra.c_l_n_r_a.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
