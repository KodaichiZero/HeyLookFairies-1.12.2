package com.kodaichizero.heylookfairies.worldgen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kodaichizero.heylookfairies.init.InitBlocks;
import com.kodaichizero.heylookfairies.util.Blob;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenFairyRing extends WorldGenerator {
	
	private static List<Block> worksAsAir;
	
	static {
		worksAsAir = new ArrayList<Block>();
		worksAsAir.add(Blocks.AIR);
		worksAsAir.add(Blocks.BROWN_MUSHROOM);
		worksAsAir.add(Blocks.RED_MUSHROOM);
		worksAsAir.add(Blocks.TALLGRASS);
		worksAsAir.add(Blocks.RED_FLOWER);
		worksAsAir.add(Blocks.YELLOW_FLOWER);
		worksAsAir.add(Blocks.DOUBLE_PLANT);
		worksAsAir.add(Blocks.SNOW_LAYER);
	}

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos pos) {
		int lowBounds = 3;
		int highBounds = worldIn.getHeight() - 3;
		
		if(pos.getY() >= lowBounds && pos.getY() <= highBounds) {
			
			//Let's create our blob with a random size.
			int randSize = 6 + worldIn.rand.nextInt(6);
        	Blob blob = new Blob(randSize, ((float)randSize) * 0.5F, 0.125F, 0.125F);
        	
        	//Loop through the potential boundaries of the blob.
        	for(int x = -(int)(randSize * 1.5F); x <= (int)(randSize * 1.5F); x++) {
        		for(int z = -(int)(randSize * 1.5F); z <= (int)(randSize * 1.5F); z++) {
        			
	        		//Compare the current position in our loop to the blob's boundaries.
	        		if(blob.pointWithinBounds(x, z)) {
	        			
	        			BlockPos posOffset = pos.add(x, 0, z); 
	        			
	        			//Make sure there's dirt under the grass.
	        			BlockPos posB2 = posOffset.down().down();
	        			if(!(worldIn.getBlockState(posB2).getBlock() == Blocks.DIRT)) {
	        				return false;
	        			}
	        			
	        			//The base of the fairy ring should always be either grass or podzol.
	        			BlockPos posB1 = posOffset.down();
	        			if(!(worldIn.getBlockState(posB1).getBlock() == Blocks.GRASS) && !((worldIn.getBlockState(posB1).getBlock() == Blocks.DIRT && worldIn.getBlockState(posB1).getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL))) {
	        				return false;
	        			}
	        			
	        			//The first layer must be air or an airy-type block.
	        			BlockPos pos1 = posOffset;
	        			if(!worksAsAir.contains(worldIn.getBlockState(pos1).getBlock())) {
	        				return false;
	        			}
	        			
	        			//The second layer must also be the same.
	        			BlockPos pos2 = posOffset.up();
	        			if(!worksAsAir.contains(worldIn.getBlockState(pos2).getBlock())) {
	        				return false;
	        			}
	        		}
        		}
        	}
        	
        	//If we have made it this far, we have successfully verified the spot is good for a fairy ring.
        	
        	//WOOOOO
        	
        	//Loop through the potential boundaries of the blob.
        	for(int x = -(int)(randSize * 1.5F); x <= (int)(randSize * 1.5F); x++) {
        		for(int z = -(int)(randSize * 1.5F); z <= (int)(randSize * 1.5F); z++) {
        			
        			//Compare the current position in our loop to the blob's boundaries.
        			float rim = blob.comparePointToBounds(x, z);
        			float rimSize = 1.0F + ((float)randSize * 0.15F);
        			
        			//If we are on the fringe, let's generate some grass.
        			if(rim < rimSize) {
        				
        				BlockPos posOffset = pos.add(x, 0, z);
        				
        				/* Biome altering code that I don't think I want to use right now
    					int i = newPos.getX() & 15;
    					int j = newPos.getZ() & 15;
    					int id = j << 4 | i;
    					Chunk c = worldIn.getChunkFromBlockCoords(newPos);
    					byte[] blockBiomeArray = c.getBiomeArray();
    					blockBiomeArray[id] = (byte)(4 & 255);
    					c.setBiomeArray(blockBiomeArray);
        				*/
        				
        				rim = Math.abs(rim);
        				
        				//Randomly make grass generate on the fringe.
        				if(worldIn.rand.nextFloat() * rimSize >= rim) {
        					
        					//We don't overwrite solid blocks that might be on the fringe.
        					if(worksAsAir.contains(worldIn.getBlockState(posOffset).getBlock())) {
        					
	        					BlockTallGrass.EnumType type;
	        					
	        					//Mix some ferns in with the grass.
	        					if(worldIn.rand.nextInt(10) == 0) {
	        						type = BlockTallGrass.EnumType.FERN;
	        					} else {
	        						type = BlockTallGrass.EnumType.GRASS;
	        					}
	        					
	        					//More rarely, make some tall grass.
	        					if(worldIn.rand.nextInt(16) == 0) {
	        						worldIn.setBlockState(posOffset, Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER).withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.GRASS), 3);
	        				        worldIn.setBlockState(posOffset.up(), Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 3);
	        					} else {
	        						worldIn.setBlockState(posOffset, Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, type), 3);
	        					}
        					}
        				} else {
        					
        					//If there's something other than snow present (like stray tall grass), make it air.
        					if(worldIn.getBlockState(posOffset).getBlock() != Blocks.SNOW_LAYER && worksAsAir.contains(worldIn.getBlockState(posOffset).getBlock())) {
        						worldIn.setBlockState(posOffset, Blocks.AIR.getDefaultState(), 3);
        					}
        				}
        			}
        		}
        	}
        	
        	//And now generate mushrooms on the fringe.
        	for(int i = 0; i < blob.getNumVertices(); i++) {
        		float coords[] = blob.getVertexPos(i);
        		IBlockState state = (worldIn.rand.nextInt(2) == 0 ? Blocks.BROWN_MUSHROOM.getDefaultState() : Blocks.RED_MUSHROOM.getDefaultState());
        		BlockPos posOffset = pos.add(coords[0] + 0.5D, 0.0D, coords[1] + 0.5D);
        		worldIn.setBlockState(posOffset.down(), Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 3);
        		worldIn.setBlockState(posOffset, state, 3);
        		worldIn.setBlockState(posOffset.up(), Blocks.AIR.getDefaultState(), 3);
        	}
        	
        	//FINALLY We bury the fungus network block in the center.
        	worldIn.setBlockState(pos.down().down(), InitBlocks.FUNGUS_NETWORK_BLOCK.getDefaultState(), 3);
        	
        	return true;
		}
		
		return false;
	}
}
