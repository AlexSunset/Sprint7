import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";

    }

    @Test
    public void getOrdersReturnOrders(){
        given()
                .when()
                .get("/v1/orders")
                .then()
                .statusCode(200)
                .and()
                .body("orders", notNullValue());
    }
}