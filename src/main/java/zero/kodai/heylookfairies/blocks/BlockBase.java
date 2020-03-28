package zero.kodai.heylookfairies.blocks;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import zero.kodai.heylookfairies.Main;
import zero.kodai.heylookfairies.init.InitBlocks;
import zero.kodai.heylookfairies.init.InitItems;
import zero.kodai.heylookfairies.util.IHasModel;

public class BlockBase extends Block implements IHasModel {
	
	public BlockBase(String name, Material material, MapColor color) {
		super(material, color);
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
		String lore = Main.proxy.getLore(this.getUnlocalizedName());
		if(lore != null && !lore.equals("")) {
			tooltip.add(lore);
		}
	}
}
