package com.example.secyrity.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String lastname;

    private int age;

    private String email;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roleList;

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User() {
    }

    public User(String username, String password, String lastname, int age, String email, List<Role> roleList) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.roleList=roleList;

    }
    public User(String username, String password, String lastname, int age, String email) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        roleList.add(new Role("USER"));

    }
/*    public User(String username, String password, String lastname, int age, String email,Role role) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        roleList.add(role);

    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoleList();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = passwordEncoder().encode(password);
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roles) {
        this.roleList = roles;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String roleToString(){
        StringBuilder sb = new StringBuilder();
        for(Role role: roleList){
            sb.append(role.getName()).append(" ");
        }
        return sb.toString();
    }
}
