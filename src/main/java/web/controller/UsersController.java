package web.controller;

import net.bytebuddy.matcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAOimt;
import web.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAOimt userDAOimt;

    public UsersController(UserDAOimt userDAOimt) {
        this.userDAOimt = userDAOimt;
    }

    @GetMapping()
    public String allUsers(Model model) {
        model.addAttribute("users", userDAOimt.allUsers());
        return "/views/list";
    }
    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "views/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user")@Valid User user
    ,BindingResult bindingResult){
       if (bindingResult.hasErrors()) {
           return "views/new";
       }
       userDAOimt.save(user);
       return "redirect:/users";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(){
        return "views/delete";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam Integer id) {
        userDAOimt.deleteUser(id);
        return "redirect:/users";
    }
    @GetMapping("/editUser")
    public String editUser(@RequestParam int id, Model model) {
        model.addAttribute("user", userDAOimt.getUser(id));
        return "views/edit";
    }
    @PostMapping("/edit")
    public String updateUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "views/edit";
        }
        userDAOimt.update(user, user.getId());
        return "redirect:/users";
    }
}
