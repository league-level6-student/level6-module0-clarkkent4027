package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    String name;

    @Mock
    Car car;

    @Mock
    CellPhone cellPhone;

    @BeforeEach
    void setUp() {
    MockitoAnnotations.openMocks(this);
    DeliveryDriver d = new DeliveryDriver(name, car, cellPhone);
    }

    @Test
    void itShouldWasteTime() {
        //given
        boolean expected = true;

        //when
        when(cellPhone.browseCatMemes()).thenReturn(true);
        boolean actual = cellPhone.browseCatMemes();
        //then
        assertEquals(expected, actual);
    }

    @Test
    void itShouldRefuel() {
        //given
        boolean expectedRefuel = true;
        int octane = 50;

        //when
        when(car.fillTank(octane)).thenReturn(true);
        boolean actualRefuel = car.fillTank(octane);

        //then
        assertEquals(expectedRefuel, actualRefuel);
    }

    @Test
    void itShouldContactCustomer() {
        //given
        boolean expected = true;
        String num = "911-911-911";

        //when
        when(cellPhone.call(num)).thenReturn(true);
        boolean actual = cellPhone.call(num);

        //then
        assertEquals(expected, actual);
    }

}
