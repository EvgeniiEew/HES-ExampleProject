package by.home.example.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "user_name", nullable = false, unique = true)
    @Length(min = 3, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String userName;

    @Column(nullable = false)
    private String Password;

    @Length(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;

    @Length(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Date createdAt;

    public UserAccount(Integer id, @Length(min = 3, max = 16) @Pattern(regexp = "[A-Za-z]+") String userName, String password, @Length(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String firstName, @Length(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String lastName, Role role, Status status, Date createdAt) {
        this.id = id;
        this.userName = userName;
        Password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.status = status;
        this.createdAt = createdAt;

    }

    public UserAccount() {
    }

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return Password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Role getRole() {
        return role;
    }

    public Status getStatus() {
        return status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
