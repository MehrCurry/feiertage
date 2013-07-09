import com.google.common.base.Predicate;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 18:22
 * To change this template use File | Settings | File Templates.
 */
public class WeekendPredicate implements Predicate<DateTime> {
    @Override
    public boolean apply(DateTime date) {
        return date.getDayOfWeek()> DateTimeConstants.FRIDAY;
    }
}
