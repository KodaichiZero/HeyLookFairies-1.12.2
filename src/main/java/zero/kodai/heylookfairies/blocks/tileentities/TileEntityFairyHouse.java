package zero.kodai.heylookfairies.blocks.tileentities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import zero.kodai.heylookfairies.blocks.container.ContainerFairyHouse;
import zero.kodai.heylookfairies.util.Reference;

public class TileEntityFairyHouse extends TileEntityLockableLoot implements ITickable {

	private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
	public int numPlayersUsing, ticksSinceSync;
	public float lidAngle, prevLidAngle;
	
	@Override
	public int getSizeInventory() {
		return 72;
	}
	
	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.chestContents) {
			if(!stack.isEmpty()) return false;
		}
		return true;
	}
	
	@Override
	public String getName() {
		return this.hasCustomName() ? new String(this.customName) : "container.fairy_house";
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		this.chestContents = NonNullList.<ItemStack>withSize(getSizeInventory(), ItemStack.EMPTY);
		if(!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, chestContents);
		if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
		
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, chestContents);
		if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
		
		return compound;
	}

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
		return new ContainerFairyHouse(playerInventory, this, playerIn);
	}

	@Override
	public String getGuiID() {
		return Reference.MOD_ID + ":copper_chest";
	}
	
	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.chestContents;
	}

	@Override
	public void update() {
		if(!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0) {
			
			this.numPlayersUsing = 0;
			float f = 5.0F;

			for(EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class,
					new AxisAlignedBB((double) ((float) pos.getX() - f), (double) ((float) pos.getY() - f),
							(double) ((float) pos.getZ() - f), (double) ((float) (pos.getX() + 1) + f),
							(double) ((float) (pos.getY() + 1) + f), (double) ((float) (pos.getZ() + 1) + f)))) {
				if(entityplayer.openContainer instanceof ContainerFairyHouse) {
					if(((ContainerFairyHouse) entityplayer.openContainer).getTileEntity() == this) {
						this.numPlayersUsing++;
					}
				}
			}
		}

		this.prevLidAngle = this.lidAngle;
		float f1 = 0.1F;

		if(this.numPlayersUsing > 0 && this.lidAngle == 0.0F) {
			double d1 = (double) pos.getX() + 0.5D;
			double d2 = (double) pos.getZ() + 0.5D;
			this.world.playSound((EntityPlayer) null, d1, (double) pos.getY() + 0.5D, d2,
					SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F,
					this.world.rand.nextFloat() * f1 + 0.9F);
		}

		if(this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
			float f2 = this.lidAngle;

			if(this.numPlayersUsing > 0) {
				this.lidAngle += f1;
			} else {
				this.lidAngle -= f1;
			}

			if(this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f3 = 0.5F;

			if(this.lidAngle < 0.5F && f2 >= 0.5F) {
				double d3 = (double) pos.getX() + 0.5D;
				double d0 = (double) pos.getZ() + 0.5D;
				this.world.playSound((EntityPlayer) null, d3, (double) pos.getY() + 0.5D, d0,
						SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F,
						this.world.rand.nextFloat() * f1 + 0.9F);
			}

			if(this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}
	
	@Override
	public void openInventory(EntityPlayer player) {
		this.numPlayersUsing++;
		this.world.addBlockEvent(pos, getBlockType(), 1, numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}
	
	@Override
	public void closeInventory(EntityPlayer player) {
		this.numPlayersUsing--;
		this.world.addBlockEvent(pos, getBlockType(), 1, numPlayersUsing);
		this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
	}
}
