import static org.hamcrest.Matchers.*;
import org.junit.Test;
import testex.DateFormatter;
import testex.JokeException;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;

public class DateformatTest {

    @Test
    public void testGetFormattedDate() {
        try {
            Calendar cal = Calendar.getInstance();
            cal.set(2004,  9, 23, 3, 34);
            Date date = cal.getTime();
            DateFormatter temp =  new DateFormatter();

            String actual = temp.getFormattedDate(date, "Europe/Paris");
            String expected = "23 okt. 2004 03:34 AM";
            assertThat(actual, comparesEqualTo(expected));
        } catch (JokeException e) {
            e.printStackTrace();
        }
    }
}
