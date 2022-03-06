package sg.edu.nus.iss.workshop13.model;

import java.util.Random;

/*
* Contact class representing contact information within address book
*/
public class Contact {

    private String name;
    private String email;
    private String phoneNumber;
    private String id;
    
    public Contact() {
        this.id = generate(8);
    }

    public Contact(String name, String email, String phoneNumber) {
        this.id = generate(8);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String id, String name, String email, String phoneNumber) {
        this.setId(id);
        this.setName(name);
        this.setEmail(email);
        this.setPhoneNumber(phoneNumber);
    }


    private synchronized String generate(int numChars) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while(strBuilder.length() < numChars) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        
        return strBuilder.toString().substring(0, numChars);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

}
