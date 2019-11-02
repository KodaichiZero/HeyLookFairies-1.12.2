package com.kodaichizero.heylookfairies.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class InitRecipes {
	
	public static void init() {
		
		//Smelting
		GameRegistry.addSmelting(InitItems.FAIRY_CRUST, new ItemStack(InitItems.CREMATED_FAIRY_CRUST, 1), 1F);
		
	}
}
