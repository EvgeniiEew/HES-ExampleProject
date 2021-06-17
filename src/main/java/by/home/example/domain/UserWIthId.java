package by.home.example.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UserWIthId extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    private Integer id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public UserWIthId(Integer id, String username, String password, boolean enabled,
                      boolean accountNonExpired, boolean credentialsNonExpired,
                      boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }
}
