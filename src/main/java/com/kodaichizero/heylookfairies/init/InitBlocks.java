package com.kodaichizero.heylookfairies.init;

import java.util.ArrayList;

import com.kodaichizero.heylookfairies.blocks.FairyCrustBlock;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class InitBlocks {
	
	public static final ArrayList<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block FAIRY_CRUST_BLOCK = new FairyCrustBlock("fairy_crust_block", Material.CLAY);

}
