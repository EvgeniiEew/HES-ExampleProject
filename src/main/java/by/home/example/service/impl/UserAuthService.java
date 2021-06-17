package by.home.example.service.impl;

import by.home.example.domain.Status;
import by.home.example.domain.UserAccount;
import by.home.example.domain.UserWIthId;
import by.home.example.service.IUserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {
    @Autowired
    private IUserAccountService iUserAccountService;

    @Override
    public UserWIthId loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            UserAccount userAccount = iUserAccountService.findByLogin(login);
            return new UserWIthId(userAccount.getId(), userAccount.getUserName(), userAccount.getPassword(), true, true, true, isAccountNonLocked(userAccount), toAuthorities(userAccount));
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User with login not found");
        }
    }

    private Collection<? extends GrantedAuthority> toAuthorities(UserAccount userAccount) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userAccount.getRole()));
    }

    private boolean isAccountNonLocked(UserAccount userAccount) {
        if (userAccount.getStatus() == Status.ACTIVE) {
            return true;
        } else return false;
    }
}
