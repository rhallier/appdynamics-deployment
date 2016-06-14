package org.appdynamics.deployment.model;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class Timerange {
	private static DateTimeFormatter fmt = DateTimeFormat.mediumDateTime();
	
	private DateTime start;
	private DateTime end;

	public static Timerange beforeNow(int mins) {
		DateTime _end = DateTime.now();
		DateTime _start = _end.minusMinutes(mins);

		Timerange result = new Timerange();

		result.start = _start;
		result.end = _end;

		return result;
	}

	public DateTime getStart() {
		return start;
	}

	public long getStartMs() {
		return start.toDate().getTime();
	}

	public DateTime getEnd() {
		return end;
	}

	public long getEndMs() {
		return end.toDate().getTime();
	}

	@Override
	public String toString() {
		return fmt.print(start) + " - "+ fmt.print(end);
	}
	
	
}
