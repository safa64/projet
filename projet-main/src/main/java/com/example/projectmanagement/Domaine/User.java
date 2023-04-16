package com.example.projectmanagement.Domaine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
public class User implements Serializable,UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "usr_seq")
    @SequenceGenerator(name = "usr_seq",sequenceName = "usr_seq")
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    private boolean activated;
    @Column(nullable = false)
    private String password;
    @Column(name = "date_de_creation")
    private LocalDateTime dateDeCreation;

    private String userLastName;
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(unique = true, nullable = false)
    private String email;
    private Long phoneNumber;
    private String title;

    @JsonIgnore
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_authorisation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "auth_id"))
    private Set<Authorisation> roles = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        dateDeCreation = LocalDateTime.now();
    }
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authorisation role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }


    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
