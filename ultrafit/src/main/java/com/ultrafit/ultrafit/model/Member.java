package com.ultrafit.ultrafit.model;

public class Member {

    private Long id; //identifier of the socio, Long because can be null
    private String name; //name of the socio
    private String surname; //appelido of the socio
    private String email; //email of the socio
    private String phone; //phone of the socio
    private String plan; //plan of membresia contratado (Basic Plan, Pro and Premium)
    public Member() {} //we create the empty constructor for Spring MVC

    public Member(Long id, String name, String surname, String email, String phone, String plan) {
        this.id = id; //the id etc etc
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone; //we asign the phone
        this.plan = plan; // Assign the plan in the constructor
    }
    //We need of course all the getters and the setters for libreria JSON to convert an object
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }
}
