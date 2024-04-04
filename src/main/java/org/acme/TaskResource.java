package org.acme;

import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/tasks")
public class TaskResource {

    @GET
    public List<Task> getAll() {
        return Task.listAll();
    }

    @GET
    @Path("/{id}")
    public Task getById(@PathParam("id") Long id) {
        Task entity = Task.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Task with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(@Valid Task task) {
        task.persist();
        return Response.status(Status.CREATED).entity(task).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public Response update(@Valid Task task, @PathParam("id") Long id) {
        Task entity = Task.findById(id);
        entity.id = id;
        entity.title = task.title;
        entity.description = task. description;
        entity.completed = task.completed;
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOne(@PathParam("id") Long id) {
        Task entity = Task.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Todo with id of " + id + " does not exist.", Status.NOT_FOUND);
        }
        entity.delete();
        return Response.noContent().build();
    }
}