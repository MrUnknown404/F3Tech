package mrunknown404.f3tech.util.proxy;

import mrunknown404.unknownlibs.utils.ICommonProxy;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ClientProxy implements ICommonProxy {
	
	@Override
	public void preInit() {
		
	}
	
	@Override
	public void init() {
		
	}
	
	@Override
	public void postInit() {
		
	}
	
	@Override
	public void registerItemRenderer(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
}
