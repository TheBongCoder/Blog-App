package com.thebongcoder.Blog.Application.repositories;

import com.thebongcoder.Blog.Application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(@Param("email") String email);
}
