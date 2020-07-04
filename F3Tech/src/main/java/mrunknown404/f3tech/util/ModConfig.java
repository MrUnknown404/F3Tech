package mrunknown404.f3tech.util;

import mrunknown404.f3tech.Main;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = Main.MOD_ID)
@Config.LangKey(Main.MOD_ID + ".config.title")
public class ModConfig {
	
	@Config.Comment("Where to round the sextant coord too")
	@Config.RangeInt(min = -3, max = 3)
	public static int sextant_accuracy = 0;
	
	@Config.Comment("Should the magnetic compass ignore metal blocks")
	public static boolean compass_ignore_metal;
	
	@Config.Comment("Magnetic compass metal block range")
	@Config.RangeInt(min = 1, max = 10)
	public static int compass_metal_range = 5;
	
	@Config.Comment("Metal blocks")
	public static String[] metal_blocks = new String[] { "minecraft:iron_block", "minecraft:iron_ore" };
	
	@EventBusSubscriber(modid = Main.MOD_ID)
	private static class EventHandler {
		@SubscribeEvent
		public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
			if (e.getModID().equals(Main.MOD_ID)) {
				ConfigManager.sync(Main.MOD_ID, Config.Type.INSTANCE);
			}
		}
	}
}
