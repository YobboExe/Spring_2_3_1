package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/table")
    public String tableOfUsers(Model model) {
        User user = new User();
        user.setId(4);
        user.setName("joe");
        user.setAge(4);
        userService.save(user);
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "all-users";
    }

    @GetMapping("/new")
    public String newUser (Model model) {
        model.addAttribute("user", new User());
        return "new-user";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/all-users";
    }

    @GetMapping("/{id}/update")
    public String updateUser(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.fineOne(id));
        return "update-user";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "all-users";
    }



    @DeleteMapping("/{id}")
    public String removeUser(@RequestParam(name = "id") int id, Model model) {
        userService.delete(id);

        return "remove-user";
    }
}
