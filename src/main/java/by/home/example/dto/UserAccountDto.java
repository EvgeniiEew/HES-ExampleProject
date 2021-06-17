package by.home.example.dto;

import by.home.example.domain.Role;
import by.home.example.domain.StatusView;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class UserAccountDto {
    private Integer id;

    @Length(min = 3, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String userName;


    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$")
    @Length(max = 16)
    private String Password;

    @Length(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;

    @Length(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;

    private Role role;

    private StatusView statusView;

    private Date createdAt;

    public UserAccountDto(Integer id, @Length(min = 3, max = 16) @Pattern(regexp = "[A-Za-z]+") String userName, @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$") @Length(max = 16) String password, @Length(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String firstName, @Length(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String lastName, Role role, StatusView statusView, Date createdAt) {
        this.id = id;
        this.userName = userName;
        Password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.statusView = statusView;
        this.createdAt = createdAt;
    }

    public UserAccountDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StatusView getStatusView() {
        return statusView;
    }

    public void setStatusView(StatusView statusView) {
        this.statusView = statusView;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
