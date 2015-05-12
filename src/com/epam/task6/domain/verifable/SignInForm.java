package com.epam.task6.domain.verifable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by olga on 21.04.15.
 */
public class SignInForm {
    @NotNull(message = "Login can't be empty")
    private String login;

    @NotNull(message = "Password can't be empty")
    @Size(min = 1, message = "Password length must be more than 0 characters")
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
