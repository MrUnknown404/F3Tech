package mrunknown404.randomtech.handlers;

import mrunknown404.randomtech.Main;
import mrunknown404.randomtech.init.ModItems;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber
public class RegistryHandler {
	
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent e) {
		for (Item item : ModItems.ITEMS) {
			Main.proxy.registerItemRenderer(item, 0, "inventory");
		}
	}
}
