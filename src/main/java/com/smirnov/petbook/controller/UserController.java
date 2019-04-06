package com.smirnov.petbook.controller;

import com.smirnov.petbook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.smirnov.petbook.entity.User;

import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("delete/{userId}")
    public @ResponseBody String deleteUser (@PathVariable Integer userId){
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return("User had been successfully deleted.");
        } else {
            return("This User doesn't exist.");
        }

    }

    @GetMapping("delete/all")
    public @ResponseBody String deleteAllUser (){
    userRepository.deleteAll();
    return ("All Users had been successfully deleted");
    }

    @GetMapping("update/{userId}")
    public @ResponseBody User updateUser(@PathVariable Integer userId, @RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()) {
            User user = byId.get();
            user.setName(name);
            user.setEmail(email);
            userRepository.save(user);
            return user;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @GetMapping("{userId}")
    public @ResponseBody Optional<User> getUser(@PathVariable Integer userId){
        if(userRepository.existsById(userId)){
            return userRepository.findById(userId);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

}