package feiertage

import net.objectlab.kit.datecalc.common.HolidayHandlerType
import org.joda.time.LocalDate
import org.junit.Test

import static org.junit.Assert.*
import static org.hamcrest.core.Is.*

/**
 * Created with IntelliJ IDEA.
 * User: Guido Zockoll
 * Date: 29.05.13
 * Time: 11:05
 * To change this template use File | Settings | File Templates.
 */
class DateCalculatorServiceTest {

    @Test
    void testServiceSetup() {
        def calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
        assertThat(calc.getHolidayCalendar().getHolidays().size(), is(77))
    }

    @Test
    void normalyItShouldMoveToTheNextMonday() {
        def calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
        def thursday = new LocalDate("2013-5-30")
        calc.setStartDate(thursday)

        assertThat(calc.moveByBusinessDays(1).getCurrentBusinessDate(), is(new LocalDate("2013-05-31")))
        calc.setStartDate(thursday)
        assertThat(calc.moveByBusinessDays(2).getCurrentBusinessDate(), is(new LocalDate("2013-06-03")))
    }

    @Test
    void checkEasterHandling() {
        def calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
        def thursday = new LocalDate("2013-3-28") //Gründonnerstag
        calc.setStartDate(thursday)

        def aDate = calc.moveByBusinessDays(1).getCurrentBusinessDate()
        assertThat(aDate, is(new LocalDate("2013-04-02")))

        calc.setStartDate(thursday)
        aDate = calc.moveByBusinessDays(2).getCurrentBusinessDate()
        assertThat(aDate, is(new LocalDate("2013-04-03")))
    }
}
