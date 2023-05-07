import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierApi {

    public final static String CREATE_COURIER_ENDPOINT = "/api/v1/courier";
    public final static String LOGIN_COURIER_ENDPOINT = "/api/v1/courier/login";

    public Response createNewCourier(CreateCourierJson createCourierJson){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(createCourierJson)
                .when()
                .post(CREATE_COURIER_ENDPOINT);
    }

    public Response loginCourier(LoginCourierJson loginCourierJson){
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(loginCourierJson)
                .when()
                .post(LOGIN_COURIER_ENDPOINT);
    }
}
