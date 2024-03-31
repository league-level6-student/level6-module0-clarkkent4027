package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;

    @Mock
    DeliveryService deliveryService;

    @Mock
    BakeryService bakeryService;

    @Mock
    PaymentService paymentService;


    @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
     myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
        Order o = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);

        //when
        myDonutShop.openForTheDay();
        myDonutShop.takeOrder(o);

        //then
        verify(myDonutShop, times(1)).addOrder(o);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        //given
        Order o = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);

        //when
        myDonutShop.openForTheDay();

        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(o));
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
        verify(myDonutShop, never()).addOrder(o);
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given

        //when

        //then
    }

}
