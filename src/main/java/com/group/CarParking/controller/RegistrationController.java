package com.group.CarParking.controller;

import java.util.ArrayList;
import java.util.List;

import com.group.CarParking.model.SlotModel;
import com.group.CarParking.model.UserModel;
import com.group.CarParking.service.SlotService;
import com.group.CarParking.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

class RegisterData {
    private String name, email, password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name + email + password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @GetMapping
    public String welcome(Model model) {
        return "register";
    }

    @GetMapping("/users")
    @ResponseBody
    public String hehe() {
        try {
            return SlotService.getSlots().get(0).getLocation();
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping(consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE, MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public String register(@RequestBody RegisterData registerData) {
        // String email = registerData.get("email");
        // String password = registerData.get("password").toString();
        // String name = registerData.get("name").toString();
        String email = registerData.getEmail();
        String name = registerData.getName();
        String password = registerData.getPassword();
        String res = UserService.createUser(UserModel.fromRegister(email, name, password));
        // model.addAttribute("slotList", list<SlotModel>);
        return res;
    }
}
