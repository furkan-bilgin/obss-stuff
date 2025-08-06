package com.furkanbilgin.week3.springmvc.repository;

import com.furkanbilgin.week3.springmvc.model.User;
import com.furkanbilgin.week3.springmvc.model.dto.UserDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(
            "UPDATE User u SET u.name = :#{#userDTO.name}, "
                    + "u.surname = :#{#userDTO.surname}, "
                    + "u.email = :#{#userDTO.email} "
                    + "WHERE u.id = :id")
    void updateUser(Long id, UserDTO userDTO);
}
