package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.DTOs.DoctorResponseDTO;
import com.WebApp.Healthmate.DTOs.LoginResponseDTO;
import com.WebApp.Healthmate.DTOs.SignupRequestDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Model.Doctor;
import com.WebApp.Healthmate.Model.Role;
import com.WebApp.Healthmate.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;

    @Autowired
 private PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    public AppUser register(SignupRequestDTO signupRequestDTO){
        if(authRepo.existsByEmail(signupRequestDTO.getEmail())) {
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

        return authRepo.save(user);
    }

    public LoginResponseDTO login(AppUser user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail() ,user.getPassword()));

        if(!authentication.isAuthenticated()){
            throw new BadCredentialsException("Invalid credentials");
        }
        AppUser existingUser = authRepo.findByEmail(user.getEmail());
        String token = jwtService.generateToken(user.getEmail());
        Role role = existingUser.getRole();
        return new LoginResponseDTO(existingUser.getRole() , "Bearer " + token);
    }

    public List<AppUser> getAllUsers() {

        return authRepo.findAll();
    }

    public AppUser getUserByEmail(String email) {
        return authRepo.findByEmail(email);
    }


    public List<DoctorResponseDTO> getDoctorsBySpecialization(String specialization) {
        List<AppUser> doctors = authRepo.findByRoleAndDoctor_Specialization(Role.DOCTOR , specialization);
        return doctors.stream().map(user ->new DoctorResponseDTO(
                user.getDoctor().getDocId(),
                user.getFullName(),
                user.getEmail(),
                user.getDoctor().getSpecialization(),
                user.getDoctor().getQualification(),
                user.getDoctor().getHospitalName()
        )).collect(Collectors.toList());
    }

    public void generateResetToken(String email) {
        try {
            AppUser user = authRepo.findByEmail(email);
            String resetToken = UUID.randomUUID().toString();

            user.setResetToken(resetToken);
            user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
            authRepo.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<DoctorResponseDTO> getAllDoctors() {
        List<AppUser> doctors = authRepo.findByRole(Role.DOCTOR);
        return doctors.stream().map(user ->new DoctorResponseDTO(
                user.getDoctor().getDocId(),
                user.getFullName(),
                user.getEmail(),
                user.getDoctor().getSpecialization(),
                user.getDoctor().getQualification(),
                user.getDoctor().getHospitalName()
        )).collect(Collectors.toList());
    }
    }

