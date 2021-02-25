package com.example.crudapp.controller;


import com.example.crudapp.entity.User;
import com.example.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Validated User userForm,
                          BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {    //TODO
            return "registration";
        }

        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            model.addAttribute("passwordError", "Пароли не совпадают");
            return "registration";
        }

        if (userService.saveUser(userForm)) {
            model.addAttribute("usernameError", "Пользователь с таким email уже существует");
            return "registration";
        }

        return "redirect:/";
    }

}
