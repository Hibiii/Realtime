package hibi.realtime.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import hibi.realtime.Common;
import hibi.realtime.Config;
import net.minecraft.client.world.ClientWorld;

@Mixin(ClientWorld.class)
public class ClientWorldMixin {

	@Shadow @Final
	private ClientWorld.Properties clientWorldProperties;

	@Inject(
		method = "setTimeOfDay",
		at = @At("HEAD"),
		cancellable = true
	)
	private void setTimeInject(long junk, CallbackInfo info) {
		if(Config.enabled) {
			this.clientWorldProperties.setTimeOfDay(Common.gameTimeNow());
			info.cancel();
		}
	}
}
