import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class UITest {

    @Test
    public void search_button_action() {
    }

    @Test
    public void get_CelsiusTemp() throws IOException {
        UI jUnit = new UI();
        double answer = jUnit.get_CelsiusTemp(100);
        assertEquals(37.7778,answer,0.01);
    }
}