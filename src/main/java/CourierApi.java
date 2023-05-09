import io.qameta.allure.Step;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierApi {

    public final static String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public final static String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";

    public Response createNewCourier(CreateCourierPOJO createCourierPOJO) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourierPOJO)
                .when()
                .post(CREATE_COURIER_ENDPOINT);
    }

    public Response loginCourier(LoginCourierPOJO loginCourierPOJO) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierPOJO)
                .when()
                .post(LOGIN_COURIER_ENDPOINT);
    }

    public int getCourierId(LoginCourierPOJO loginCourierPOJO) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierPOJO)
                .when()
                .post(LOGIN_COURIER_ENDPOINT)
                .then().extract().path("id");
    }

    public void deleteCourier(int id) {
        given().delete(CREATE_COURIER_ENDPOINT + id);
    }
}
