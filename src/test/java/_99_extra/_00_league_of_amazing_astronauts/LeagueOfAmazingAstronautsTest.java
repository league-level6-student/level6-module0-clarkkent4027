package _99_extra._00_league_of_amazing_astronauts;

import _10_white_box_testing.MyDonutShop;
import _99_extra._00_league_of_amazing_astronauts.LeagueOfAmazingAstronauts;
import _99_extra._00_league_of_amazing_astronauts.models.Astronaut;
import _99_extra._00_league_of_amazing_astronauts.models.Rocketship;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/*

When writing the tests, mock both the Rocketship and Astronaut for the sake of practice.
 */
class LeagueOfAmazingAstronautsTest {

    LeagueOfAmazingAstronauts underTest = new LeagueOfAmazingAstronauts();


    @Mock
    Rocketship rocketship;

    @Mock
    Astronaut astronaut;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        rocketship = new Rocketship();
        astronaut = new Astronaut();
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
        underTest.setRocketship(rocketship);
        //when
        underTest.prepareAstronaut(astronaut);
        //then
        verify(astronaut, times(1)).isTrained();
        verify(rocketship, times(1)).loadOccupant(astronaut);
    }

    @Test
    void itShouldLaunchRocket() throws Exception{
        //given
        underTest.setRocketship(rocketship);
        boolean b = rocketship.getAstronaut().isTrained();
        //when
        int miles = 68_000_000;
        String dest = "Mars";
        underTest.launchRocket(dest);
        //then
        assertEquals(true, b);
        verify(rocketship, times(1)).setDestination(dest, miles);
        verify(rocketship, times(1)).launch();

    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() throws Exception{
        //given
        underTest.setRocketship(rocketship);
        boolean b = rocketship.getAstronaut().isTrained();
        //when
        int miles = 68_000_000;
        String dest = "Ohio";
        underTest.launchRocket(dest);
        //then
        assertEquals(true, b);
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(dest));
        assertEquals(exceptionThrown.getMessage(), "Destination is unavailable");
    }

    @Test
    void itShouldThrowNotLoaded() throws Exception{
        //given

        //when
        //then

    }
}