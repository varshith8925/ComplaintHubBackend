package com.ComplaintManagement.repo;

import com.ComplaintManagement.model.User;
import com.ComplaintManagement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    List<User> findByRole(Role role);
}
