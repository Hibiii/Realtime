package hibi.realtime;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.TranslatableText;

public class MenuInteg implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return parent -> {
			ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(TITLE);
			ConfigEntryBuilder entryBuilder = builder.entryBuilder();
			builder.getOrCreateCategory(CAT)
				.addEntry(entryBuilder.startBooleanToggle(ENABLED, Config.enabled)
					.setDefaultValue(false)
					.setSaveConsumer(val -> Config.enabled = val)
					.build())
				.addEntry(entryBuilder.startBooleanToggle(SYNC_SUN_TIMES, Config.syncSunTimes)
					.setDefaultValue(false)
					.setSaveConsumer(val -> Config.syncSunTimes = val)
					.setTooltip(TIP_SUN_TIMES_1, TIP_SUN_TIMES_2, TIP_INTERNET)
					.build());
				return builder.build();
		};
	}

	private static final TranslatableText
		TITLE = new TranslatableText("realtime.config.title"),
		CAT = new TranslatableText("realtime.config.cat"),
		ENABLED = new TranslatableText("realtime.config.enabled"),
		SYNC_SUN_TIMES = new TranslatableText("realtime.config.sync_sun_times"),
		TIP_SUN_TIMES_1 = new TranslatableText("realtime.config.sync_sun_times.tooltip.1"),
		TIP_SUN_TIMES_2 = new TranslatableText("realtime.config.sync_sun_times.tooltip.2"),
		TIP_INTERNET = new TranslatableText("realtime.config.tooltip.internet");
}
