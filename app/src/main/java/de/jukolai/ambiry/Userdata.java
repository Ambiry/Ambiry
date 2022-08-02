package de.jukolai.ambiry;

public class Userdata {
    private String FirstName;
    private String Email;
    private String password;

    public Userdata(String firstName, String email, String password) {
        FirstName = firstName;
        Email = email;
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
