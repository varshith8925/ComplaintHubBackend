package com.ComplaintManagement.service;

import com.ComplaintManagement.dto.AuthResponse;
import com.ComplaintManagement.dto.LoginRequest;
import com.ComplaintManagement.dto.RegisterRequest;
import com.ComplaintManagement.model.Role;
import com.ComplaintManagement.model.User;
import com.ComplaintManagement.repo.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final UserRepo userRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepo userRepo, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse registerUser(RegisterRequest registerRequest)
    {
        User user=new User();
        user.setUserName(registerRequest.getUserName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(registerRequest.getRole());
        if(user.getRole()== Role.ADMIN)
        {
            user.setApproved(false);
        }
        else
        {
            user.setApproved(true);
        }
        User savedUser=userRepo.save(user);
        return mapToAuthResponse(savedUser);

    }

    public AuthResponse login(LoginRequest loginRequest)
    {
        User user=userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(
                ()->new RuntimeException("invalid email or password")
        );
        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword()))
        {
            throw new RuntimeException("invalid email or password");
        }
        if(!user.isApproved())
        {
            throw new RuntimeException("your account still not approved...contact the application owner");
        }

        return mapToAuthResponse(user);
    }

    public void approveAdmin(Long userId)
    {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user with this id not found"));
        if(user.getRole()!=Role.ADMIN)
        {
            throw  new RuntimeException("This user is not an Admin. Only Admin accounts require approval.");
        }
        if(user.isApproved())
        {
            throw new RuntimeException("user is already approved");
        }
        user.setApproved(true);
        userRepo.save(user);

    }

    public List<User> getAdminRequests()
    {
        return userRepo.findByRole(Role.ADMIN);
    }

    public void rejectAdmin(Long userId)
    {
        User user=userRepo.findById(userId).orElseThrow(()->new RuntimeException("user with this id not found"));
        if(user.getRole()!=Role.ADMIN)
        {
            throw new RuntimeException("This user is not an Admin. Only Admin accounts can be rejected.");
        }
        userRepo.delete(user);
    }

    private AuthResponse mapToAuthResponse(User user) {
        AuthResponse response = new AuthResponse();
        response.setId(user.getId());
        response.setUserName(user.getUserName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole());
        response.setApproved(user.isApproved());
        return response;
    }

}
