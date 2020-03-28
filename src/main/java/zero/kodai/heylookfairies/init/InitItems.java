package zero.kodai.heylookfairies.init;

import java.util.ArrayList;

import net.minecraft.item.Item;
import zero.kodai.heylookfairies.items.ItemBase;
import zero.kodai.heylookfairies.items.ItemCrematedFairyCrust;
import zero.kodai.heylookfairies.items.ItemFairyComm;
import zero.kodai.heylookfairies.items.ItemMagicDye;

public class InitItems {

	public static final ArrayList<Item> ITEMS = new ArrayList<Item>();
	
	//Tools
	public static final Item FAIRY_COMM = new ItemFairyComm("fairy_comm");
	
	//Misc
	public static final Item FAIRY_DUST = new ItemBase("fairy_dust");
	public static final Item FAIRY_CRUST = new ItemBase("fairy_crust");
	public static final Item CREMATED_FAIRY_CRUST = new ItemCrematedFairyCrust("cremated_fairy_crust");
	public static final Item MAGIC_DYE = new ItemMagicDye("magic_dye");
}
