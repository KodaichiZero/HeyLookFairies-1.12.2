package zero.kodai.heylookfairies.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import zero.kodai.heylookfairies.Main;
import zero.kodai.heylookfairies.blocks.tileentities.TileEntityFairyHouse;
import zero.kodai.heylookfairies.util.Reference;

public class BlockFairyHouse extends BlockBase {
	
	public static final SoundType HOUSE = new SoundType(1.0F, 1.3F, SoundEvents.BLOCK_STONE_BREAK, SoundEvents.BLOCK_STONE_STEP, SoundEvents.BLOCK_STONE_PLACE, SoundEvents.BLOCK_STONE_HIT, SoundEvents.BLOCK_STONE_FALL);
	
	public BlockFairyHouse(String name, Material material, MapColor color) {
		super(name, material, color);
		setCreativeTab(CreativeTabs.DECORATIONS);
		setSoundType(HOUSE);
		setHardness(2.0F);
		setResistance(10.0F);
		setHarvestLevel("pickaxe", 0);
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			playerIn.openGui(Main.instance, Reference.GUI_FAIRY_HOUSE, worldIn, pos.getX(), pos.getY(), pos.getZ());
		}
		return true;
	}
	
	@Override 
	public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
		TileEntityFairyHouse tile = (TileEntityFairyHouse)worldIn.getTileEntity(pos);
		InventoryHelper.dropInventoryItems(worldIn, pos, tile);
		super.breakBlock(worldIn, pos, state);
	}

	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if(stack.hasDisplayName()) {
			TileEntity tile = worldIn.getTileEntity(pos);
			if(tile instanceof TileEntityFairyHouse) {
				((TileEntityFairyHouse)tile).setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityFairyHouse();
	}

	@SuppressWarnings("deprecation")
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}

	@Override
	public boolean isFullBlock(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	
	
	
}
