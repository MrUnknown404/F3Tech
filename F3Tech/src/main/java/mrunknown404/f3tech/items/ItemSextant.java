package mrunknown404.f3tech.items;

import mrunknown404.f3tech.util.ModConfig;
import mrunknown404.unknownlibs.utils.MathUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSextant extends Item {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		player.setActiveHand(hand);
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack stack, World world, EntityLivingBase entity, int timeLeft) {
		if (!world.isRemote && entity instanceof EntityPlayer && getMaxItemUseDuration(stack) - timeLeft >= getMaxItemUseDuration(stack) / 3600) {
			((EntityPlayerMP) entity).connection
			.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString("X: " + getNum(entity, entity.posX) + " | Z: " + getNum(entity, entity.posZ))));
		}
	}
	
	@Override
	public int getMaxItemUseDuration(ItemStack stack) {
		return 25 * 3600;
	}
	
	@Override
	public EnumAction getItemUseAction(ItemStack stack) {
		return EnumAction.BOW;
	}
	
	private static String getNum(EntityLivingBase player, double num) {
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
