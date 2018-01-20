package br.biblioteca.livros.validator;

import br.biblioteca.livros.beans.User;
import br.biblioteca.livros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passsword", "NotEmpty");
		if (user.getPasssword().length() < 8 || user.getPasssword().length() > 32) {
			errors.rejectValue("passsword", "Size.userForm.passsword");
		}

		if (!user.getPasssword().equals(user.getPasswordConfirm())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
		}
		
	}
}