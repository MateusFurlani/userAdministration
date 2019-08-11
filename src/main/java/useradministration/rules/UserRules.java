package useradministration.rules;

import java.text.ParseException;

import useradministration.model.User;
import useradministration.validation.UserValidators;

public class UserRules {
	
	/**
	 * regra que atualiza dados do usuario
	 * @param user
	 * @param newAttributesOfUser
	 * @throws ParseException 
	 */
	public void updateUser(User user, User newAttributesOfUser) throws ParseException {
		UserValidators validator = new UserValidators();
		user.setName(newAttributesOfUser.getName());
		validator.cpfValidator(newAttributesOfUser.getCpf());
		user.setCpf(newAttributesOfUser.getCpf());
		validator.dateValidator(newAttributesOfUser.getDateOfBirth());
		user.setDateOfBirth(newAttributesOfUser.getDateOfBirth());
	}

}
