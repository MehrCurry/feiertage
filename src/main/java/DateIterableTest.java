import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 18:17
 * To change this template use File | Settings | File Templates.
 */
public class DateIterableTest {

    @Test
    public void testDateIterable() {
        List<DateTime> dates=FluentIterable.from(new DateIterable(DateTime.now())).limit(10).toList();
        assertThat(dates.size(),is(10));
    }

    @Test
    public void testWeekendFilter() {
        List<DateTime> dates=FluentIterable.from(new DateIterable(DateTime.parse("2013-12-22")))
                .filter(Predicates.not(new WeekendPredicate()))
                .filter(Predicates.not(HolidayPredicate.GERMANY))
                .limit(10).toList();
        assertThat(dates.size(),is(10));
        System.out.println(dates);
    }
}
