import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderApi {

    public final static String CREATE_ORDER_ENDPOINT = "/api/v1/orders";

    public Response createNewOrder(CreateOrderPOJO createOrderPOJO) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createOrderPOJO)
                .when()
                .post(CREATE_ORDER_ENDPOINT);
    }

    public static Response getOrders() {
        return given()
                .when()
                .get(CREATE_ORDER_ENDPOINT);
    }
}
