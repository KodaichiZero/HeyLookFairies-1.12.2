package com.kodaichizero.heylookfairies.worldgen;

import java.util.Random;

import com.kodaichizero.heylookfairies.util.Blob;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBoulder extends WorldGenerator {

    /** 
     * The type of boulder. Usually made up of stone.
     */
    private BlockStone.EnumType boulderType;

    public WorldGenBoulder(BlockStone.EnumType stoneType) {
        super(true);
        this.boulderType = stoneType;
    }

    public WorldGenBoulder() {
        super(false);
        this.boulderType = null;
    }
	
	/**
	 * Generate a boulder.
	 */
	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		if (boulderType == null) {
			int type = rand.nextInt(32);
			switch (type) {
			case 0:
				boulderType = BlockStone.EnumType.ANDESITE;
			case 1:
				boulderType = BlockStone.EnumType.GRANITE;
			case 2:
				boulderType = BlockStone.EnumType.DIORITE;
			default:
				boulderType = BlockStone.EnumType.STONE;
			}
		}

		int i = rand.nextInt(3) + 4;

		if (rand.nextInt(12) == 0) {
			i *= 2;
		}

		boolean flag = true;

		if (position.getY() >= 1 && position.getY() + i + 1 < 256) {
			for (int j = position.getY(); j <= position.getY() + 1 + i; ++j) {
				int k = 3;

				if (j <= position.getY() + 3) {
					k = 0;
				}

				BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

				for (int l = position.getX() - k; l <= position.getX() + k && flag; ++l) {
					for (int i1 = position.getZ() - k; i1 <= position.getZ() + k && flag; ++i1) {
						if (j >= 0 && j < 256) {
							IBlockState state = worldIn.getBlockState(blockpos$mutableblockpos.setPos(l, j, i1));

							if (!state.getBlock().isAir(state, worldIn, blockpos$mutableblockpos)
									&& !state.getBlock().isLeaves(state, worldIn, blockpos$mutableblockpos)) {
								flag = false;
							}
						} else {
							flag = false;
						}
					}
				}
			}

			if (!flag) {
				return false;
			} else {
				Block block1 = worldIn.getBlockState(position.down()).getBlock();

				if (block1 != Blocks.DIRT && block1 != Blocks.GRASS) {
					return false;
				} else {
					
					//Here is where the fun begins
					float radius = 3.0F + rand.nextFloat() * 6.0F;
					Blob blob = new Blob(5 + rand.nextInt(4), radius, 0.25F, 0.25F);
					
					int yPlus = (int)(radius * 0.75F);
					int yMinus = -(int)(radius * 0.5F);
					
					for(int y = yMinus; y <= yPlus; y++) {
						for(int x = -(int)(radius * 1.5F); x <= (int)(radius * 1.5F); x++) {
							for(int z = -(int)(radius * 1.5F); z <= (int)(radius * 1.5F); z++) {
								
								float yOffset = (float)Math.abs(y > 0 ? y / 0.75F : y / 0.5F) / radius * 1.125F;
								float radiusMod = (float)Math.cos(yOffset * (float)(Math.PI / 2.0D));
								
								float rim = blob.comparePointToBoundsWithRadiusMod(x, z, radiusMod);
								if(rim < 0.0F) {
									worldIn.setBlockState(position.add(x, y, z), Blocks.STONE.getDefaultState().withProperty(BlockStone.VARIANT, boulderType), 3);
								}
							}
						}
					}
					
					return true;
				}
			}
		} else {
			return false;
		}
	}
}
