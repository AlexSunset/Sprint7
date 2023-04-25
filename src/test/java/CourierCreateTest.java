import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierCreateTest {

    private String login = "sunset";
    private String password = "123";
    private String firstName = "Alex";

    CreateCourier createCourier = new CreateCourier(login, password, firstName);
    LoginCourier loginCourier = new LoginCourier(login, password);

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    //При создании нового курьера 201 код ответа и "ок": true
    @Test
    @DisplayName("create courier and check that response code is 200 and field ok is true")
    public void createNewCourierAndCheckResponse() {
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createCourier)
                        .when()
                        .post("/api/v1/courier");

        response.then().assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    //При создании двух одинаковых курьеров ошибка 409 и соответствующий текст ошибки
    @Test
    public void cannotCreateTwoSameCouriers() {
        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createCourier)
                        .when()
                        .post("/api/v1/courier");
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and().statusCode(409);
    }

    @After
    public void deleteCourier(){
        int id = given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().path("id");
        given().delete("/api/v1/courier/" + id);
    }
}
