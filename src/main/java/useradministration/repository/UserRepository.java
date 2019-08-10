package useradministration.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import useradministration.model.User;

@Repository
public interface UserRepository extends JpaRepository <User, Long>{

	Optional<User> findUserByCpf(String cpf);
	Optional<List<User>> findUserByName(String name);
}
