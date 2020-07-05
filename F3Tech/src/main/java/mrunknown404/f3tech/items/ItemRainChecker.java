package mrunknown404.f3tech.items;

import java.util.Locale;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;

public class ItemRainChecker extends Item {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			
			String timeLeft = "Right now!";
			
			if (!world.isRaining()) {
				WorldInfo info = world.getWorldInfo();
				float seconds = (info.getCleanWeatherTime() > 0 ? info.getCleanWeatherTime() : info.getRainTime()) / 20f;
				
				if (seconds < 60) {
					timeLeft = String.format(Locale.ENGLISH, "%.1fs", seconds);
				} else if (seconds < 3600) {
					timeLeft = String.format(Locale.ENGLISH, "%.1fm", seconds / 60);
				} else {
					timeLeft = String.format(Locale.ENGLISH, "%.1fh", seconds / 3600);
				}
			}
			
			((EntityPlayerMP) player).connection.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString("Time until next rain: " + timeLeft)));
		}
		
		return super.onItemRightClick(world, player, hand);
	}
}
