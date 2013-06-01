package feiertage

import net.objectlab.kit.datecalc.common.HolidayHandlerType
import org.joda.time.LocalDate

before_each "initialize a calculator", {
    given "a date calculator configured in forward mode", {
        calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
    }

}

scenario "whitsun is not on target2 calendar", {
    given "friday just before whitsun", {
        aDate = new LocalDate("2013-05-17")
        calc.setStartDate(aDate)
    }
    when "i move by 4 work day", {
        result = calc.moveByBusinessDays(4).getCurrentBusinessDate()
    }
    then "the result should be the next thursday", {
        result.shouldBe new LocalDate("2013-05-23")
    }
}