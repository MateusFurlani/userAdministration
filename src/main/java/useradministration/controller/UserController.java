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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import useradministration.model.User;
import useradministration.service.UserService;

@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;

	@PostMapping(path = "/usuario")
	public ResponseEntity<Object> save(@RequestBody User user) {
		try {
			this.userService.save(user);
		} catch (DataIntegrityViolationException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@GetMapping(path = "/usuario/{cpf}")
	public ResponseEntity<?> findByCpf(@PathVariable("cpf") String cpf) {
		User user;
		try {
			user = this.userService.searchByCpf(cpf);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	@GetMapping(path = "/usuario/{name}")
	public ResponseEntity<?> findByName(@PathVariable("name") String name) {
		List<User> users = null;
		try {
			users = this.userService.searchByName(name);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(users);
	}

	@DeleteMapping(path = "/usuario/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		try {
			this.userService.deleteUser(id);
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}
