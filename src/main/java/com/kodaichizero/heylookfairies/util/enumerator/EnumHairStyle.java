package com.kodaichizero.heylookfairies.util.enumerator;

import net.minecraft.client.model.ModelBase;
import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyPigtails;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBigbun;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBraidedponytail;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyNeckpillow;

public enum EnumHairStyle {
	
	PIGTAILS(0, "pigtails", new ModelFairyPigtails()),
	BIGBUN(1, "bigbun", new ModelFairyBigbun()),
	BRAIDEDPONYTAIL(2, "braidedponytail", new ModelFairyBraidedponytail()),
	NECKPILLOW(3, "neckpillow", new ModelFairyNeckpillow());

	private int meta;
	private String name;
	private String displayName;
	private ModelBase model;
	
	private static final EnumHairStyle[] ID_LOOKUP = new EnumHairStyle[values().length];
	
	private EnumHairStyle(int meta, String name, ModelBase model) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getHairStyleDisplayName(name);
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
	public static EnumHairStyle byMetadata(int id) {
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
	public ModelBase getModel() {
		return model;
	}

	/**
	 * Fill out the static arrays.
	 */
	static {
        for (EnumHairStyle enumhairstyle : values()) {
        	ID_LOOKUP[enumhairstyle.getMetadata()] = enumhairstyle;
        }
    }
}
