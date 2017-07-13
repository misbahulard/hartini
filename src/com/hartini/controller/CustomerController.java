package com.hartini.controller;

import com.hartini.entity.User;
import com.hartini.service.UserService;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by misbahul on 20/06/17.
 */
@Controller
@SessionAttributes({
        "userData",
        "cart"
})
public class CustomerController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(Model model, HttpSession httpSession) {
        User userSession = (User) httpSession.getAttribute("userData");
        if (userSession != null)
            return "redirect:/";
        User user = new User();
        model.addAttribute("user", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String authUser(@Valid User user, BindingResult bindingResult, Map model) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        User userDB = userService.fetchUserByUsername(user.getUsername());

        if (passwordEncryptor.checkPassword(user.getPassword(), userDB.getPassword())) {
            model.put("userData", userDB);
            return "redirect:/";
        } else {
            model.put("error", "Invalid username or password");
            return "login";
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String showSignupPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup(@Valid User user, BindingResult bindingResult, Map model) {
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        userService.addUser(user);
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus status) {
        status.setComplete();
        return "redirect:/";
    }
}
