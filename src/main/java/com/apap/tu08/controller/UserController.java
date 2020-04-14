package com.apap.tu08.controller;

import com.apap.tu08.model.UserModel;
import com.apap.tu08.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    final String passwordRegEx = "^(?=.*[0-9]).{8, }$";

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/addUser")
    private String addUserSubmit(@ModelAttribute UserModel user){
        userService.addUser(user);
        return "home";
    }

    @GetMapping(value = "/updatePassword")
	private String shopUpdatePassword() {
		return "update-password";
	}

    @PostMapping(value="/updatePassword")
    private String updatePasswordSubmit(String newPassword,
                                        String confirmPassword,
                                        Model model, String oldPassword, String username) {
        UserModel user = userService.findUserByUsername(username);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), oldPassword)) {

            if (!newPassword.matches(passwordRegEx)){
                model.addAttribute("message", "password haru mengandung angka dan huruf serta minimal memiliki 8 karakter!");
                return "home";
            }
            if (newPassword.equals(confirmPassword)) {
                user.setPassword(newPassword);
                userService.addUser(user);
                model.addAttribute("message", "password has been updated!");
            } else {
                model.addAttribute("message", "you have "
                        + "inputted the wrong new password");
            }
        } else {
            model.addAttribute("message", "password lama salah");
        }
        return "home";
    }
}
