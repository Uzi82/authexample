package org.authexample.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String roles;
    public Collection<SimpleGrantedAuthority> getAuthorities() {
        return Arrays.stream(roles.split(", ")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
