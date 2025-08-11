package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.Model.UserPrinciple;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> user = Optional.ofNullable(userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found")));

        return new UserPrinciple(user);
    }
}
