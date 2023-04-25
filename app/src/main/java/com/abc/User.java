package com.abc;

public class User {

    private int id_costumer;
    private int id_stuff;
    private String login;
    private String password;
    private String name;
    private String surname;
    private String middle_name;

    public User(int id_costumer, int id_stuff, String login, String password, String name, String surname, String middle_name) {
        this.id_costumer = id_costumer;
        this.id_stuff = id_stuff;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.middle_name = middle_name;
    }

    public User() {

    }

    public int getId_costumer() {
        return id_costumer;
    }

    public void setId_costumer(int id_costumer) {
        this.id_costumer = id_costumer;
    }

    public int getId_stuff() {
        return id_stuff;
    }

    public void setId_stuff(int id_stuff) {
        this.id_stuff = id_stuff;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }
}
