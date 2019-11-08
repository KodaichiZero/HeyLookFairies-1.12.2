package com.kodaichizero.heylookfairies.util;

import net.minecraft.client.model.ModelBase;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyPigtails;
import com.kodaichizero.heylookfairies.Main;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBigbun;


public enum EnumHairStyle {
	
	PIGTAILS(0, "pigtails", new ModelFairyPigtails()),
	BIGBUN(1, "bigbun", new ModelFairyBigbun());

	private int index;
	private String name;
	private String displayName;
	private ModelBase model;
	
	private static final EnumHairStyle[] ID_LOOKUP = new EnumHairStyle[values().length];
	
	private EnumHairStyle(int index, String name, ModelBase model) {
		this.index = index;
		this.name = name;
		this.displayName = Main.proxy.getHairstyleDisplayName(name);
		this.model = model;
	}
	
	/**
	 * Get the ID of the hair style.
	 */
    public int getId() {
        return this.index;
    }
    
	/**
	 * Get the total number of available hair styles.
	 */
    public String getName() {
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
	public static EnumHairStyle getById(int id) {
		if(id < 0) {
			id = 0;
		} else if(id > values().length) {
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
        	ID_LOOKUP[enumhairstyle.getId()] = enumhairstyle;
        }
    }

	/*
	@Override
	public EnumHairStyle setRegistryName(ResourceLocation name) {
		if (getRegistryName() != null) {
            throw new IllegalStateException("Attempted to set registry name with existing registry name! New: " + name + " Old: " + getRegistryName());
		}

        this.registryName = GameData.checkPrefix(name);
        return (EnumHairStyle)this;
	}

	@Override
	public ResourceLocation getRegistryName() {
		if (delegate.name() != null) return delegate.name();
        return registryName != null ? registryName : null;
	}

	@Override
	public Class<EnumHairStyle> getRegistryType() {
		// TODO Auto-generated method stub
		return null;
	}
	*/
}