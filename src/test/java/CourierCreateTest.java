import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends BaseTest {

    //При создании нового курьера 201 код ответа и "ок": true
    @Test
    @Description("When create new courier - response code is 200 and response body contains ok: true")
    public void createNewCourierAndCheckResponse() {
        Response response = courierApi.createNewCourier(createCourierPOJO);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    //При создании двух одинаковых курьеров ошибка 409 и соответствующий текст ошибки
    @Test
    @Description("When trying to create two same couriers - response code is 409 and actual error message")
    public void cannotCreateTwoSameCouriers() {
        courierApi.createNewCourier(createCourierPOJO);
        Response response = courierApi.createNewCourier(createCourierPOJO);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(SC_CONFLICT);
    }

    @After
    public void deleteCourier() {
        int id = courierApi.getCourierId(loginCourierPOJO);
        courierApi.deleteCourier(id);
    }
}
