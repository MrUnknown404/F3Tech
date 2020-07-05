package mrunknown404.f3tech.init;

import java.util.ArrayList;
import java.util.List;

import mrunknown404.f3tech.items.ItemBiomeChecker;
import mrunknown404.f3tech.items.ItemCalendar;
import mrunknown404.f3tech.items.ItemDepthMeter;
import mrunknown404.f3tech.items.ItemLightDetector;
import mrunknown404.f3tech.items.ItemMagneticCompass;
import mrunknown404.f3tech.items.ItemRainChecker;
import mrunknown404.f3tech.items.ItemSextant;
import mrunknown404.f3tech.items.ItemSlimeChecker;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item MAGNETIC_COMPASS = addItem(new ItemMagneticCompass(), "magnetic_compass");
	public static final Item SEXTANT = addItem(new ItemSextant(), "sextant");
	public static final Item BIOME_CHECKER = addItem(new ItemBiomeChecker(), "biome_checker");
	public static final Item LIGHT_DETECTOR = addItem(new ItemLightDetector(), "light_detector");
	public static final Item DEPTH_METER = addItem(new ItemDepthMeter(), "depth_meter");
	public static final Item RAIN_CHECKER = addItem(new ItemRainChecker(), "rain_checker");
	public static final Item CALENDAR = addItem(new ItemCalendar(), "calendar");
	public static final Item SLIME_CHECKER = addItem(new ItemSlimeChecker(), "slime_checker");
	//stopwatch?
	
	private static Item addItem(Item item, String name) {
		return addItem(item, name, 64, ModCreativeTabs.F3TECH_ITEMS);
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
