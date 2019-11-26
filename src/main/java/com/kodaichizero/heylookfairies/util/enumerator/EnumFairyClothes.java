package com.kodaichizero.heylookfairies.util.enumerator;

import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBase;
import com.kodaichizero.heylookfairies.entity.model.clothes.ModelFairyColonialDress;
import com.kodaichizero.heylookfairies.entity.model.clothes.ModelFairyHulaGirl;
import com.kodaichizero.heylookfairies.entity.model.clothes.ModelFairyShroomyTop;
import com.kodaichizero.heylookfairies.entity.model.clothes.ModelFairyForestTunic;

public enum EnumFairyClothes {
	
	COLONIALDRESS(0, "colonialdress", new ModelFairyColonialDress()),
	HULAGIRL(1, "hulagirl", new ModelFairyHulaGirl()),
	FORESTTUNIC(2, "foresttunic", new ModelFairyForestTunic()),
	SHROOMYTOP(3, "shroomytop", new ModelFairyShroomyTop());

	private int meta;
	private String name;
	private String displayName;
	private ModelFairyBase model;
	
	private static final EnumFairyClothes[] ID_LOOKUP = new EnumFairyClothes[values().length];
	
	private EnumFairyClothes(int meta, String name, ModelFairyBase model) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getDisplayName("clothes." + name);
		this.model = model;
	}
	
	/**
	 * Get the ID of the hair style.
	 */
    public int getMetadata() {
        return this.meta;
    }
    
	/**
	 * Get the total number of available hair styles.
	 */
    public String getUnlocalizedName() {
        return this.name;
    }
    
	/**
	 * Get the ID of the hair style.
	 */
    public static int getLength() {
        return values().length;
    }
	
	/**
	 * Returns a specified hair style based on the ID parameter.
	 */
	public static EnumFairyClothes byMetadata(int id) {
		if(id < 0) {
			id = 0;
		} else if(id >= values().length) {
			id = values().length - 1;
		}
		
		return ID_LOOKUP[id];
	}

	/**
	 * Gets the name of the hair style as defined in the local translation file.
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Gets the model which will be used in the render layer.
	 */
	public ModelFairyBase getModel() {
		return model;
	}

	/**
	 * Fill out the static arrays.
	 */
	static {
        for (EnumFairyClothes enumhairstyle : values()) {
        	ID_LOOKUP[enumhairstyle.getMetadata()] = enumhairstyle;
        }
    }
}
