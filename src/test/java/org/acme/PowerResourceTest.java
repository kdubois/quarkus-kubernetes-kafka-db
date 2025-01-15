package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class PowerResourceTest{

    @Test
    void testGetDevices() {
        // test values are in src/main/resources/import.sql
        given().when().get("/power").then().statusCode(200)
                    .body("[0].device", is("barbiecar")).and()
                    .body("[1].device", is("oppenheimercar"));
    }

    @Test
    void testReceivePowerEvent() {
        String devicePower = "{\"device\":\"mycar\",\"power\":100}";
        given()
                .contentType("application/json")
                .body(devicePower)
                .when()
                    .post("/power")
                .then()
                    .body(equalTo("Great Success!"));
    }
}