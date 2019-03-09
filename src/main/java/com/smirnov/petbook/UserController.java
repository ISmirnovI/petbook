package com.smirnov.petbook;

import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.smirnov.petbook.User;
import com.smirnov.petbook.UserRepository;

@Controller    // This means that this class is a Controller
@RequestMapping("/user") // This means URL's start with /demo (after Application path)
public class UserController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UserRepository userRepository;

    @GetMapping("/add") // Map ONLY GET Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        // This returns a JSON or XML with the users
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

}