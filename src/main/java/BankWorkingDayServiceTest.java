import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class BankWorkingDayServiceTest {
    @Test
    public void testGetNextWorkingDay() throws Exception {
        BankWorkingDayService service=new BankWorkingDayService();

        DateTime start= DateTime.parse("2013-12-20");
        assertThat(service.getNextWorkingDay(start, 0), is(start));
        assertThat(service.getNextWorkingDay(start, 1), is(start.plusDays(3)));
        assertThat(service.getNextWorkingDay(start, 2), is(start.plusDays(4)));
        assertThat(service.getNextWorkingDay(start, 3), is(start.plusDays(7)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeDistance() {
        BankWorkingDayService service=new BankWorkingDayService();

        DateTime start= DateTime.parse("2013-12-20");
        service.getNextWorkingDay(start,-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReallyLongDistance() {
        BankWorkingDayService service=new BankWorkingDayService();

        DateTime start= DateTime.parse("2013-12-20");
        service.getNextWorkingDay(start,999999);
    }

    @Test
    public void testMoreThan100Years() {
        BankWorkingDayService service=new BankWorkingDayService();

        DateTime start= DateTime.parse("2013-12-20");
        DateTime nextWorkingDay = service.getNextWorkingDay(start, 99999);
        assertThat(nextWorkingDay, is(DateTime.parse("2408-09-26")));
    }
}
