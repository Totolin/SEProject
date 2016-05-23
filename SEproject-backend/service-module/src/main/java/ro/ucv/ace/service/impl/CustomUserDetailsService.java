package ro.ucv.ace.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.ucv.ace.dto.user.UserDto;
import ro.ucv.ace.exception.ServiceEntityNotFoundException;
import ro.ucv.ace.service.LoginService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ctotolin on 24-Apr-16.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    LoginService loginService;

    private List<GrantedAuthority> getGrantedAuthorities(UserDto userDto) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + userDto.getAccount().getType()));

        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
            UserDto userDto = loginService.getByUsername(s);

            return new org.springframework.security.core.userdetails.User(userDto.getAccount().getUsername(), userDto.getAccount().getPassword(),
                    userDto.getAccount().getState().equals("Active"), true, true, true, getGrantedAuthorities(userDto));
        } catch (ServiceEntityNotFoundException e) {
            throw new UsernameNotFoundException("Username not found");
        }
    }
}
