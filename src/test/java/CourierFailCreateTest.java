import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierFailCreateTest {

    private String login = "sunset";
    private String password = "123";
    private String firstName = "Alex";

    CreateCourier createCourier = new CreateCourier(login, password, firstName);

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    //Нельзя создать курьера без указания логина
    @Test
    public void cannotCreateCourierWithoutLogin() {
        createCourier.setLogin(null);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createCourier)
                        .when()
                        .post("/api/v1/courier");

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    //Нельзя создать курьера без указания пароля
    @Test
    public void cannotCreateCourierWithoutPassword() {
        createCourier.setPassword(null);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createCourier)
                        .when()
                        .post("/api/v1/courier");

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(400);
    }

    @After
    public void setDefault(){
        createCourier.setLogin(login);
        createCourier.setPassword(password);
    }
}