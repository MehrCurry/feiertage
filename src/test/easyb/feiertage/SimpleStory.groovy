package feiertage

import net.objectlab.kit.datecalc.common.HolidayHandlerType
import org.joda.time.LocalDate

/**
 * Created with IntelliJ IDEA.
 * User: Guido Zockoll
 * Date: 29.05.13
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
/**

 * A simple scenario

 */

scenario "skipping a normal weekend", {
    def result

    given "a date representing a thursday", {
        aDate=new LocalDate("2013-03-28")
    }
    and "a new datecalculor initialized with that startDate",{
        calc=DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
        calc.setStartDate(aDate)

    }

    when "i get a workingday 2 days ahead", {
        result=calc.moveByBusinessDays(2).getCurrentBusinessDate()
    }

    then "it should be the next monday", {
        result.shouldBe new LocalDate("2013-04-03")

    }
}
