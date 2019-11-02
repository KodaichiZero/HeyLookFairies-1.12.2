package com.kodaichizero.heylookfairies.init;

import java.util.ArrayList;

import com.kodaichizero.heylookfairies.items.ItemBase;
import com.kodaichizero.heylookfairies.items.misc.CrematedFairyCrust;

import net.minecraft.item.Item;

public class InitItems {

	public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	//Tools
	public static final Item FAIRY_COMM = new ItemBase("fairy_comm");
	
	//Misc
	public static final Item FAIRY_DUST = new ItemBase("fairy_dust");
	public static final Item FAIRY_CRUST = new ItemBase("fairy_crust");
	public static final Item CREMATED_FAIRY_CRUST = new CrematedFairyCrust("cremated_fairy_crust");

}
