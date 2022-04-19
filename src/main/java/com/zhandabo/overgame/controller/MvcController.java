package com.zhandabo.overgame.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/overgame")
@RequiredArgsConstructor
public class MvcController {

    @GetMapping("/main")
    public String basePage() {
        return "main";
    }

    @GetMapping("/welcome")
    public String homePage() {
        return "welcome";
    }

    @GetMapping("/register-base")
    public String registerBasePage() {
        return "register_base";
    }

    @GetMapping("/user/logout")
    public String byePage() {
        return "logout";
    }

    @GetMapping("/user/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/user/change-password")
    public String change_passwordPage() {
        return "change_password";
    }

    @GetMapping("/user/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/user/success")
    public String successPage() {
        return "success";
    }

    @GetMapping("/user/edit-profile")
    public String editProfilePage() {
        return "edit_profile";
    }

    @GetMapping("/user/profile")
    public String profilePage() {
        return "profile";
    }

    @GetMapping("/user/game-create")
    public String gameCreatePage() {
        return "game_create";
    }

    @GetMapping("/user/game-play")
    public String gamePlayPage() {
        return "game_play";
    }
}
