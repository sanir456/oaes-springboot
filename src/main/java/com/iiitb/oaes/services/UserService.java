package com.iiitb.oaes.services;

import com.iiitb.oaes.modal.DAO.UserDao;
import com.iiitb.oaes.modal.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service(value = "userService")
public class UserService {
    @Autowired
    private UserDao userDao;

    public boolean login(Map<String,String> payload){
        String userName = payload.get("userName");
        String password = payload.get("password");

        boolean login = false;
        User admin = userDao.findByUserName(userName);


        if(admin!=null && admin.getUserPassword().equals(password))
        {
            login = true;
        }
        else
        {
            login = false;
        }
        return login;
    }
}
