import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest extends BaseTest {

    @Test
    public void getOrdersReturnOrders(){
        given()
                .when()
                .get("/v1/orders")
                .then()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());
    }


    @Test
    public void orderResponseBodyContainsTrack(){
        Response response = orderApi.createNewOrder(createOrder);
        response.then().assertThat().body("track", Matchers.notNullValue());
    }
}