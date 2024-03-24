package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
        double wage = 16.00;
        int hours = 24;
        double expected = wage*hours;

        //when
        double paychek = payroll.calculatePaycheck(wage, hours);

        //then
        assertEquals(expected, paychek);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
        int miles = 55;
        double expected = miles*0.575;

        //when
        double reimbursement = payroll.calculateMileageReimbursement(miles);

        //then
        assertEquals(expected, reimbursement);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
        String name = "Ezra";
        double wage = 2.50;
        String expected = "Hello Ezra, We are pleased to offer you an hourly wage of 2.50";

        //when
        String letter = payroll.createOfferLetter(name, wage);

        //then
        assertEquals(expected, letter);
    }

}
