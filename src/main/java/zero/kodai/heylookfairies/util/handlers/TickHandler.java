package zero.kodai.heylookfairies.util.handlers;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class TickHandler {

	//Variables for the screen warp effect
	private long startTime;
	private long endTime;
	private float prevFov = 0.0F;
	private static long duration = 800L;
	private static double magnitude = 120D;
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onPlayerTick(PlayerTickEvent event) {
		if(event.player == Minecraft.getMinecraft().player) {
			
			if(event.player.collidedVertically && event.player.prevPosY > event.player.posY && event.player.isSneaking()) {
				startTime = System.currentTimeMillis();
				endTime = startTime + duration;
				prevFov = Minecraft.getMinecraft().gameSettings.fovSetting;
			}
			
			return;
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		Long currentTime = System.currentTimeMillis();
		if(currentTime < endTime) {
			
			Long elapsed = currentTime - startTime;
			float fovSetting = (float)(magnitude * (1 - Math.abs(Math.cos((elapsed * Math.PI) / duration)))) + prevFov;
			Minecraft.getMinecraft().gameSettings.fovSetting = fovSetting;
			
		} else if(prevFov > 0.0F) {
			
			startTime = 0L;
			endTime = 0L;
			prevFov = 0.0F;
			
		}
	}
}
