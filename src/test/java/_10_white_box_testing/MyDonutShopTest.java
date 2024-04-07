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
        myDonutShop.openForTheDay();
        when(bakeryService.getDonutsRemaining()).thenReturn(1);
        Order o = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        when(paymentService.charge(o)).thenReturn(true);
        //when
        myDonutShop.takeOrder(o);

        //then
        verify(paymentService, times(1)).charge(o);
        verify(deliveryService, times(1)).scheduleDelivery(o);
        verify(bakeryService, times(1)).removeDonuts(1);


    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() throws Exception {
        //given
        myDonutShop.openForTheDay();
        Order o = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                55,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);

        //when
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(o));
        //then
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
        verify(bakeryService, never()).removeDonuts(o.getNumberOfDonuts());
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException() throws Exception {
        //given
        Order o = new Order("CUSTOMER_NAME",
                "CUSTOMER_PHONE_NUMBER",
                1,
                5.00,
                "CREDIT_CARD_NUMBER",
                true);
        myDonutShop.closeForTheDay();

        //when
        Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(o));

        //then
        assertEquals(exceptionThrown.getMessage(), "Sorry we're currently closed");
        assertEquals(0, bakeryService.getDonutsRemaining());
        verify(bakeryService, times(1)).throwAwayLeftovers();
    }

}
