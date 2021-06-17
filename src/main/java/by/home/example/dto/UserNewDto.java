package by.home.example.dto;

import by.home.example.domain.Role;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserNewDto {

    @Pattern(regexp = "[A-Za-z]+")
    @NotNull
    @Size(min = 3, max = 16)
    private String userName;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$")
    @NotNull
    @Size(min = 3, max = 16)
    private String password;

    @NotNull
    @Size(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 16)
    @Pattern(regexp = "[A-Za-z]+")
    private String lastName;

    private Role role;

    public UserNewDto(@Pattern(regexp = "[A-Za-z]+") @NotNull @Size(min = 3, max = 16) String userName, @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,}$") @NotNull @Size(min = 3, max = 16) String password, @NotNull @Size(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String firstName, @NotNull @Size(min = 1, max = 16) @Pattern(regexp = "[A-Za-z]+") String lastName, Role role) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserNewDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
