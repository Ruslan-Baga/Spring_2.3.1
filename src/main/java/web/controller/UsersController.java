package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.service.UserService;
import web.model.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "/views/list";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "views/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user")User user) {
        userService.save(user);
       return "redirect:/users";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        return "views/delete";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam Integer id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/editUser")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "views/edit";
    }
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user, user.getId());
        return "redirect:/users";
    }
}
