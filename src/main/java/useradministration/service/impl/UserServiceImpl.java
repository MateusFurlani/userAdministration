package useradministration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import useradministration.model.User;
import useradministration.repository.UserRepository;
import useradministration.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public User searchByCpf(String cpf) throws Exception {
		return userRepository.findUserByCpf(cpf).orElseThrow(() -> new Exception("Usuario não encontrado"));
	}

	@Override
	public List<User> searchByName(String name) throws Exception {
		return userRepository.findUserByName(name).orElseThrow(() -> new Exception("Usuario não encontrado"));
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
		userRepository.delete(user);
	}

}
