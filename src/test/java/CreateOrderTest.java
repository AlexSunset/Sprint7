import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;


@RunWith(Parameterized.class)
public class CreateOrderTest extends BaseTest{

    ArrayList<String> testColour;

    public CreateOrderTest(ArrayList<String> testColour){
        this.testColour = testColour;
    }

    @Parameterized.Parameters
    public static Object[][] getColourData() {
        return new Object[][] {
                {new ArrayList<>(List.of("BLACK"))},
                {new ArrayList<>(List.of("GREY"))},
                {new ArrayList<>(Arrays.asList("BLACK", "GREY"))},
                {new ArrayList<String>()},
        };
    }

    @Test
    public void RandomColourOrderCreateReturns201(){
        createOrder.setColor(testColour);
        Response response = orderApi.createNewOrder(createOrder);
        response.then().assertThat().statusCode(SC_CREATED);
    }
}