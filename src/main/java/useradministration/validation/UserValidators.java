package useradministration.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.caelum.stella.validation.CPFValidator;
import useradministration.controller.UserController;

public class UserValidators {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * usa CPFValidator para relizar a validação do padrão do cpf, sendo aceito
	 * apenas no padrão 123.456.789-10
	 * 
	 * @param userCpf
	 */
	public void cpfValidator(String userCpf) {
		logger.info("inicia validação do cpf");
		CPFValidator validator = new CPFValidator();
		validator.assertValid(userCpf);
		logger.info("cpf validado");
	}

	/**
	 * usa o SimpleDateFormat para realizar a validação de data, sendo aceito apenas
	 * o padrão dd/MM/yyyy
	 * 
	 * @param userDateOfBirth
	 * @throws ParseException
	 */
	public void dateValidator(String userDateOfBirth) throws ParseException {
		logger.info("inicia validação da data");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		sdf.parse(userDateOfBirth);
		logger.info("data validada");
	}
}
