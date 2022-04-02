package deb.hk.date01;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class Date_Time_01 {

	public static void main(String[] args) {

		// Today's Date
		LocalDate today = LocalDate.now();
		System.out.println("Today's local date: " + today);

		System.out.println("================================");

		// Current Day, Month and year
		LocalDate today02 = LocalDate.now();
		int year = today02.getYear();
		int month = today02.getMonthValue();
		int day = today02.getDayOfMonth();

		System.out.println("Year: " + year + " , Month: " + month + " , Day: " + day);

		System.out.println("===================================");

		// Get A Particular Date
		LocalDate dateOfBirth = LocalDate.of(1993, 10, 10);
		System.out.println("Your Date Of Birth is: " + dateOfBirth);

		System.out.println("====================================");

		// check if two dates are equal
		LocalDate date01 = LocalDate.of(2022, 4, 02);
		if (date01.equals(today)) {
			System.out.println("Today " + today + " and date01 " + date01 + " are equal");
		}

		System.out.println("====================================");

		// Check For recurring Events
		LocalDate dateOfBirth01 = LocalDate.of(2010, 01, 14);
		MonthDay birthday = MonthDay.of(dateOfBirth01.getMonth(), dateOfBirth01.getDayOfMonth());
		MonthDay currentMonthDay = MonthDay.from(today);
		if (currentMonthDay.equals(birthday)) {
			System.out.println("Many Many happy returns of the day !!");
		} else {
			System.out.println("Sorry, today is not your birthday");
		}

		System.out.println("========================================");

		// Add Hours In Time
		LocalTime time = LocalTime.now();
		LocalTime newTime = time.plusHours(2); // adding two hours
		System.out.println("Time after 2 hours : " + newTime);

		System.out.println("===========================================");

		// Find Date After 1 week
		LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
		System.out.println("Today is : " + today);
		System.out.println("Date after 1 week : " + nextWeek);

		System.out.println("=============================================");

		// Date Before and After 1 year
		LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);
		System.out.println("Date before 1 year : " + previousYear);
		LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
		System.out.println("Date after 1 year : " + nextYear);

		System.out.println("=====================================");

		// Using Clock
		// Returns the current time based on your system clock and set to UTC.
		Clock clock = Clock.systemUTC();
		System.out.println("Clock : " + clock);
		// Returns time based on system clock zone
		Clock defaultClock = Clock.systemDefaultZone();
		System.out.println("Clock : " + defaultClock);

		System.out.println("====================================");

		// Check if a date is before or after another date
		LocalDate tomorrow = LocalDate.of(2014, 1, 15);
		if (tomorrow.isAfter(today)) {
			System.out.println("Tomorrow comes after today");
		}
		LocalDate yesterday = today.minus(1, ChronoUnit.DAYS);
		if (yesterday.isBefore(today)) {
			System.out.println("Yesterday is day before today");
		}

		System.out.println("======================================");

		// Date and time with timezone in Java 8
		ZoneId america = ZoneId.of("America/New_York");
		LocalDateTime localtDateAndTime = LocalDateTime.now();
		ZonedDateTime dateAndTimeInNewYork = ZonedDateTime.of(localtDateAndTime, america);
		System.out.println("Current date and time in a particular timezone : " + dateAndTimeInNewYork);

		System.out.println("====================================");

		// represent fixed date
		YearMonth currentYearMonth = YearMonth.now();
		System.out.printf("Days in month year %s: %d%n", currentYearMonth, currentYearMonth.lengthOfMonth());
		YearMonth creditCardExpiry = YearMonth.of(2018, Month.FEBRUARY);
		System.out.printf("Your credit card expires on %s %n", creditCardExpiry);

		System.out.println("=============================");

		// check leap year
		if (today.isLeapYear()) {
			System.out.println("This year is Leap year");
		} else {
			System.out.println("2014 is not a Leap year");
		}

		System.out.println("==============================");

		// how many days the month between dates
		LocalDate java8Release = LocalDate.of(2014, Month.MARCH, 14);
		Period periodToNextJavaRelease = Period.between(today, java8Release);
		System.out.println("Months left between today and Java 8 release : " + periodToNextJavaRelease.getMonths());

		System.out.println("================================");

		// Date and Time with timezone offset
		LocalDateTime datetime = LocalDateTime.of(2014, Month.JANUARY, 14, 19, 30);
		ZoneOffset offset = ZoneOffset.of("+05:30");
		OffsetDateTime date = OffsetDateTime.of(datetime, offset);
		System.out.println("Date and Time with timezone offset in Java : " + date);

		System.out.println("=============================");

		// custom formatting
		String goodFriday = "Apr 18 2014";
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
			LocalDate holiday = LocalDate.parse(goodFriday, formatter);
			System.out.printf("Successfully parsed String %s, date is %s%n", goodFriday, holiday);
		} catch (DateTimeParseException ex) {
			System.out.printf("%s is not parsable!%n", goodFriday);
			ex.printStackTrace();
		}

	}
}
