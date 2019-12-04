package com.kodaichizero.heylookfairies.items;

import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.util.Blob;
import com.kodaichizero.heylookfairies.util.enumerator.EnumMagicDyeColor;

import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ItemMagicDye extends ItemBase {
	
	public ItemMagicDye(String name) {
		super(name);
		this.setHasSubtypes(true);
	}
    
    /**
     * returns a list of items with the same ID, but different meta (eg: dye returns 16 items)
     */
	@Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if(this.isInCreativeTab(tab)) {
            for(EnumMagicDyeColor color : EnumMagicDyeColor.values()) {
                items.add(new ItemStack(this, 1, color.getMetadata()));
            }
        }
    }
	
	/**
	 * Make sure to register the model for all 16 subtypes.
	 */
	@Override
	public void registerModels() {
		for(EnumMagicDyeColor color : EnumMagicDyeColor.values()) {
			Main.proxy.registerItemRenderer(this, color.getMetadata(), "inventory");
		}
	}
    
    /**
     * Get the custom name of the dye.
     */
    @Override
    public String getItemStackDisplayName(ItemStack stack) {
    	int color = stack.getMetadata();
    	return Main.proxy.getDisplayName("magicdye." + EnumMagicDyeColor.byMetadata(color).getUnlocalizedName()) + " " + super.getItemStackDisplayName(stack);
    }
    
    /**
     * Called when a Block is right-clicked with this Item
     */
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(!worldIn.isRemote && hand == EnumHand.MAIN_HAND) {
	    	ItemStack itemstack = player.getHeldItem(hand);
	        if(player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
	        	
	        	//Let's create our blob.
	        	int randSize = 6 + worldIn.rand.nextInt(6);
	        	//int randSize = 99;
	        	Blob blob = new Blob(randSize, ((float)randSize) * 0.5F, 0.125F, 0.125F);
	        	
	        	//Loop through the potential boundaries of the blob.
	        	for(int x = -(int)(randSize * 1.5F); x <= (int)(randSize * 1.5F); x++) {
	        		for(int z = -(int)(randSize * 1.5F); z <= (int)(randSize * 1.5F); z++) {
	        			
	        			//Compare the current position in our loop to the blob's boundaries.
	        			float rim = blob.comparePointToBounds(x, z);
	        			float rimSize = 1.0F + ((float)randSize * 0.15F);
	        			
	        			//If we are on the fringe, let's generate some grass.
	        			if(rim < rimSize && rim > -rimSize) {
	        				
	        				BlockPos newPos = pos.add(x, 1.0D, z);
        					int i = newPos.getX() & 15;
        					int j = newPos.getZ() & 15;
        					int id = j << 4 | i;
        					Chunk c = worldIn.getChunkFromBlockCoords(newPos);
        					byte[] blockBiomeArray = c.getBiomeArray();
        					blockBiomeArray[id] = (byte)(4 & 255);
        					c.setBiomeArray(blockBiomeArray);
	        				
	        				rim = Math.abs(rim);
	        				
	        				//Randomly determine if the grass will spawn based on 
	        				if(worldIn.rand.nextFloat() * rimSize >= rim) {
	        					
	        					BlockTallGrass.EnumType type;
	        					
	        					//Mix some ferns in with the grass.
	        					if(worldIn.rand.nextInt(10) == 0) {
	        						type = BlockTallGrass.EnumType.FERN;
	        					} else {
	        						type = BlockTallGrass.EnumType.GRASS;
	        					}
	        					
	        					//More rarely, make some tall grass.
	        					if(worldIn.rand.nextInt(16) == 0) {
	        						worldIn.setBlockState(newPos, Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.LOWER).withProperty(BlockDoublePlant.VARIANT, BlockDoublePlant.EnumPlantType.GRASS), 3);
	        				        worldIn.setBlockState(newPos.up(), Blocks.DOUBLE_PLANT.getDefaultState().withProperty(BlockDoublePlant.HALF, BlockDoublePlant.EnumBlockHalf.UPPER), 3);
	        					} else {
	        						worldIn.setBlockState(newPos, Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, type), 3);
	        					}
	        				}
	        			}
	        		}
	        	}
	        	
	        	//And now generate some mushrooms
	        	//int dist = 0;
	        	
	        	for(int i = 0; i < blob.getNumVertices(); i++) {
	        		float coords[] = blob.getVertexPos(i);
	        		
	        		
	        		IBlockState state = (worldIn.rand.nextInt(2) == 0 ? Blocks.BROWN_MUSHROOM.getDefaultState() : Blocks.RED_MUSHROOM.getDefaultState());
	        		worldIn.setBlockState(pos.add(coords[0] + 0.5D, 0.0D, coords[1] + 0.5D), Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.PODZOL), 3);
	        		worldIn.setBlockState(pos.add(coords[0] + 0.5D, 1.0D, coords[1] + 0.5D), state, 3);
	        		worldIn.setBlockState(pos.add(coords[0] + 0.5D, 2.0D, coords[1] + 0.5D), Blocks.AIR.getDefaultState(), 3);
	        		
	        		/*
	        		if(dist > 0) {
	        			dist --;
	        		} else if(worldIn.rand.nextInt(8) == 0) {
	        			dist = 8;
	        			((BlockMushroom)state.getBlock()).generateBigMushroom(worldIn, pos.add(coords[0] + 0.5D, 1.0D, coords[1] + 0.5D), state, worldIn.rand);
	        		}
	        		*/
	        	}

	        	return EnumActionResult.SUCCESS;
	        }
        }
        
        return EnumActionResult.FAIL;
    }
}
