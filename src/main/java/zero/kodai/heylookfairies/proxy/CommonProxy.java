package zero.kodai.heylookfairies.proxy;

import net.minecraft.item.Item;
import zero.kodai.heylookfairies.util.enumerator.EnumFairyClothes;
import zero.kodai.heylookfairies.util.enumerator.EnumHairStyle;

public class CommonProxy {
	
	/**
	 * Register an item's renderer.
	 */
	public void registerItemRenderer(Item item, int meta, String id) {
	}

	/**
	 * Register all entities' renderers.
	 */
	public void registerEntityRenderers() {
	}

	/**
	 * Get the name of a magic dye color from the player's locale.
	 */
	public String getDisplayName(String name) {
		return "";
	}
	
	/**
	 * Get an item's custom lore.
	 */
	public String getLore(String name) {
		return "";
	}

	public Object getClothesModel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getHairModel(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
