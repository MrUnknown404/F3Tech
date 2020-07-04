package mrunknown404.randomtech.items;

import javax.annotation.Nullable;

import mrunknown404.unknownlibs.utils.MathUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemLightDetector extends Item {
	public ItemLightDetector() {
		addPropertyOverride(new ResourceLocation("light"), new IItemPropertyGetter() {
			@Override
			public float apply(ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
				if (entity == null) {
					return 0;
				}
				
				if (world == null) {
					world = entity.world;
				}
				
				int light = world.getLight(new BlockPos(entity.posX, entity.posY, entity.posZ));
				
				return light == 0 ? 0 : MathUtils.within(light, 1, 7) ? 1 : MathUtils.within(light, 8, 14) ? 2 : 3;
			}
		});
	}
}
