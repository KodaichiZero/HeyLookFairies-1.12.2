package zero.kodai.heylookfairies.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import zero.kodai.heylookfairies.registry.MagicDye;

@Deprecated
public class DyeUtil {

	public static ItemStack createDye(ItemStack stack, MagicDye dye) {
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setString("Dye", dye.getName());
		
		stack.setTagCompound(tag);
		
		return stack;
	}
	
	//public static String getRegistryNameFromNBT
}
