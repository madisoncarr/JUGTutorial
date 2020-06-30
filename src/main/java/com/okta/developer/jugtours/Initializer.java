package com.okta.developer.jugtours;

import com.okta.developer.jugtours.model.Event;
import com.okta.developer.jugtours.model.Group;
import com.okta.developer.jugtours.model.GroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.stream.Stream;

@Profile("initialize")
@Component //denoted class for Spring to autodetect for dependency injection
class Initializer implements CommandLineRunner { //CommandLineRunner has run() method for what to do before starting db, like seeding, etc

    private final GroupRepository repository;

    public Initializer(GroupRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("Denver JUG", "Utah JUG", "Seattle JUG",
                "Richmond JUG").forEach(name ->
                repository.save(new Group(name))
        );

        Group djug = repository.findByName("Denver JUG");
        Event e = Event.builder().title("Full Stack Reactive")
                .description("Reactive with Spring Boot + React")
                .date(Instant.parse("2018-12-12T18:00:00.000Z"))
                .build();
        djug.setEvents(Collections.singleton(e));
        repository.save(djug);

        repository.findAll().forEach(System.out::println); //double colon operator/method reference operator
    }
}
