import com.google.common.base.Predicate;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;
import org.joda.time.DateTime;

/**
 * Created with IntelliJ IDEA.
 * User: guido
 * Date: 09.07.13
 * Time: 18:34
 * To change this template use File | Settings | File Templates.
 */
public class HolidayPredicate implements Predicate<DateTime> {
    public static final HolidayPredicate GERMANY=new HolidayPredicate(HolidayManager.getInstance(HolidayCalendar.GERMANY));
    private HolidayManager m;

    public HolidayPredicate(HolidayManager m) {
        this.m = m;
    }

    @Override
    public boolean apply(DateTime dateTime) {
        return m.isHoliday(dateTime.toLocalDate());
    }
}
