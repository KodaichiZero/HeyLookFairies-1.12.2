package com.kodaichizero.heylookfairies.blocks;

import java.util.List;

import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.init.InitBlocks;
import com.kodaichizero.heylookfairies.init.InitItems;
import com.kodaichizero.heylookfairies.util.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BlockBase extends Block implements IHasModel {
	
	public BlockBase(String name, Material material) {
		super(material);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		
		InitBlocks.BLOCKS.add(this);
		InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}
	
	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
	
	/**
	 * Add the ability to add custom lore.
	 */
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		String lore = Main.proxy.getBlockLore(this.getUnlocalizedName());
		if(lore != null && !lore.equals("")) {
			tooltip.add(lore);
		}
	}
}
