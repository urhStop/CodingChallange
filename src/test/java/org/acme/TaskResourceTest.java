package org.acme;


import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TaskResourceTest {

    @Test
    @Order(1)
    public void testGetAll() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/tasks")
                .then()
                .statusCode(200)
                .body(is(ALL));
    }

    @Test
    @Order(2)
    public void testGet() {
        given()
                .accept(ContentType.JSON)
                .when()
                .get("/tasks/1")
                .then()
                .statusCode(200)
                .body(is(ONE));
    }

    @Test
    @Order(3)
    public void testCreate() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(CREATE_NEW)
                .post("/tasks")
                .then()
                .statusCode(201)
                .body(is(NEW_CREATED));
    }

    @Test
    @Order(4)
    public void testUpdate() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .body(UPDATE)
                .patch("/tasks/51")
                .then()
                .statusCode(200)
                .body(is(UPDATED));
    }

    private static final String ALL = "[{\"id\":1,\"title\":\"car\",\"description\":\"change tyres on car\",\"completed\":true},{\"id\":51,\"title\":\"shop\",\"description\":\"buy milk, eggs, bread\",\"completed\":false},{\"id\":101,\"title\":\"homework\",\"description\":\"do homework until weekend\",\"completed\":false},{\"id\":151,\"title\":\"dog\",\"description\":\"veterinarian next week on tuesday\",\"completed\":false},{\"id\":201,\"title\":\"work\",\"description\":\"work on project\",\"completed\":false}]";

    private static final String ONE = "{\"id\":1,\"title\":\"car\",\"description\":\"change tyres on car\",\"completed\":true}";

    private static final String CREATE_NEW = "{\"title\":\"birthday\",\"description\":\"wish grandma hbd on 10.4.\",\"completed\":false}";

    private static final String NEW_CREATED = "{\"id\":251,\"title\":\"birthday\",\"description\":\"wish grandma hbd on 10.4.\",\"completed\":false}";

    private static final String UPDATE = "{\"id\":51,\"title\":\"shop\",\"description\":\"buy bread, apples, oil, butter\",\"completed\":false}";

    private static final String UPDATED = "{\"id\":51,\"title\":\"shop\",\"description\":\"buy bread, apples, oil, butter\",\"completed\":false}";
}