package com.security.service;

import com.security.domain.Users;
import com.security.model.UsersDTO;
import com.security.repository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    public void addUser(UsersDTO usersDTO) {
        Users users = new Users();
        BeanUtils.copyProperties(usersDTO, users);
        usersRepository.save(users);
    }

    public UsersDTO findUsersById(long id) {
        Users users = usersRepository.findById(id);
        UsersDTO usersDTO = new UsersDTO();
        BeanUtils.copyProperties(users, usersDTO);
        return usersDTO;
    }
//
//    public void editUser(UsersDTO usersDTO) {
//        Users users = FullStackBeanUtils.cloneUsersDTO(usersDTO);
//        usersRepository.save(users);
//    }
//
//    public UsersDTO findUserById(long id) {
//        return FullStackBeanUtils.cloneUsers(usersRepository.findById(id));
//    }
//
    public UsersDTO findUser(UsersDTO usersDTO) {
        Users user = usersRepository.findUsersByUserNameAndPassword(usersDTO.getUserName(), usersDTO.getPassword());
        UsersDTO savedUsersDTO = new UsersDTO();

        if(user != null) {
            BeanUtils.copyProperties(user, savedUsersDTO);
        }
        return savedUsersDTO;
    }


}
