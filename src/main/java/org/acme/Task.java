package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

@Entity
@Table(name = "tasks")
public class Task extends PanacheEntityBase {

    @Id
    @GeneratedValue
    public UUID id;

    @NotBlank(message = "Title cannot be blank")
    public String title;

    @NotBlank(message = "Description cannot be blank")
    public String description;

    public boolean completed;

    public Task() {}

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.completed = false;
    }
}