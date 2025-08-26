package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Thread.sleep;
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

    @Test
    void testReceiveMultiplePowerEvents() throws InterruptedException {
        int expectedTotalPower = 0;
        int power = 100;
        String devicePower = "{\"device\":\"mysecondcar\",\"power\":" + power + "}";
        for (int i=0; i<2; i++) {
            given()
                    .contentType("application/json")
                    .body(devicePower)
                    .when()
                    .post("/power")
                    .then()
                    .body(equalTo("Great Success!"));

            expectedTotalPower += power;
        }
    }
}