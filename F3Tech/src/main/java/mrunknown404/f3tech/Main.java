package mrunknown404.f3tech;

import mrunknown404.f3tech.handlers.SlimeSeedHandler;
import mrunknown404.f3tech.network.message.MessageSlimeSeed;
import mrunknown404.unknownlibs.utils.ICommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = Main.MOD_ID, useMetadata = true, dependencies = "required-after:unknownlibs@[1.0.4,)")
public class Main {
	public static final String MOD_ID = "f3tech";
	
	@Instance
	public static Main main;
	
	public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(MOD_ID);
	
	@SidedProxy(clientSide = "mrunknown404.f3tech.util.proxy.ClientProxy", serverSide = "mrunknown404.f3tech.util.proxy.ServerProxy")
	public static ICommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit();
		
		MinecraftForge.EVENT_BUS.register(new SlimeSeedHandler());
	}
	
	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init();
		
		NETWORK.registerMessage(MessageSlimeSeed.class, MessageSlimeSeed.class, 0, Side.CLIENT);
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit();
	}
}
