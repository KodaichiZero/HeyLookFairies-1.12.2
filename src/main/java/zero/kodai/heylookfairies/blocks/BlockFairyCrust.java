package zero.kodai.heylookfairies.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.init.SoundEvents;

public class BlockFairyCrust extends BlockBase {

	public static final SoundType CRUST = new SoundType(1.0F, 1.3F, SoundEvents.BLOCK_GRAVEL_BREAK, SoundEvents.BLOCK_GRAVEL_STEP, SoundEvents.BLOCK_GRAVEL_PLACE, SoundEvents.BLOCK_GRAVEL_HIT, SoundEvents.BLOCK_GRAVEL_FALL);
	
	public BlockFairyCrust(String name, Material material, MapColor color) {
		super(name, material, color);
		// TODO Auto-generated constructor stub
	}

}
