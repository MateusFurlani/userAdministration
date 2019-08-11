package useradministration.service;

import java.util.List;

import useradministration.model.User;

public interface UserService {

	public void save(User user) throws  Exception;
	
	public User searchByCpf(String cpf) throws Exception;
	
	public List<User> searchByName(String name) throws Exception;
	
	public void deleteUser(Long id) throws Exception;
	
	public void modifyUserById(Long id, User user) throws Exception;
}
