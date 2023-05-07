import io.restassured.RestAssured;
import org.junit.Before;

public class BaseTest {

    public final String login = "sunset";
    public final String password = "123";
    public final String firstName = "Alex";

    CreateCourierJson createCourierJson = new CreateCourierJson(login, password, firstName);
    LoginCourierJson loginCourierJson = new LoginCourierJson(login, password);
    CourierApi courierApi = new CourierApi();
    OrderApi orderApi = new OrderApi();
    CreateOrderJson createOrder = new CreateOrderJson("Alex", "Zakatov", "Moscow",
            5, "+79991234567", 2,"2020-06-06", "Comment");

    @Before
    public void setUp(){
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru/";
    }

}
