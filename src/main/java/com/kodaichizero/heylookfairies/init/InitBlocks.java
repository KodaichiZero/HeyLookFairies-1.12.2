package com.kodaichizero.heylookfairies.init;

import java.util.ArrayList;

import com.kodaichizero.heylookfairies.blocks.FairyCrustBlock;
import com.kodaichizero.heylookfairies.blocks.FungusNetworkBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FAIRY_CRUST_BLOCK = new FairyCrustBlock("fairy_crust_block", Material.CLAY);
	public static final Block FUNGUS_NETWORK_BLOCK = new FungusNetworkBlock("fungus_network_block", Material.GROUND);

}
