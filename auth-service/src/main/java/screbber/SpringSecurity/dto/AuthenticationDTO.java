package screbber.SpringSecurity.dto;

import javax.validation.constraints.NotEmpty;

public class AuthenticationDTO {
    @NotEmpty(message = "Имя не должно быть пустым")
    private String username;

    @NotEmpty(message = "Пароль не может быть пустым")
    private String password;

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
}
