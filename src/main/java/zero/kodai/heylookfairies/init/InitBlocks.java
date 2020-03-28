package zero.kodai.heylookfairies.init;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import zero.kodai.heylookfairies.blocks.BlockFairyCrust;
import zero.kodai.heylookfairies.blocks.BlockFairyHouse;
import zero.kodai.heylookfairies.blocks.BlockPortalFungus;
import zero.kodai.heylookfairies.blocks.BlockPortalMushroom;

public class InitBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FAIRY_CRUST_BLOCK = new BlockFairyCrust("block_fairy_crust", Material.CLAY, MapColor.MAGENTA);
	public static final Block PORTAL_FUNGUS = new BlockPortalFungus("block_portal_fungus", Material.GROUND, MapColor.PURPLE);
	public static final Block PORTAL_MUSHROOM = new BlockPortalMushroom("block_portal_mushroom", Material.PLANTS, MapColor.PINK);
	public static final Block FAIRY_HOUSE = new BlockFairyHouse("block_fairy_house", Material.ROCK, MapColor.BROWN);
}
