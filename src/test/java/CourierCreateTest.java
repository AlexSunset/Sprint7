import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest extends BaseTest{

    //При создании нового курьера 201 код ответа и "ок": true
    @Test
    @Description("When create new courier - response code is 200 and response body contains ok: true")
    public void createNewCourierAndCheckResponse() {
        Response response = courierApi.createNewCourier(createCourierJson);
        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(SC_CREATED);
    }

    //При создании двух одинаковых курьеров ошибка 409 и соответствующий текст ошибки
    @Test
    @Description("When trying to create two same couriers - response code is 409 and actual error message")
    public void cannotCreateTwoSameCouriers() {
        courierApi.createNewCourier(createCourierJson);
        Response response = courierApi.createNewCourier(createCourierJson);
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(SC_CONFLICT);
    }

    @After
    public void deleteCourier(){
        int id = given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierJson)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().path("id");
        given().delete("/api/v1/courier/" + id);
    }
}
