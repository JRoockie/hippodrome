import myHippodrome.Hippodrome;
import myHippodrome.Horse;
import myHippodrome.Main;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HippodromeTest {
    @Test
    public void testNullCheck() {
        IllegalArgumentException exp = Assertions
                .assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exp.getMessage());

    }

    @Test
    public void testEmpty() {
        List<Horse> h = new ArrayList<>();
        IllegalArgumentException exp = Assertions.
                assertThrows(IllegalArgumentException.class, () -> new Hippodrome(h));
        assertEquals("Horses cannot be empty.", exp.getMessage());
    }

    @Test
    public void testGetHorses() {
        List<Horse> h = new ArrayList<>();
        for (int i = 0; i < 29; i++) {
            int speed = ThreadLocalRandom.current().nextInt(1, 30 + 1);
            char randomChar = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
            h.add(new Horse(String.valueOf(randomChar), speed));
        }
        assertEquals(h, new Hippodrome(h).getHorses());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    public void testMove() {

        List<Horse> h = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse s = Mockito.mock(Horse.class);
            h.add(s);
        }
        Hippodrome test = new Hippodrome(h);
        test.move();
        for (var i : h) {
            Mockito.verify(i).move();
        }
    }

    @Test
    public void testGetWinner() {
        List<Horse> h = new ArrayList<>();
        for (int i = 0; i < 29; i++) {
            int speed = ThreadLocalRandom.current().nextInt(1, 30 + 1);
            char randomChar = (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
            h.add(new Horse(String.valueOf(randomChar), speed));
        }
        Hippodrome hippodrome = new Hippodrome(h);
        Horse winner = hippodrome.getWinner();
        Horse expectedWinner = null;
        for (var i : h) {
            if (i.getDistance() > winner.getDistance()) {
                expectedWinner = i;
                assertEquals(expectedWinner, winner);
            }
        }
    }
    @Test
    @Disabled
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void testMain() throws Exception {
        Main.main(new String[]{});
    }


}
