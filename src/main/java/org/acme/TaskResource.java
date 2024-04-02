package org.acme;

import java.util.List;
import java.util.UUID;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @GET
    public List<Task> getAll() {
        return Task.listAll();
    }

    @GET
    @Path("/{id}")
    public Task getTask(UUID id) {
        return Task.findById(id);
    }
    /*
    @POST
    @Transactional
    public Response create(Task task) {
        task.persist();
        if (task.isPersistent()) {
            return Response.created(URI.create("/tasks/" + task.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
    */
    public UUID addTask(String title, String description) {
        Task task = new Task(title, description);
        task.persist();
        return task.id;
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Task update(UUID id, Task task) {
        Task entity = Task.findById(id);
        if (entity == null) throw new NotFoundException();

        entity.title = task.title;
        entity.description = task.description;
        entity.completed = task.completed;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(UUID id) {
        Task entity = Task.findById(id);
        if (entity == null) throw new NotFoundException();

        entity.delete();
    }
}