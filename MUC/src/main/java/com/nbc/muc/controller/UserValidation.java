package com.nbc.muc.controller;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.nbc.muc.pojo.User;

@Component("userValidator")
public class UserValidation {
	public boolean supports(Class<?> klass) {
		return User.class.isAssignableFrom(klass);
	}

	public void validateAdd(Object target, Errors errors) {
		User registration = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.registration.userName", "用户名不能为空.");
		String userName = registration.getUserName();
		if ((userName.length()) > 50) {
			errors.rejectValue("userName",
					"lengthOfUser.registration.userName",
					"User Name must not more than 50 characters.");
		}
		if (!(registration.getPassword()).equals(registration
				.getConfirmPassword())) {
			errors.rejectValue("password",
					"matchingPassword.registration.password",
					"两次输入的密码不一致！");
		}
	}
	public void validateUpdate(Object target, Errors errors) {
		User registration = (User) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.registration.userName", "用户名不能为空.");
		String userName = registration.getUserName();
		if ((userName.length()) > 50) {
			errors.rejectValue("userName",
					"lengthOfUser.registration.userName",
					"User Name must not more than 50 characters.");
		}
	}
}
