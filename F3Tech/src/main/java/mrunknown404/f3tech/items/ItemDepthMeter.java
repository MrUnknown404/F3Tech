package mrunknown404.f3tech.items;

import javax.annotation.Nullable;

import mrunknown404.unknownlibs.utils.MathUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketTitle;
import net.minecraft.network.play.server.SPacketTitle.Type;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemDepthMeter extends Item {
	public ItemDepthMeter() {
		addPropertyOverride(new ResourceLocation("depth"), new IItemPropertyGetter() {
			@Override
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				if (entity == null) {
					return 0;
				}
				
				if (world == null) {
					world = entity.world;
				}
				
				int yy = MathUtils.floor((float) entity.posY);
				if (yy < 0) {
					return -1;
				}
				
				String ret = ("" + yy).substring(0, ("" + yy).length() - 1);
				if (ret.isEmpty()) {
					return 0;
				}
				
				int math = Integer.parseInt(ret) * 10;
				return math < 260 ? math : -1;
			}
		});
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString("Y: " + MathUtils.floor((float) player.posY))));
		}
		
		return super.onItemRightClick(world, player, hand);
	}
}
