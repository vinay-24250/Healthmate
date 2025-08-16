package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.DTOs.DoctorResponseDTO;
import com.WebApp.Healthmate.DTOs.LoginResponseDTO;
import com.WebApp.Healthmate.DTOs.SignupRequestDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Doctor;
import com.WebApp.Healthmate.Model.Role;
import com.WebApp.Healthmate.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
 private PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public AppUser register(SignupRequestDTO signupRequestDTO){
        if(userRepo.existsByEmail(signupRequestDTO.getEmail())) {
            System.out.println("Already exist");
            throw new RuntimeException("Email already exist");
        }
       AppUser user = new AppUser();
        user.setFullName(signupRequestDTO.getFullName());
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(encoder.encode(signupRequestDTO.getPassword()));
        user.setRole(signupRequestDTO.getRole());

        if(user.getRole() == Role.DOCTOR){

           Doctor doc = new Doctor();
            doc.setUser(user);
            doc.setSpecialization(signupRequestDTO.getSpecialization());
            doc.setQualification(signupRequestDTO.getQualification());
            doc.setHospitalName(signupRequestDTO.getHospitalName());

            user.setDoctor(doc);
        }

        return userRepo.save(user);
    }

    public LoginResponseDTO login(AppUser user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail() ,user.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Invalid credentials");
        }
        Optional<AppUser> existingUser = userRepo.findByEmail(user.getEmail());
        String token = jwtService.generateToken(user.getEmail());
        Role role = existingUser.get().getRole();
        return new LoginResponseDTO(existingUser.get().getRole() , "Bearer " + token);
    }

    public List<AppUser> getAllUsers() {
        return userRepo.findAll();
    }

    public AppUser getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() ->new RuntimeException("User not found"));
    }


    public List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization) {
        List<AppUser> doctors = userRepo.findByRoleAndDoctor_Specialization(Role.DOCTOR , specialization);
        return doctors.stream().map(user ->new DoctorResponseDTO(
                user.getDoctor().getDoc_id(),
                user.getFullName(),
                user.getEmail(),
                user.getDoctor().getSpecialization(),
                user.getDoctor().getQualification(),
                user.getDoctor().getHospitalName()
        )).collect(Collectors.toList());
    }
}
