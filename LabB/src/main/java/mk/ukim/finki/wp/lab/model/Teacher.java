package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.Random;

@Data
public class Teacher {
    private Long id;
    private String name;
    private String surname;

    public Teacher(String name, String surname) {
        Random random = new Random();
        this.id = random.nextLong();
        this.name = name;
        this.surname = surname;
    }
}
