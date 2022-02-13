package hibi.realtime;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SunSync {

	public static long getSyncedTime() {
		long realTime = Common.realTimeInTicks();
		for (int i = 0; i < 6; i++) {
			Sector s = sectors.get(i);
			if(realTime >= s.left && realTime < s.right) {
				return Common.convertRealToGame((long) ((realTime - s.left) * s.scale + MC_CONSTANTS[i]));
			}
		}
		return 0;
	}

	public static void load() throws Throwable {
		try(Scanner s = new Scanner(new URL("https://wttr.in/?format=%D+%S+%z+%s+%d").openStream())) {
			s.useDelimiter(" ");
			sectors = new ArrayList<Sector>();
			long left = 0, right = 0;
			String[] split;
			for (int i = 0; i < 5; i++) {
				split = s.next().split(":");
				int hourRight = Integer.parseInt(split[0]);
				int minuteRight = Integer.parseInt(split[1]);
				int secondRight = Integer.parseInt(split[2]);
				right = Common.realTimeInTicks(hourRight, minuteRight, secondRight);
				float scale = (float)(MC_CONSTANTS[i + 1] - MC_CONSTANTS[i])/(float)(right - left);
				sectors.add(new Sector(left, right, scale));
				left = right;
			}
			right = 24000l;
			float scale = (MC_CONSTANTS[6] - MC_CONSTANTS[5])/(right - left);
			sectors.add(new Sector(left, right, scale));

		}
	}

	public static final long[] MC_CONSTANTS = {
		0, 5000, 6000, 12000, 18000, 19000, 24000
	};

	private static int loadedDay = -1;
	private static List<Sector> sectors = null;

	private static class Sector {
		public long left, right;
		public float scale;
		public Sector(long left, long right, float scale) {
			this.left = left;
			this.right = right;
			this.scale = scale;
		}
	}

	private SunSync() {
	}
}
