package useradministration.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import useradministration.model.User;
import useradministration.service.UserService;

@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	/**
	 * Chamada do post para inserir um usuario
	 * @param user
	 * @return
	 */
	@PostMapping(path = "/usuario")
	public ResponseEntity<Object> save(@RequestBody User user) {
		try {
			this.userService.save(user);
		} catch (DataIntegrityViolationException e) {
			logger.error("Cpf já está sendo usado");
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		logger.info("retorna 201 - creado com sucesso");
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	/**
	 * Chamada do get para buscar usuario pelo cpf
	 * @param cpf
	 * @return usuario
	 */
	@GetMapping(path = "/usuario/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf) {
		User user;
		try {
			user = this.userService.searchByCpf(cpf);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		logger.info("retorna 200 - usuario encontrado");
		return ResponseEntity.ok(user);
	}
	
	/**
	 * Chamada do get para buscar usuario pelo nome
	 * @param name
	 * @return lista de usuarios
	 */
	@GetMapping(path = "/usuario/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		List<User> users = null;
		try {
			users = this.userService.searchByName(name);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		logger.info("retorna 200 - usuario encontrado");
		return ResponseEntity.ok(users);
	}

	/**
	 * Chamada do delete para excluir usuario
	 * @param id
	 * @return 
	 */
	@DeleteMapping(path = "/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			this.userService.deleteUser(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		logger.info("retorna 204 - usuario excluido");
		return ResponseEntity.noContent().build();
	}
	
	/**
	 * Chamada do put para alterar usuario informado pelo id
	 * @param id
	 * @param user
	 * @return
	 */
	@PutMapping(path = "/usuario/{id}")
	public ResponseEntity<?> modifyUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			this.userService.modifyUserById(id, user);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		logger.info("retorna 204 - usuario alterado");
		return ResponseEntity.noContent().build();
	}
}
