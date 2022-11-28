package com.iiitb.oaes.controller;

import com.iiitb.oaes.modal.User;
import com.iiitb.oaes.modal.DAO.UserDao;
import com.iiitb.oaes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserConrtroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody Map<String,String> payload)
    {
        boolean login = userService.login(payload);
        return ResponseEntity.ok(login);
    }
}
