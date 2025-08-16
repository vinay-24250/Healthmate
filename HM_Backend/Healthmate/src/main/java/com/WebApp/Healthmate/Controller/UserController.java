package com.WebApp.Healthmate.Controller;


import com.WebApp.Healthmate.DTOs.DoctorResponseDTO;
import com.WebApp.Healthmate.DTOs.LoginResponseDTO;
import com.WebApp.Healthmate.DTOs.SignupRequestDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {


    @Autowired
    private UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?>register(@RequestBody SignupRequestDTO requestDTO) {
        try {
            AppUser savedUser = userService.register(requestDTO);
            return new ResponseEntity<>(savedUser ,HttpStatus.CREATED);
        }catch(RuntimeException e){
            return  new ResponseEntity<>("Email already exist" ,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AppUser user){
       LoginResponseDTO responseDTO = userService.login(user);
       return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/allUsers")
    public List<AppUser> allUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<AppUser> getUser(Authentication authentication){
   String email = authentication.getName();
   AppUser user = userService.getUserByEmail(email);
   return ResponseEntity.ok(user);
    }

    @GetMapping("/doctor/{specialization}")
    public List<DoctorResponseDTO> getDoctors(@PathVariable  String specialization){
        return userService.getDoctorsBySpecialization(specialization);
    }




}
