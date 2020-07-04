package mrunknown404.randomtech.items;

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

public class ItemBiomeChecker extends Item {
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString("Biome: " + world.getBiome(player.getPosition()).getBiomeName())));
		}
		
		return super.onItemRightClick(world, player, hand);
	}
}
