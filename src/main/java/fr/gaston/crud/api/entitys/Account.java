package fr.gaston.crud.api.entitys;


import fr.gaston.crud.api.security.Roles;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@Table(name = "account")
@ToString
public class Account implements UserDetails {
    @Id
    @SequenceGenerator(name = "name_sequence", sequenceName = "name_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "name_sequence")
    private Long id;
    private String username;
    private String mail;
    private String password;
    @Enumerated(EnumType.STRING)
    private Roles role;


    public Account() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
