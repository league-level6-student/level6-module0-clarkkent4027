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
    }

    @Test
    void itShouldPrepareAstronaut() {
        //given
        underTest.setRocketship(rocketship);
        //when
        underTest.prepareAstronaut(astronaut);
        //then
        verify(astronaut, times(1)).train();
        verify(rocketship, times(1)).loadOccupant(astronaut);
    }

    @Test
    void itShouldLaunchRocket(){
        //given
        astronaut = new Astronaut();
        underTest.setRocketship(rocketship);
        underTest.prepareAstronaut(astronaut);
        when(rocketship.isLoaded()).thenReturn(true);
        //when
        int miles = 68_000_000;
        String dest = "Mars";
        underTest.launchRocket(dest);
        //then
        assertEquals(true, rocketship.isLoaded());
        verify(rocketship, times(1)).setDestination(dest, miles);
        verify(rocketship, times(1)).launch();

    }


    @Test
    void itShouldThrowWhenDestinationIsUnknown() throws Exception{
        //given
        underTest.setRocketship(rocketship);
        underTest.prepareAstronaut(astronaut);
        when(rocketship.isLoaded()).thenReturn(true);
        //when
        String dest = "Ohio";
        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(dest));
        assertEquals(false, dest.equals("Mars"));
        assertEquals(exceptionThrown.getMessage(), "Destination is unavailable");
    }

    @Test
    void itShouldThrowNotLoaded() throws Exception{
        //given
        underTest.setRocketship(rocketship);
        //when
        when(rocketship.isLoaded()).thenReturn(false);
        when(astronaut.isTrained()).thenReturn(false);
        String dest = "Mars";
        //then
        Throwable exceptionThrown = assertThrows(Exception.class, () -> underTest.launchRocket(dest));
        assertEquals(exceptionThrown.getMessage(), "Rocketship is not loaded");

    }
}