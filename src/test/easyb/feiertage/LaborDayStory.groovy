package feiertage

import net.objectlab.kit.datecalc.common.HolidayHandlerType
import org.joda.time.LocalDate

before_each "initialize a calculator", {
    given "a date calculator configured in forward mode", {
        calc = DateCalculatorService.getCalculator(HolidayHandlerType.FORWARD)
    }

}
scenario "skipping labor day", {
    given "a day just before labor day", {
        aDate = new LocalDate("2013-04-30")
        calc.setStartDate(aDate)
    }
    when "i move by 4 work day", {
        result = calc.moveByBusinessDays(4).getCurrentBusinessDate()
    }
    then "the calculated date should be 7 days ahead", {
        result.shouldBe new LocalDate("2013-05-07")
    }
}

scenario "labor day is on sunday", {
    def result

    given "a day just before labor day in a year where labor day is on sunday", {
        aDate = new LocalDate("2011-04-30")
        calc.setStartDate(aDate)
    }
    when "i move by 4 work day", {
        result = calc.moveByBusinessDays(4).getCurrentBusinessDate()
    }
    then "the calculated date should be 5 days ahead", {
        result.shouldBe new LocalDate("2011-05-06")
    }
}