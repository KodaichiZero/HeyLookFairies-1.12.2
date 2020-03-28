package zero.kodai.heylookfairies.blocks.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import zero.kodai.heylookfairies.blocks.tileentities.TileEntityFairyHouse;

public class ContainerFairyHouse extends Container {

	private final int numRows;
	private final TileEntityFairyHouse tile;
	
	public ContainerFairyHouse(InventoryPlayer playerInv, TileEntityFairyHouse tile, EntityPlayer player) {
		
		this.tile = tile;
		this.numRows = tile.getSizeInventory() / 9;
		tile.openInventory(player);
		
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(tile, j + (i * 9), 8 + (j * 18), 18 + (i * 18)));
			}
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 9; j++) {
				this.addSlotToContainer(new Slot(playerInv, j + (i * 9), 8 + (j * 18), 175 + (i * 18)));
			}
		}
		
		for(int i = 0; i < 9; i ++) {
			this.addSlotToContainer(new Slot(playerInv, i, 8 + (i * 18), 233));
		}
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tile.isUsableByPlayer(playerIn);
	}

	@Override
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);
		tile.closeInventory(playerIn);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if(slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if(index < this.numRows * 9) {
				if(!this.mergeItemStack(itemstack1, this.numRows * 9, this.inventorySlots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if(!this.mergeItemStack(itemstack1, 0, this.numRows * 9, false)) {
				return ItemStack.EMPTY;
			}

			if(itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}
	
	public TileEntityFairyHouse getTileEntity() {
		return this.tile;
	}
}
