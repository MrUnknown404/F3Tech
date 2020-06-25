package mrunknown404.randomtech.items;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemMagneticCompass extends Item {
	public ItemMagneticCompass() {
		addPropertyOverride(new ResourceLocation("angle"), new IItemPropertyGetter() {
			@SideOnly(Side.CLIENT)
			double rotation;
			@SideOnly(Side.CLIENT)
			double rota;
			@SideOnly(Side.CLIENT)
			long lastUpdateTick;
			
			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entityIn) {
				if (entityIn == null && !stack.isOnItemFrame()) {
					return 0;
				}
				
				Entity entity = entityIn != null ? entityIn : stack.getItemFrame();
				if (world == null) {
					world = entity.world;
				}
				
				double d0;
				if (world.provider.isSurfaceWorld()) {
					double d1 = entityIn != null ? (double) entity.rotationYaw : MathHelper.wrapDegrees(180 + ((EntityItemFrame) entity).facingDirection.getHorizontalIndex() * 90),
							d2 = Math.atan(-90) / (Math.PI * 2);
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
			
			@SideOnly(Side.CLIENT)
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
