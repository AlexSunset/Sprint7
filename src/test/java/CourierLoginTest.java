import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest extends BaseTest {

    @Before
    public void createCourierForTest() {
        courierApi.createNewCourier(createCourierPOJO);
    }

    //Успешный логин и статус код 200
    @Test
    @Description("Basic login test. Status code is 200")
    public void successLogin() {
        courierApi.loginCourier(loginCourierPOJO)
                .then()
                .statusCode(SC_OK);
    }

    //Логин без логина, статус код 400 и сообщение об ошибке
    @Test
    @Description("Trying to login without login word - response code is 400 and actual error message")
    public void loginWithoutLoginError() {
        loginCourierPOJO.setLogin("");
        courierApi.loginCourier(loginCourierPOJO)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //Логин без пароля, статус код 400 и сообщение об ошибке
    @Test
    @Description("Trying to login without password - response code is 400 and actual error message")
    public void loginWithoutPasswordError() {
        loginCourierPOJO.setPassword("");
        courierApi.loginCourier(loginCourierPOJO)
                .then()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    @Description("Trying to login with wrong login word - response code is 404 and actual error message")
    public void wrongLogin() {
        loginCourierPOJO.setLogin("wrong");
        courierApi.loginCourier(loginCourierPOJO)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @Description("Trying to login with wrong password - response code is 404 and actual error message")
    public void wrongPassword() {
        loginCourierPOJO.setPassword("wrong");
        courierApi.loginCourier(loginCourierPOJO)
                .then()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier() {
        loginCourierPOJO.setLogin(login);
        loginCourierPOJO.setPassword(password);
        int id = given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierPOJO)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().path("id");
        given().delete("/api/v1/courier/" + id);
    }
}