package com.ultrafit.ultrafit.model;

public class Trainer {

    private Long id; //the id of the trainer
    private String name; //the name of the trainer
    private String specialty; //the speciality of the trainer, it depends of what he do (Boxing, Yoga)
    private int experienceYears; //And how many experience he have
    //Now we just do the same thing that we have done for the member, but this time for the trainer
    
    public Trainer() {}

    public Trainer(Long id, String name, String specialty, int experienceYears) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.experienceYears = experienceYears;
    }

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }
}
