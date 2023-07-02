import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;


public class HorseTest {

    @Test
    public void testNull() {
        IllegalArgumentException exp = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2));
        assertEquals("Name cannot be null.", exp.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void testCheckSymbol(String s) {
        IllegalArgumentException exp = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(s, 3));
        assertEquals("Name cannot be blank.", exp.getMessage());
    }

    @Test
    public void testSpeedTest() {
        IllegalArgumentException exp = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("s", -1));
        assertEquals("Speed cannot be negative.", exp.getMessage());
    }

    @Test
    public void testDistance() {
        IllegalArgumentException exp = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("sas", 5, -1));
        assertEquals("Distance cannot be negative.", exp.getMessage());
    }

    @Test
    public void testName() {
        assertEquals("s", new Horse("s", 5).getName());
    }

    @Test
    public void testSpeed() {
        assertEquals(5, new Horse("s", 5).getSpeed());
    }

    @Test
    public void testDistanceConst() {
        assertEquals(3, new Horse("g", 3, 3).getDistance());
    }
    @Test
    public void testWithoutDist(){
        assertEquals(0, new Horse("g", 3).getDistance());
    }


    @Test
   @ExtendWith(MockitoExtension.class)
    public void testMove() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("f",3,3);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void testMoveFormula() {
        Horse horse = new Horse("f",3,3);
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            double expectedValue = 0.4;
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(expectedValue);

            double distance = horse.getDistance();
            double speed = horse.getSpeed();
            distance += speed * Horse.getRandomDouble(0.2, 0.9);
            horse.move();
            assertEquals(distance, horse.getDistance(), 0.001);
        }
    }


}
