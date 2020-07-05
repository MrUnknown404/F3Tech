package mrunknown404.f3tech.handlers;

import mrunknown404.f3tech.Main;
import mrunknown404.f3tech.network.message.MessageSlimeSeed;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;

public class SlimeSeedHandler {
	public static long seed;
	
	@SubscribeEvent
	public void onPlayerLogin(PlayerLoggedInEvent e) {
		if (e.player instanceof EntityPlayerMP) {
			Main.NETWORK.sendTo(new MessageSlimeSeed(e.player.world.getSeed()), (EntityPlayerMP) e.player);
		}
	}
}
