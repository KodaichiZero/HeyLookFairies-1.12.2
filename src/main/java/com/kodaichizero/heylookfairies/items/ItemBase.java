package com.kodaichizero.heylookfairies.items;

import java.util.List;

import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.init.InitItems;
import com.kodaichizero.heylookfairies.util.IHasModel;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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

	/**
	 * Add the ability to add custom lore.
	 */
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String lore = Main.proxy.getItemLore(this.getUnlocalizedName());
		if(lore != null && !lore.equals("")) {
			tooltip.add(lore);
		}
	}
}
