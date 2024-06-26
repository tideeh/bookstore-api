package com.example.bookstoreapi.models.vo.security;

import java.io.Serializable;
import java.util.Objects;

public class AccountCredentialsVO implements Serializable {

    private String username;
    private String password;

    public AccountCredentialsVO() {}

    public AccountCredentialsVO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AccountCredentialsVO)) {
            return false;
        }
        AccountCredentialsVO accountCredentialsVO = (AccountCredentialsVO) o;
        return Objects.equals(username, accountCredentialsVO.username) && Objects.equals(password, accountCredentialsVO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
