package com.how2java.tmall.dao;
  
import com.how2java.tmall.pojo.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.how2java.tmall.pojo.User;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer>{
    User findByName(String name);
    User getByNameAndPassword(String name, String password);

}