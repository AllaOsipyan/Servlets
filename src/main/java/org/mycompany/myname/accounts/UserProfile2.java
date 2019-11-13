package org.mycompany.myname.accounts;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "usersaccounts", schema = "users")
public class UserProfile2 implements Serializable {


    @Id
    @Column(name = "login", unique = true, nullable = false)
    private String login;

    @Column(name = "session_id", unique = true)
    private String sessionId;

    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public UserProfile2() {
    }

    public UserProfile2(String login, String pass, String email) {
        this.setLogin(login);
        this.setPass(pass);
        this.setEmail(email);
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login){
        this.login = login;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass){
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }
}
