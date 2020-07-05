package mrunknown404.f3tech.items;

import java.util.Random;

import javax.annotation.Nullable;

import mrunknown404.f3tech.handlers.SlimeSeedHandler;
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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class ItemSlimeChecker extends Item {
	private static final Random RANDOM = new Random();
	
	public ItemSlimeChecker() {
		addPropertyOverride(new ResourceLocation("slime"), new IItemPropertyGetter() {
			@Override
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				if (entity == null) {
					return 0;
				}
				
				if (world == null) {
					world = entity.world;
				}
				
				return isSlimeChunk(SlimeSeedHandler.seed, new BlockPos(entity.posX, entity.posY, entity.posZ)) ? 1 : 0;
			}
		});
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		if (!world.isRemote) {
			((EntityPlayerMP) player).connection.sendPacket(new SPacketTitle(Type.ACTIONBAR, new TextComponentString(
					isSlimeChunk(world.getSeed(), new BlockPos(player.posX, player.posY, player.posZ)) ? "Currently in a slime chunk!" : "Just a normal boring chunk.")));
		}
		
		return super.onItemRightClick(world, player, hand);
	}
	
	private static boolean isSlimeChunk(long seed, BlockPos pos) {
		int x = pos.getX() >> 4;
		int z = pos.getZ() >> 4;
		RANDOM.setSeed(seed + (x * x * 4987142) + (x * 5947611) + (z * z) * 4392871L + (z * 389711) ^ 987234911L);
		return RANDOM.nextInt(10) == 0;
	}
}
