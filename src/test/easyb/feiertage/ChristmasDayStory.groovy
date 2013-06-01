package feiertage

import net.objectlab.kit.datecalc.common.HolidayHandlerType
import org.joda.time.LocalDate

before_each "initialize a calculator", {
    given "a date calculator configured in forward mode", {
        calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
    }

}
scenario "skipping christmas", {
    given "christmas day", {
        aDate = new LocalDate("2013-12-25")
        calc.setStartDate(aDate)
    }
    when "i move by 12 month", {
        result = calc.moveByMonths(12).getCurrentBusinessDate()
    }
    then "the calculated date should also skip the weekend", {
        result.shouldBe new LocalDate("2014-12-29")
    }
}