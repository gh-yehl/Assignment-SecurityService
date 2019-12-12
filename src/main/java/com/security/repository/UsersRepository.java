package com.security.repository;

import com.security.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UsersRepository extends JpaRepository<Users,Long> {
    public Users findById(long id);

    public Users findUsersByUserNameAndPassword(String userName, String password);

    @Query(value = "select * from Users u where u.user_name like %?1% " , nativeQuery = true)
    public List<Users> findByUserNameLike(String userName);
}
