package hibi.realtime;

import java.time.LocalDateTime;

public final class Common {
	
	/**
	 * Get the real time converted to Minecraft time in ticks.
	 */
	public static long gameTimeNow() {
		LocalDateTime now = LocalDateTime.now();
		double time = ((now.getHour() * 60) + now.getMinute()) * 60 + now.getSecond();
		time *= 24000d / 86400d;
		return (((long)time) + 18000l) % 24000;
	}

	private Common() {
	}
}
