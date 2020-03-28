package zero.kodai.heylookfairies.util.enumerator;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import zero.kodai.heylookfairies.Main;

public enum EnumFairyClothes {
	
	COLONIALDRESS(0, "colonialdress"),
	HULAGIRL(1, "hulagirl"),
	FORESTTUNIC(2, "foresttunic"),
	SHROOMYTOP(3, "shroomytop");

	private int meta;
	private String name;
	private String displayName;
	private Object model;
	
	private static final EnumFairyClothes[] ID_LOOKUP = new EnumFairyClothes[values().length];
	
	private EnumFairyClothes(int meta, String name) {
		this.meta = meta;
		this.name = name;
		this.displayName = Main.proxy.getDisplayName("clothes." + name);
		this.model = Main.proxy.getClothesModel(meta);
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
	@SideOnly(Side.CLIENT)
	public Object getModel() {
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
