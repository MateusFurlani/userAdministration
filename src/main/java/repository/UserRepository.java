package repository;

import org.springframework.stereotype.Repository;

import model.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

	Optional<User> findUserByCpf(String cpf);
	Optional<User> findUserByName(String name);
}
