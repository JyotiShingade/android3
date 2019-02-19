package com.example.ass3android;

public class User
{
    private String firstname,lastname,email;
    private String dob;
    private String id;

    public User(String id, String firstname, String lastname, String email, String dob) {
       this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.dob = dob;
    }

   public String  getId() {
        return id;
    }

    public String getFirstname()
    {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getDob() {
        return dob;
    }
}
