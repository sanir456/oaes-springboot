package com.iiitb.oaes.modal.DAO;

import com.iiitb.oaes.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserDao extends JpaRepository<User,Integer> {
    public User findByUserName(String username);
}
