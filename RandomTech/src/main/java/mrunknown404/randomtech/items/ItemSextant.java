package mrunknown404.randomtech.items;

import mrunknown404.randomtech.util.ModConfig;
import mrunknown404.unknownlibs.utils.MathUtils;
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

public class ItemSextant extends Item {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			((EntityPlayerMP) player).connection
					.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString("X: " + getNum(player, player.posX) + " | Z: " + getNum(player, player.posZ))));
		}
		
		return super.onItemRightClick(world, player, hand);
	}
	
	private static String getNum(EntityPlayer player, double num) {
		int acc = ModConfig.sextant_accuracy;
		
		if (acc < 0) {
			int zzz = MathUtils.floor((float) num);
			String os = "", ret = ("" + zzz).substring(0, ("" + zzz).length() + acc);
			for (int i = 0; i < -acc; i++) {
				os += "0";
			}
			
			return ret.isEmpty() ? "0" : ret + os;
		} else if (acc > 0) {
			return "" + MathUtils.roundTo((float) num, acc);
		}
		
		return "" + MathUtils.floor((float) num);
	}
}
