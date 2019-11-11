package com.kodaichizero.heylookfairies.entity.render;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairy;
import com.kodaichizero.heylookfairies.entity.render.layer.LayerFairyHair;
import com.kodaichizero.heylookfairies.util.EnumHairStyle;
import com.kodaichizero.heylookfairies.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFairy extends RenderLiving<EntityFairy> {
	
	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID + ":textures/entities/fairy.png");
	
	public RenderFairy(RenderManager manager) {
		super(manager, new ModelFairy(), 0.2F);
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.PIGTAILS));
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.BIGBUN));
		this.addLayer(new LayerFairyHair(this, EnumHairStyle.BRAIDEDPONYTAIL));
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFairy entity) {
		return TEXTURE;
	}
}
