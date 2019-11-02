package com.kodaichizero.heylookfairies;

import com.kodaichizero.heylookfairies.init.InitRecipes;
import com.kodaichizero.heylookfairies.proxy.CommonProxy;
import com.kodaichizero.heylookfairies.util.References;
import com.kodaichizero.heylookfairies.util.handlers.RegistryHandler;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;


//Hey ho let's define this mod, baby!
@Mod(modid = References.MOD_ID, name = References.NAME, version = References.VERSION)
public class Main {

	@Instance
	public static Main instance;
	
	@SidedProxy(clientSide = References.CLIENT_PROXY_CLASS, serverSide = References.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@EventHandler
	public static void PreInit(FMLPreInitializationEvent event)
	{
		RegistryHandler.preInitRegistries();
	}
	
	@EventHandler
	public static void init(FMLInitializationEvent event)
	{
		InitRecipes.init();
	}
	
	@EventHandler
	public static void Postinit(FMLPostInitializationEvent event)
	{
		
	}
}