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

    private static final String ALL = "[{\"id\":1,\"title\":\"car\",\"description\":\"change tyres on car\",\"completed\":true}]";
}