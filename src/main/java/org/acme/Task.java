package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

@Entity
public class Task extends PanacheEntity {

    @NotBlank
    @Column(unique = true)
    public String title;

    public String description;

    public boolean completed;

    public Task() {}

    public static List<Task> findNotCompleted() {
        return list("completed", false);
    }

    public static List<Task> findCompleted() {
        return list("completed", true);
    }

    public static long deleteCompleted() {
        return delete("completed", true);
    }
}