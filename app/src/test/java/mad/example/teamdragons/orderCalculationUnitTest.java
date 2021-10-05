package mad.example.teamdragons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.testng.annotations.Test;
import org.junit.jupiter.api.BeforeEach;


public class orderCalculationUnitTest {

    private OrderCalculation ordercalculation;

    @BeforeEach
    public void setup(){

        ordercalculation = new OrderCalculation();
    }

    @Test
    public void testOrderCal(){

        double result = ordercalculation.orderCal(30.00, 5.00);
        assertEquals(35.00, result);

    }

}

