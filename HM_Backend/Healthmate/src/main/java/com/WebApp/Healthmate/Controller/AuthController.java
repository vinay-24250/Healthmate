package com.WebApp.Healthmate.Controller;
import com.WebApp.Healthmate.DTOs.DoctorResponseDTO;
import com.WebApp.Healthmate.DTOs.LoginResponseDTO;
import com.WebApp.Healthmate.DTOs.SignupRequestDTO;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
@RestController
public class AuthController {


    @Autowired
    private AuthService authService;


    @PostMapping("/signup")
    public ResponseEntity<?>register(@RequestBody SignupRequestDTO requestDTO) {
        try {
            AppUser savedUser = authService.register(requestDTO);
            return new ResponseEntity<>(savedUser ,HttpStatus.CREATED);
        }catch(RuntimeException e){
            return  new ResponseEntity<>("Email already exist" ,HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody AppUser user , HttpServletResponse response){
       LoginResponseDTO responseDTO = authService.login(user);
       String rawToken = responseDTO.getToken().replace("Bearer " ,"");

        ResponseCookie cookie = ResponseCookie.from("token" , rawToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(24*60*60)
                .sameSite("Lax")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());
       return ResponseEntity.ok(new LoginResponseDTO(responseDTO.getRole() ,rawToken));
    }

    @GetMapping("/allUsers")
    public List<AppUser> allUsers(){
        return authService.getAllUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<AppUser> getUser(Authentication authentication){
   String email = authentication.getName();
   AppUser user = authService.getUserByEmail(email);
   return ResponseEntity.ok(user);
    }

    @GetMapping("/doctor/{specialization}")
    public List<DoctorResponseDTO> getDoctors(@PathVariable  String specialization){
        return authService.getDoctorsBySpecialization(specialization);
    }

    @GetMapping("/doctors")
    public List<DoctorResponseDTO> getDoctors(){
        return authService.getAllDoctors();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email){
        authService.generateResetToken(email);
        return  ResponseEntity.ok("Password reset link sent to email");
    }
}
