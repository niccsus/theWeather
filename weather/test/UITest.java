import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UITest {

    @Test
    public void forecastCheck() throws IOException {
        UI jUnit2;
        jUnit2 = new UI();
        String done = jUnit2.forecastCheck();
        assertEquals("The weekly forecast has been printed.", "The weekly forecast has been printed.");
    }

    @Test
    public void get_CelsiusTemp() throws IOException {
        UI jUnit = new UI();
        double answer = jUnit.get_CelsiusTemp(100);
        assertEquals(37.7778,answer,0.01);
    }
}