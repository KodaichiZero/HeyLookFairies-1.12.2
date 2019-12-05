package com.kodaichizero.heylookfairies.blocks;

import java.util.Random;

import javax.annotation.Nullable;

import com.kodaichizero.heylookfairies.init.InitBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalMushroom extends BlockBase implements IGrowable {

	public static final SoundType FUNGUS = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GRASS_BREAK, SoundEvents.BLOCK_GRASS_STEP, SoundEvents.BLOCK_GRASS_PLACE, SoundEvents.BLOCK_GRASS_HIT, SoundEvents.BLOCK_GRASS_FALL);
	
	protected static final AxisAlignedBB MUSHROOM_AABB = new AxisAlignedBB(0.30000001192092896D, 0.0D, 0.30000001192092896D, 0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);

	public BlockPortalMushroom(String name, Material material) {
		super(name, material);
		setSoundType(FUNGUS);
		setHardness(0.2F);
		setResistance(1F);
		setHarvestLevel("sword", 0);
		setLightLevel(0.5F);
		setLightOpacity(0);
		this.setTickRandomly(true);
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		return MUSHROOM_AABB;
	}
	
    /**
     * Checks if this block can be placed exactly at the given position.
     */
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return super.canPlaceBlockAt(worldIn, pos) && this.canBlockStay(worldIn, pos, this.getDefaultState());
    }
	
    /**
     * Return true if the block can sustain a Bush
     */
    protected boolean canSustainBush(IBlockState state) {
        return state.isFullBlock();
    }
    
	/**
	 * Portal mushrooms need to be near portal fungus to stay alive.
	 */
	public boolean canBlockStay(World worldIn, BlockPos pos, IBlockState state) {
		if(pos.getY() >= 0 && pos.getY() < 256) {
			IBlockState iblockstate = worldIn.getBlockState(pos.down());

			if(iblockstate.getBlock() == Blocks.MYCELIUM || iblockstate.getBlock() == Blocks.GRASS || (iblockstate.getBlock() == Blocks.DIRT && iblockstate.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.PODZOL)) {
				for(int x = -6; x <= 6; x++) {
					for(int y = -3; y <= 3; y++) {
						for(int z = -6; z <= 6; z++) {
							if(worldIn.getBlockState(pos.add(x, y, z)).getBlock() == InitBlocks.PORTAL_FUNGUS) {
								return true;
							}
						}
					}
				}
				
			}
		}
		
		return false;
	}
	
	/**
	 * This generates a big tall inky cap mushroom.
	 */
    public boolean generateBigMushroom(World worldIn, BlockPos pos, IBlockState state, Random rand) {
    	// TODO Will add this later.
    	return false;
    }

	@Override
	public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
        this.checkAndDropBlock(worldIn, pos, state);
    }
	
	/**
	 * Runs during world tick
	 */
	@Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        this.checkAndDropBlock(worldIn, pos, state);
    }

	/**
	 * This can make the mushroom randomly decay.
	 */
    protected void checkAndDropBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(!this.canBlockStay(worldIn, pos, state)) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
        }
    }
    
    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
        return NULL_AABB;
    }

    /**
     * Used to determine ambient occlusion and culling when rebuilding chunks for render
     */
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    /**
     * Used for something important I'm sure
     */
    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }
    
    /**
     * Makes this not have a black background.
     */
    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }
    
    /**
     * Get the geometry of the queried face at the given position and state. This is used to decide whether things like
     * buttons are allowed to be placed on the face, or how glass panes connect to the face, among other things.
     * <p>
     * Common values are {@code SOLID}, which is the default, and {@code UNDEFINED}, which represents something that
     * does not fit the other descriptions and will generally cause other things not to connect to the face.
     * 
     * @return an approximation of the form of the given face
     */
    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
        return BlockFaceShape.UNDEFINED;
    }
}
