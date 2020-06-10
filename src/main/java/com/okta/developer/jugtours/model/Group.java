package com.okta.developer.jugtours.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data //creates getters, setters, toString method, etc
@NoArgsConstructor //intended to document intention, required for hibernate
@RequiredArgsConstructor //generates constructor with one param per field w/ special handling, throws NullPointerException for @NonNull
@Entity //tells Java that this is a database model and should persist
@Table(name = "user_group")
public class Group {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull //marks field below as required
    private String name;
    private String address;
    private String city;
    private String stateOrProvince;
    private String country;
    private String postalCode;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Event> events;
}
