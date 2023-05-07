import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    public final static String CREATE_ORDER_ENDPOINT = "/api/v1/orders";

    public Response createNewOrder(CreateOrderJson createOrderJson) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createOrderJson)
                .when()
                .post(CREATE_ORDER_ENDPOINT);
    }
}
