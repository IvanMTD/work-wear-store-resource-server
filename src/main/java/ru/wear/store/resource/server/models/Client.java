package ru.wear.store.resource.server.models;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Table(name = "Clients")
public class Client {
    @Id
    @Serial
    private long id;

    private String username;
    private String password;

    private String role;

    private String surname;
    private String name;
    private String middleName;
    private Gender gender;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    private LocalDate birthdate;

    private String eMail;
    private String phoneNumber;

    private int index;
    private String country;
    private String state;
    private String city;
    private String street;
    private int houseNumber;
    private int apartmentNumber;

    public enum Gender {
        MALE("Male"), FEMALE("Female");

        private String description;

        Gender(String description){
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
