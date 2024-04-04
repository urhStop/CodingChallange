package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Task extends PanacheEntity {

    @NotBlank
    @Column(unique = true)
    public String title;

    public String description;

    public boolean completed;

    public Task() {}
}