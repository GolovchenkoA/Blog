package com.company.model;

public class User {

    private Long id;
    private String login;
    private String password;
    private String username;

    public User(){}

/*    public User(Long id, String login, String password, String username) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.username = username;
    }*/
        public User( String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public String toString() {

        return id + ":" + login + ":" + password + ":" + username;
    }
}

