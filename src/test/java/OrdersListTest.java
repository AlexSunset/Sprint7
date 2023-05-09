import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.CoreMatchers.notNullValue;

public class OrdersListTest extends BaseTest {

    @Test
    public void getOrdersReturnOrders() {
        OrderApi.getOrders()
                .then()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue());
    }


    @Test
    public void orderResponseBodyContainsTrack() {
        Response response = orderApi.createNewOrder(createOrder);
        response.then().assertThat().body("track", Matchers.notNullValue());
    }
}