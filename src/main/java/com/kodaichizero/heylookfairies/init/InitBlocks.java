package com.kodaichizero.heylookfairies.init;

import java.util.ArrayList;

import com.kodaichizero.heylookfairies.blocks.BlockFairyCrust;
import com.kodaichizero.heylookfairies.blocks.BlockPortalFungus;
import com.kodaichizero.heylookfairies.blocks.BlockPortalMushroom;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FAIRY_CRUST_BLOCK = new BlockFairyCrust("block_fairy_crust", Material.CLAY);
	public static final Block PORTAL_FUNGUS = new BlockPortalFungus("block_portal_fungus", Material.GROUND);
	public static final Block PORTAL_MUSHROOM = new BlockPortalMushroom("block_portal_mushroom", Material.PLANTS);
}
