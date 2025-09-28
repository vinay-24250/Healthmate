package com.WebApp.Healthmate.Service;

import com.WebApp.Healthmate.Model.UserPrinciple;
import com.WebApp.Healthmate.Model.AppUser;
import com.WebApp.Healthmate.Repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepo authRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser user = authRepo.findByEmail(email);

        return new UserPrinciple(user);
    }
}
