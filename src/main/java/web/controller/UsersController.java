package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.servise.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showUsers(ModelMap model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @GetMapping({"/{id}"})
    public String showUserById(@PathVariable("id") int id, ModelMap model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping
     public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id){
        userService.updateUser(id, user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
