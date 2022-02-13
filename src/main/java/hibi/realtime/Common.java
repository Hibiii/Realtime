package hibi.realtime;

import java.time.LocalDateTime;

public final class Common {
	
	/**
	 * Get the real time converted to Minecraft time in ticks.
	 */
	public static long gameTimeNow() {
		return convertRealToGame(realTimeInTicks());
	}

	/**
	 * Converts a given unoffset time between 0 ~ 24000 to Minecraft's tickwise format (0 ~ 24000 - 6h)
	 */
	public static long convertRealToGame(long in) {
		return (in + 18000l) % 24000l;
	}
	/**
	 * Get the real time right now converted to Minecraft time in ticks unoffset, as a 0 ~ 24000 scale.
	 */
	public static long realTimeInTicks() {
		LocalDateTime now = LocalDateTime.now();
		return realTimeInTicks(now.getHour(), now.getMinute(), now.getSecond());
	}

	/**
	 * Converts a time into game ticks, unoffset, as a 0 ~ 24000 scale.
	 */
	public static long realTimeInTicks(int hour, int minute, int second) {
		double time = ((hour * 60) + minute) * 60 + second;
		time *= 24000d / 86400d;
		return (long)time;
	}

	private Common() {
	}
}
