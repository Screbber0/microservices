package screbber.SpringSecurity.dto;

import javax.validation.constraints.NotEmpty;

public class PersonDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    private String username;

    @NotEmpty(message = "email не должен быть пустым")
    private String email;
    private String password;

    public PersonDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
