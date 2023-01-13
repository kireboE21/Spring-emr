package com.cobanogluhasan.springboot.repository;



import com.cobanogluhasan.springboot.controller.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
