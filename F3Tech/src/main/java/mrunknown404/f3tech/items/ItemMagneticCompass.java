package mrunknown404.f3tech.items;

import java.util.Arrays;

import javax.annotation.Nullable;

import mrunknown404.f3tech.util.ModConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemMagneticCompass extends Item {
	public ItemMagneticCompass() {
		addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
			double rotation, rota;
			long lastUpdateTick;
			
			@Override
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null && !stack.isOnItemFrame()) {
					return 0;
				}
				
				Entity entity = entityIn != null ? entityIn : stack.getItemFrame();
				if (world == null) {
					world = entity.world;
				}
				
				double atan = Math.atan(-90);
				if (!ModConfig.compass_ignore_metal && entity instanceof EntityPlayer) {
					int range = ModConfig.compass_metal_range;
					for (MutableBlockPos b : BlockPos.getAllInBoxMutable(entity.getPosition().add(-range, -range, -range), entity.getPosition().add(range, range, range))) {
						if (Arrays.asList(ModConfig.metal_blocks).contains(world.getBlockState(b).getBlock().getRegistryName().toString())) {
							atan = Math.atan2(b.getZ() + 0.5 - entity.posZ, b.getX() + 0.5 - entity.posX);
							break;
						}
					}
				}
				
				double d0;
				if (world.provider.isSurfaceWorld()) {
					double d1 = entityIn != null ? (double) entity.rotationYaw : MathHelper.wrapDegrees(180 + ((EntityItemFrame) entity).facingDirection.getHorizontalIndex() * 90),
							d2 = atan / (Math.PI * 2);
					d1 = MathHelper.positiveModulo(d1 / 360, 1);
					d0 = 0.5 - (d1 - 0.25 - d2);
				} else {
					d0 = Math.random();
				}
				
				if (entityIn != null) {
					d0 = wobble(world, d0);
				}
				
				return MathHelper.positiveModulo((float) d0, 1);
			}
			
			private double wobble(World world, double amount) {
				if (world.getTotalWorldTime() != lastUpdateTick) {
					lastUpdateTick = world.getTotalWorldTime();
					double d0 = MathHelper.positiveModulo(amount - rotation + 0.5, 1) - 0.5;
					rota += d0 * 0.1;
					rota *= 0.8;
					rotation = MathHelper.positiveModulo(rotation + rota, 1);
				}
				
				return rotation;
			}
		});
	}
}
