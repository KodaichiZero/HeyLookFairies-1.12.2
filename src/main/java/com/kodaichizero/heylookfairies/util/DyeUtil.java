package com.kodaichizero.heylookfairies.util;

import com.kodaichizero.heylookfairies.registry.MagicDye;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class DyeUtil {

	public static ItemStack createDye(ItemStack stack, MagicDye dye) {
		NBTTagCompound tag = new NBTTagCompound();
		
		tag.setString("Dye", dye.getName());
		
		stack.setTagCompound(tag);
		
		return stack;
	}
	
	//public static String getRegistryNameFromNBT
}
