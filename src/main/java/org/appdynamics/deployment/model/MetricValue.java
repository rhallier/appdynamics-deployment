package org.appdynamics.deployment.model;

public class MetricValue {

	private long startTimeInMillis;
	private long value;
	private long min;
	private long max;
	private long current;
	private long sum;
	private long count;
	private double standardDeviation;
	private long occurrences;
	private boolean useRange;

	public MetricValue() {
		super();
	}

	public long getStartTimeInMillis() {
		return startTimeInMillis;
	}

	public long getValue() {
		return value;
	}

	public long getMin() {
		return min;
	}

	public long getMax() {
		return max;
	}

	public long getCurrent() {
		return current;
	}

	public long getSum() {
		return sum;
	}

	public long getCount() {
		return count;
	}

	public double getStandardDeviation() {
		return standardDeviation;
	}

	public long getOccurrences() {
		return occurrences;
	}

	public boolean isUseRange() {
		return useRange;
	}

	@Override
	public String toString() {
		return "_MetricValue [startTimeInMillis=" + startTimeInMillis + ", value=" + value + ", min=" + min + ", max=" + max + ", current=" + current + ", sum=" + sum + ", count=" + count + ", standardDeviation=" + standardDeviation + ", occurrences=" + occurrences + ", useRange=" + useRange + "]";
	}

}
