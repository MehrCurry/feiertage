package feiertage

import de.jollyday.HolidayManager
import net.objectlab.kit.datecalc.common.DateCalculator
import net.objectlab.kit.datecalc.common.DefaultHolidayCalendar
import net.objectlab.kit.datecalc.common.HolidayHandlerType
import net.objectlab.kit.datecalc.common.StandardTenor
import net.objectlab.kit.datecalc.joda.LocalDateKitCalculatorsFactory
import org.joda.time.LocalDate
/**
 * Created with IntelliJ IDEA.
 * User: Guido Zockoll
 * Date: 28.05.13
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
class Main {
    public static void main(String[] args) {
        HolidayManager manager = HolidayManager.getInstance("DE") // get UK HolidayManager

// create or get the Holidays
        def holidays = []
        def thisYear=new LocalDate().getYear()
        def firstYear = thisYear - 2
        def lastYear = thisYear + 8
        (firstYear..lastYear).each { holidays += manager.getHolidays(it)}

// fill dates into set of LocalDate
        def holidayDates = holidays.collect {it.date}.toSet()

// create the HolidayCalendar ASSUMING that the set covers 2013!
        def calendar = new DefaultHolidayCalendar<LocalDate>
        (holidayDates, new LocalDate(firstYear,1,1), new LocalDate(lastYear,1,1))

// register the holidays, any calculator with name "DE"
// asked from now on will receive an IMMUTABLE reference to this calendar
        LocalDateKitCalculatorsFactory.getDefaultInstance().registerHolidays("DE", calendar);

// ask for a LocalDate calculator for "DE"
// (even if a new set of holidays is registered, this one calculator is not affected
        DateCalculator<LocalDate> cal = LocalDateKitCalculatorsFactory.getDefaultInstance()
                .getDateCalculator("DE", HolidayHandlerType.FORWARD);

// set startDate, this will also set the current business date.
        cal.setStartDate(new LocalDate("2013-05-26"));

// startDate stays 25 May 13 BUT the currentDate has moved,
// according to Forward handler to 26 May 2013.
        LocalDate start = cal.getStartDate();   // 28 Aug 06
        LocalDate current = cal.getCurrentBusinessDate(); // 29 Aug 06

        LocalDate newCurrent = cal.moveByDays(4).getCurrentBusinessDate(); // 4 Sept 06 due to weekend!
        println(newCurrent)

// Example with Tenor, 1W with a 2 day spot lag
        LocalDate date1WeekFromSpot = cal.moveByTenor(StandardTenor.T_1W, 2).getCurrentBusinessDate();
        println(date1WeekFromSpot)
    }
}
