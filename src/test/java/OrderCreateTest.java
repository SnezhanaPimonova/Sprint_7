import models.Order;
import org.junit.Before;
import steps.OrderStep;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreateTest {
    private OrderStep orderStep;
    private final String[] color;
    public OrderCreateTest(String[] color) {
        this.color = color;
    }

    @Parameterized.Parameters(name= "color = {0}")
    public static Object[][] colorData() {
        Object[][] objects;
        objects = new Object[][]{
                {new String[]{"GREY"}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY", "BLACK"}},
                {null},
        };
        return objects;
    }

    @Before
    public void setUp(){
        orderStep = new OrderStep();
    }

    @DisplayName("Создание заказа")
    @Description("Проверяется создание заказа на: серый цвет, черный цвет, два цвета, без выбора цвета")
    @Test
    public void checkCreateOrderTest() {
        Order order = new Order("Владимир", "Баранов", "Цветной бульвар, 1", "5", "79871112233", 3, "2023-05-21", "Поехали!", color);
        ValidatableResponse response = orderStep.createOrder(order);
        response
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(201);
    }
}

