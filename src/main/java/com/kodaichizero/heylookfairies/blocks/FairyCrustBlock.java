package com.kodaichizero.heylookfairies.blocks;

import java.util.Random;

import com.kodaichizero.heylookfairies.init.InitItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;

public class FairyCrustBlock extends BlockBase {
	
	public static final SoundType CRUST = new SoundType(1.0F, 1.3F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_GRAVEL_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_FALL);
	
	public FairyCrustBlock(String name, Material material) {
		super(name, material);
		setSoundType(CRUST);
		setHardness(0.8F);
		setResistance(5F);
		setHarvestLevel("shovel", 0);
		setLightLevel(0.5F);
		setLightOpacity(0);
	}

    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return InitItems.FAIRY_CRUST;
    }

    //Quantity Dropped
    public int quantityDropped(Random random)
    {
        return 4;
    }
}
