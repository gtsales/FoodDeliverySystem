package dev.luiz.user.system.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.luiz.user.system.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByCpf(String cpf);
}
