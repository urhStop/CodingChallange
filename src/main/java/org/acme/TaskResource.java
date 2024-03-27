package org.acme;

import java.net.URI;
import java.util.List;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {

    @GET
    public List<Task> list() {
        return Task.listAll();
    }

    @GET
    @Path("/{id}")
    public Task getById(Long id) {
        return Task.findById(id);
    }

    @POST
    @Transactional
    public Response create(Task task) {
        task.persist();
        if (task.isPersistent()) {
            return Response.created(URI.create("/tasks" + task.id)).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Task update(Long id, Task task) {
        Task entity = Task.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.title = task.title;
        entity.description = task.description;
        entity.completed = task.completed;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(Long id) {
        Task entity = Task.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }
}