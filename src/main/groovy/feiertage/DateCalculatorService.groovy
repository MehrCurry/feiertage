package feiertage

import de.jollyday.HolidayManager
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory
import org.joda.time.LocalDate

/**
 * Created with IntelliJ IDEA.
 * User: Guido Zockoll
 * Date: 29.05.13
 * Time: 10:57
 * To change this template use File | Settings | File Templates.
 */
class DateCalculatorService {
    static final String CALENDER_NAME="TARGET2"
    static {
        URL url = DateCalculatorService.class.getClassLoader().getResource("target2.xml");
        HolidayManager manager = HolidayManager.getInstance(url);
// create or get the Holidays
        def holidays = []
        def thisYear = new LocalDate().getYear()
        def firstYear = thisYear - 2
        def lastYear = thisYear + 8
        (firstYear..lastYear).each { holidays += manager.getHolidays(it) }

// fill dates into set of LocalDate
        def holidayDates = holidays.collect { it.date }.toSet()

// create the HolidayCalendar ASSUMING that the set covers 2013!
        def calendar = new DefaultHolidayCalendar<LocalDate>
        (holidayDates, new LocalDate(firstYear, 1, 1), new LocalDate(lastYear, 1, 1))

// register the holidays, any calculator with name "DE"
// asked from now on will receive an IMMUTABLE reference to this calendar
        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays(CALENDER_NAME, calendar);

// ask for a LocalDate calculator for "DE"
// (even if a new set of holidays is registered, this one calculator is not affected

    }

    static def getCalculator(String type) {
        LocalDateKitCalculatorsFactory.getDefaultInstance()
                .getDateCalculator(CALENDER_NAME, type)
    }
}
