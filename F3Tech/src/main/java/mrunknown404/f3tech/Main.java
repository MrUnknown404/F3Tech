package mrunknown404.f3tech;

import mrunknown404.unknownlibs.utils.ICommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Main.MOD_ID, useMetadata = true, dependencies = "required-after:unknownlibs@[1.0.4,)")
public class Main {
	public static final String MOD_ID = "f3tech";
	
	@Instance
	public static Main main;
	
	@SidedProxy(clientSide = "mrunknown404.f3tech.util.proxy.ClientProxy", serverSide = "mrunknown404.f3tech.util.proxy.ServerProxy")
	public static ICommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit();
	}
}
