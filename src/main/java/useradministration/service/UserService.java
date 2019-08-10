package useradministration.service;

import java.util.List;

import useradministration.model.User;

public interface UserService {

	public void save(User user);
	
	public User searchByCpf(String cpf) throws Exception;
	
	public List<User> searchByName(String name) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
}
