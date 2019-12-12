package com.security.controller;

import com.security.model.UsersDTO;
import com.security.service.MailService;
import com.security.service.UsersService;
import com.security.utils.JWTHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@CrossOrigin(maxAge = 3600)
@RestController
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private MailService mailService;

    @Autowired
    JWTHelper jwtHelper;

    /**
     * User signUp Function
     * @param userFormDTO
     * @return
     */
    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public UsersDTO signUp(@RequestBody UsersDTO userFormDTO) {
        userFormDTO.setUserType("user");
        userFormDTO.setConfirmed("0");
        usersService.addUser(userFormDTO);

        UsersDTO savedUsersDTO = usersService.findUser(userFormDTO);

        try {
            mailService.sendMailHtml(savedUsersDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedUsersDTO;
    }

    @RequestMapping("/activate")
    public void activate(HttpServletRequest request) {
        String activateCode = request.getParameter("activateCode");
        String activateCodeArgs[] = activateCode.split("@");
        String userId = activateCodeArgs[1];
        UsersDTO usersDTO = usersService.findUsersById(Long.valueOf(userId).longValue());

        usersDTO.setConfirmed("1");
        usersService.addUser(usersDTO);
    }

    /**
     * User signIn function
     * @param userFormDTO
     * @return
     */
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public UsersDTO sigIn(@RequestBody UsersDTO userFormDTO,HttpServletResponse response ) {
        UsersDTO usersDTO = usersService.findUser(userFormDTO);

        String token = jwtHelper.generateToken(usersDTO);
        usersDTO.setToken(token);
        System.out.println("/signIn service response status=======>"+response.getStatus());
        return usersDTO;
    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public UsersDTO updateProfile(@RequestBody UsersDTO userFormDTO) {
        userFormDTO.setConfirmed("1");

        usersService.addUser(userFormDTO);

        return userFormDTO;
    }

    @RequestMapping(value = "/zuulTest")
    public UsersDTO zuulTest() {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserType("Zuul Test");

        return usersDTO;
    }
}
