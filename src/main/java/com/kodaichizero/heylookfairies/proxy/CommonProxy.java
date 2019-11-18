package com.kodaichizero.heylookfairies.proxy;

import net.minecraft.item.Item;

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
	 * Get the name of a hairstyle from the player's locale.
	 */
	public String getHairStyleDisplayName(String name) {
		return "";
	}

	/**
	 * Get the name of a magic dye color from the player's locale.
	 */
	public String getMagicDyeDisplayName(String name) {
		return "";
	}
	
	/**
	 * Get an item's custom lore.
	 */
	public String getItemLore(String name) {
		return "";
	}
	
	/**
	 * Get a block's custom lore.
	 */
	public String getBlockLore(String name) {
		return "";
	}
}
