package feiertage

import de.jollyday.HolidayManager
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory
import org.apache.commons.lang3.time.StopWatch
import org.joda.time.Interval
import org.joda.time.LocalDate

/**
 * Created with IntelliJ IDEA.
 * User: Guido Zockoll
 * Date: 29.05.13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
class DateCalculatorService {
    static final String CALENDER_NAME = "TARGET2"
    static {
        URL url = DateCalculatorService.class.getClassLoader().getResource("target2.xml");
        HolidayManager manager = HolidayManager.getInstance(url);

        def now = new LocalDate()
        def first = now.withDayOfYear(1).minusYears(2).toDateMidnight()
        def last = now.withDayOfYear(1).plusYears(8).toDateMidnight()
        def sw = new StopWatch()

        sw.start()
        def holidayDates = manager.getHolidays(new Interval(first, last)).collect { it.date }.sort { it }
        sw.stop()
        println(sw)

        def calendar = new DefaultHolidayCalendar<LocalDate>
        (holidayDates.toSet(), first.toLocalDate(), last.toLocalDate())

        // register the holidays, any calculator with name "TARGET2"
        // asked from now on will receive an IMMUTABLE reference to this calendar
        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays(CALENDER_NAME, calendar);
    }

    static def getCalculator(String type) {
        LocalDateKitCalculatorsFactory.getDefaultInstance()
                .getDateCalculator(CALENDER_NAME, type)
    }
}
