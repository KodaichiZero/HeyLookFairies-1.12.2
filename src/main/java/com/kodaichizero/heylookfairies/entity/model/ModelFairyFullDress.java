package com.kodaichizero.heylookfairies.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyFullDress extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
    public ModelRenderer dressMainTop1;
    public ModelRenderer dressMainTop2;
    public ModelRenderer dressMainTop3;
    public ModelRenderer dressBelt;
    public ModelRenderer dressSkirt1;
    public ModelRenderer dressSkirt2;
    public ModelRenderer dressKnickers;
    public ModelRenderer dressLeftShoulder;
    public ModelRenderer dressLeftShoulderTrim;
    public ModelRenderer dressRightShoulder;
    public ModelRenderer dressRightShoulderTrim;
	
	public ModelFairyFullDress() {
        this.textureWidth = 32;
        this.textureHeight = 32;
    

	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
		/*
		this.hairTop.render(scale);
		*/
		
        GlStateManager.popMatrix();
    }
	
	/**
	 * Defining custom animations for the hair.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {

	}
}
