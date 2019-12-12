package com.security.controller;

import com.security.model.UsersDTO;
import com.security.utils.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(maxAge = 3600)
@RestController
public class JWTController {

    @Autowired
    private JWTHelper jwtHelper;

    /**
     *
     * @param token
     */
    @RequestMapping("/verifyToken")
    public Map verifyToken(@RequestParam("token") String token) throws ServletException {
        HashMap<String, String> map  = jwtHelper.validateToken(token);
        System.out.println(map);
        return map;
    }

    @RequestMapping("/getTokenTest")
    public UsersDTO generateMyToken(){
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserName("testUserName");
        usersDTO.setUserType("testUserType");
        String token = jwtHelper.generateToken(usersDTO);

        usersDTO.setToken(token);
        return usersDTO;
    }

}
