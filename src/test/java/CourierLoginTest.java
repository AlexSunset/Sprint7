import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierLoginTest {

    private String login = "sunset";
    private String password = "123";
    private String firstName = "Alex";

    CreateCourier createCourier = new CreateCourier(login, password, firstName);
    LoginCourier loginCourier = new LoginCourier(login, password);

    //Создание тестового курьера
    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier");
    }

    //Успешный логин и статус код 200
    @Test
    public void successLogin(){
        given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(200);
    }

    //Логин без логина, статус код 400 и сообщение об ошибке
    @Test
    public void loginWithoutLoginError(){
        loginCourier.setLogin("");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    //Логин без пароля, статус код 400 и сообщение об ошибке
    @Test
    public void loginWithoutPasswordError(){
        loginCourier.setPassword("");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(400)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }


    @Test
    public void wrongLogin(){
        loginCourier.setLogin("wrong");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void wrongPassword(){
        loginCourier.setPassword("wrong");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourier)
                .when()
                .post("/api/v1/courier/login")
                .then()
                .statusCode(404)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @After
    public void deleteCourier(){
        loginCourier.setLogin(login);
        loginCourier.setPassword(password);
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