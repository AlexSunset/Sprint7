import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {

    public final String login = "sunset";
    public final String password = "123";
    public final String firstName = "Alex";

    CreateCourierPOJO createCourierPOJO = new CreateCourierPOJO(login, password, firstName);
    LoginCourierPOJO loginCourierPOJO = new LoginCourierPOJO(login, password);
    CourierApi courierApi = new CourierApi();
    OrderApi orderApi = new OrderApi();
    CreateOrderPOJO createOrder = new CreateOrderPOJO("Alex", "Zakatov", "Moscow",
            5, "+79991234567", 2, "2020-06-06", "Comment");

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

}
