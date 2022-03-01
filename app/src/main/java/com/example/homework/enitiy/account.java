package com.example.homework.enitiy;

public class account {
    private String account;
    private String password;
    private String email;

    public account(String acc, String psw, String mail) {
        this.account = acc;
        this.password = psw;
        this.email = mail;
    }

    public String getAcc() {
        return account;
    }

    public String getPsw() {
        return password;
    }

    public String getMail() {
        return email;
    }

    public void setAcc(String acc) {
        this.account = acc;
    }

    public void setPsw(String psw) {
        this.password = psw;
    }

    public void setMail(String mail) {
        this.email = mail;
    }
}
