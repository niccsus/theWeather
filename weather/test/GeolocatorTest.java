import org.junit.Test;

import static org.junit.Assert.*;

public class GeolocatorTest {

    @Test
    public void checkCity() {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=Sacramento&key=AIzaSyCVMgcmhkqfQKxbeUnJPGx6V5twYGMq-9k";
        Geolocator jUnit = new Geolocator(url, 3);
        assertTrue(jUnit.checkCity());

    }
}