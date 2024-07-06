package org.example1.security.web;

import org.example1.security.model.User;
import org.example1.security.service.RoleService;
import org.example1.security.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("admin/")
public class MainController {

    private final UserService userService;
    private final RoleService roleService;

    public MainController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getIndex(ModelMap model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "index";
    }

    @PostMapping("add")
    public String postAdd(@ModelAttribute("newUser") User user) {
        userService.add(user);
        return "redirect:/admin/";
    }

    @PostMapping("delete")
    public String postDelete(@RequestParam("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @PostMapping("update")
    public String postEdit(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
}
