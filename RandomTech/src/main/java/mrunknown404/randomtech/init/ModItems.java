package mrunknown404.randomtech.init;

import java.util.ArrayList;
import java.util.List;

import mrunknown404.randomtech.items.ItemMagneticCompass;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ModItems {
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	public static final Item COMPASS = addItem(new ItemMagneticCompass(), "magnetic_compass");
	
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