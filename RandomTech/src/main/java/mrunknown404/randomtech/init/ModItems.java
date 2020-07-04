package mrunknown404.randomtech.init;

import java.util.ArrayList;
import java.util.List;

import mrunknown404.randomtech.items.ItemBiomeChecker;
import mrunknown404.randomtech.items.ItemDepthMeter;
import mrunknown404.randomtech.items.ItemLightDetector;
import mrunknown404.randomtech.items.ItemMagneticCompass;
import mrunknown404.randomtech.items.ItemSextant;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item MAGNETIC_COMPASS = addItem(new ItemMagneticCompass(), "magnetic_compass");
	public static final Item SEXTANT = addItem(new ItemSextant(), "sextant");
	public static final Item BIOME_CHECKER = addItem(new ItemBiomeChecker(), "biome_checker");
	public static final Item LIGHT_DETECTOR = addItem(new ItemLightDetector(), "light_detector");
	public static final Item DEPTH_METER = addItem(new ItemDepthMeter(), "depth_meter");
	//speed
	
	private static Item addItem(Item item, String name) {
		return addItem(item, name, 64, ModCreativeTabs.RANDOMTECH_ITEMS);
	}
	
	private static Item addItem(Item item, String name, int stackSize, CreativeTabs tab) {
		ITEMS.add(item);
		item.setRegistryName(name);
		item.setUnlocalizedName(name);
		item.setMaxStackSize(stackSize);
		item.setCreativeTab(tab);
		return item;
	}
}
