package org.mycompany.myname.accounts;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "accounts", schema = "users")
public class UserProfile implements Serializable {


    @Id
    @Column(name = "login", unique = true, nullable = false)
    private String login;


    @Column(name = "pass", nullable = false)
    private String pass;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    public UserProfile() {
    }
    public UserProfile(String login, String pass, String email) {

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
