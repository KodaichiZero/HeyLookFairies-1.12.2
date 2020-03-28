package zero.kodai.heylookfairies.util.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import zero.kodai.heylookfairies.blocks.container.ContainerFairyHouse;
import zero.kodai.heylookfairies.blocks.tileentities.TileEntityFairyHouse;
import zero.kodai.heylookfairies.gui.block.GuiFairyHouse;
import zero.kodai.heylookfairies.util.Reference;

public class GuiHandler implements IGuiHandler {

	public GuiHandler() {
		
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_FAIRY_HOUSE) return new ContainerFairyHouse(player.inventory, (TileEntityFairyHouse)world.getTileEntity(new BlockPos(x,y,z)), player);
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_FAIRY_HOUSE) return new GuiFairyHouse();
		return null;
	}

	public static void registerGUIs() {
		
	}
}
