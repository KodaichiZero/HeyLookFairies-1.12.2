package zero.kodai.heylookfairies.blocks;

import java.util.Random;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockPortalFungus extends BlockBase {
	
	public static final SoundType FUNGUS = new SoundType(1.0F, 1.0F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_GRAVEL_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_FALL);
	
	public BlockPortalFungus(String name, Material material, MapColor color) {
		super(name, material, color);
		setSoundType(FUNGUS);
		setHardness(0.8F);
		setResistance(5F);
		setHarvestLevel("shovel", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
	}

	//The object that gets dropped.
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(Blocks.DIRT);
    }
    
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);

        //if (rand.nextInt(3) == 0) {
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + 1.1F), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + 1.1F), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() - 0.1F), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + rand.nextFloat()), 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() + 1.1F), 0.0D, 0.0D, 0.0D);
            worldIn.spawnParticle(EnumParticleTypes.TOWN_AURA, (double)((float)pos.getX() + rand.nextFloat()), (double)((float)pos.getY() + rand.nextFloat()), (double)((float)pos.getZ() - 0.1F), 0.0D, 0.0D, 0.0D);
        //}
    }
}
