import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

public class CourierFailCreateTest extends BaseTest {


    //Нельзя создать курьера без указания логина
    @Test
    @Description("When trying create courier without login - response code is 400 and actual error message")
    public void cannotCreateCourierWithoutLogin() {
        createCourierJson.setLogin(null);
        Response response = courierApi.createNewCourier(createCourierJson);

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    //Нельзя создать курьера без указания пароля
    @Test
    @Description("When trying create courier without password - response code is 400 and actual error message")
    public void cannotCreateCourierWithoutPassword() {
        createCourierJson.setPassword(null);
        Response response = courierApi.createNewCourier(createCourierJson);

        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @After
    public void setDefault(){
        createCourierJson.setLogin(login);
        createCourierJson.setPassword(password);
    }
}