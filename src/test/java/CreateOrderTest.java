import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class CreateOrderTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

    CreateOrder createOrder = new CreateOrder("Alex", "Zakatov", "Moscow",
            5, "+79991234567", 2,"2020-06-06", "Comment");

    @Test
    public void blackColourOrderCreate(){
        ArrayList<String> testColour = new ArrayList<>();
        testColour.add("BLACK");
        createOrder.setColor(testColour);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createOrder)
                        .when()
                        .post("/api/v1/orders");

        response.then().assertThat().statusCode(201);
    }

    @Test
    public void greyColourOrderCreate(){
        ArrayList<String> testColour = new ArrayList<>();
        testColour.add("GREY");
        createOrder.setColor(testColour);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createOrder)
                        .when()
                        .post("/api/v1/orders");

        response.then().assertThat().statusCode(201);
    }

    @Test
    public void blackAndGreyColourOrderCreate(){
        ArrayList<String> testColour = new ArrayList<>();
        testColour.add("BLACK");
        testColour.add("GREY");
        createOrder.setColor(testColour);
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createOrder)
                        .when()
                        .post("/api/v1/orders");

        response.then().assertThat().statusCode(201);
    }

    @Test
    public void noColourOrderCreate(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createOrder)
                        .when()
                        .post("/api/v1/orders");

        response.then().assertThat().statusCode(201);
    }

    @Test
    public void orderResponseBodyContainsTrack(){
        Response response =
                given()
                        .header("Content-type", "application/json")
                        .and()
                        .body(createOrder)
                        .when()
                        .post("/api/v1/orders");

        response.then().assertThat().body("track", notNullValue());
    }
}