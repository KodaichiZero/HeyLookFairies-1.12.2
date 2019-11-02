package com.kodaichizero.heylookfairies.items;

import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.init.InitItems;
import com.kodaichizero.heylookfairies.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {

	public ItemBase(String name) {
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.MISC);
		
		InitItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}

}
