package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Task extends PanacheEntity {

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
/*
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() { return this.completed; }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
*/
}