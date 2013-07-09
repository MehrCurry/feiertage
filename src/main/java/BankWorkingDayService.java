import com.google.common.collect.FluentIterable;
import org.joda.time.DateTime;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Predicates.not;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 19:05
 * To change this template use File | Settings | File Templates.
 */
public class BankWorkingDayService {

    public DateTime getNextWorkingDay(DateTime start,int distance) {
        checkArgument(distance >= 0);
        checkArgument(distance < 100000);

        return FluentIterable.from(new DateIterable(start))
                .filter(not(new WeekendPredicate()))
                .filter(not(HolidayPredicate.GERMANY))
                .limit(1 + distance).last().get();
    }
}
