import steps.OrderStep;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;

public class GetListOrdersTest {

    private OrderStep orderStep;

    @Before
    public void setUp(){
        orderStep = new OrderStep();
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Проверяется, что запрос возвращает список заказов")
    public void getListOrdersTest() {
        ValidatableResponse response = orderStep.getListOrders();
        response
                .assertThat()
                .body("orders", notNullValue())
                .and()
                .statusCode(200);
    }
}


