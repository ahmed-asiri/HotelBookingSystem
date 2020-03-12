package Models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ia7ma
 */
public class User {
      private String password;
      private String ssn;
      private String fname;
      private String lname;
      private String email;
      private String phone;
      
      
public User(){
    
}

    public User(String password, String ssn, String fname, String lname, String email, String phone) {
        this.password = password;
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.phone = phone;
    }


public boolean login(String ssn, String pass){
    return false;
}

public void logout(){
    // remove the object and go back to the main page.
    
}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}



