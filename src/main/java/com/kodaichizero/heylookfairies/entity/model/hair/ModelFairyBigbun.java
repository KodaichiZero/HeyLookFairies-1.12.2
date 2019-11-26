package com.kodaichizero.heylookfairies.entity.model.hair;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyBigbun extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
    public ModelRenderer hairTop;
    public ModelRenderer hairFrontLeft;
    public ModelRenderer hairFrontMid;
    public ModelRenderer hairFrontRight;
    public ModelRenderer hairFrontRight2;
    public ModelRenderer hairLeftFront;
    public ModelRenderer hairLeftMid;
    public ModelRenderer hairLeftBack;
    public ModelRenderer hairRightFront;
    public ModelRenderer hairRightMid;
    public ModelRenderer hairRightBack;
    public ModelRenderer hairBack;
    public ModelRenderer hairBunCenter;
    public ModelRenderer hairBunFront;
    public ModelRenderer hairBunBack;
    public ModelRenderer hairBunTop;
    public ModelRenderer hairBunBottom;
	
	public ModelFairyBigbun() {
        this.textureWidth = 32;
        this.textureHeight = 32;
    
	    //Top
	    this.hairTop = new ModelRenderer(this, 0, 0);
	    this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
	    this.hairTop.setRotationPoint(0.0F, 0.0F, 0.0F);
	    
	    //Front
	    this.hairFrontLeft = new ModelRenderer(this, 0, 7);
	    this.hairFrontLeft.addBox(-3.0F, -6.0F, -4.0F, 2, 2, 1);
	    this.hairTop.addChild(hairFrontLeft);
	    
	    this.hairFrontMid = new ModelRenderer(this, 4, 7);
	    this.hairFrontMid.addBox(-2.0F, -6.0F, -4.0F, 1, 1, 1);
	    this.hairTop.addChild(hairFrontMid);
	    
	    this.hairFrontRight = new ModelRenderer(this, 8, 7);
	    this.hairFrontRight.addBox(-1.0F, -6.0F, -4.0F, 4, 2, 1);
	    this.hairTop.addChild(hairFrontRight);
	    
	    this.hairFrontRight2 = new ModelRenderer(this, 18, 7);
	    this.hairFrontRight2.addBox(-0.5F, -5.0F, -5.0F, 3, 2, 1);
	    this.hairTop.addChild(hairFrontRight2);
	    
	    //Left
	    this.hairLeftFront = new ModelRenderer(this, 0, 10);
	    this.hairLeftFront.addBox(-4.0F, -6.0F, -3.0F, 1, 3, 2);
	    this.hairTop.addChild(hairLeftFront);
	    
	    this.hairLeftMid = new ModelRenderer(this, 6, 10);
	    this.hairLeftMid.addBox(-4.0F, -6.0F, -1.0F, 1, 4, 1);
	    this.hairTop.addChild(hairLeftMid);
	    
	    this.hairLeftBack = new ModelRenderer(this, 10, 10);
	    this.hairLeftBack.addBox(-4.0F, -6.0F, 0.0F, 1, 5, 3);
	    this.hairTop.addChild(hairLeftBack);
	    
	    //Right
	    this.hairRightFront = new ModelRenderer(this, 0, 10);
	    this.hairRightFront.addBox(3.0F, -6.0F, -3.0F, 1, 3, 2);
	    this.hairTop.addChild(hairRightFront);
	    
	    this.hairRightMid = new ModelRenderer(this, 6, 10);
	    this.hairRightMid.addBox(3.0F, -6.0F, -1.0F, 1, 4, 1);
	    this.hairTop.addChild(hairRightMid);
	    
	    this.hairRightBack = new ModelRenderer(this, 10, 10);
	    this.hairRightBack.addBox(3.0F, -6.0F, 0.0F, 1, 5, 3);
	    this.hairTop.addChild(hairRightBack);
	    
	    //Back
	    this.hairBack = new ModelRenderer(this, 0, 18);
	    this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
	    this.hairTop.addChild(hairBack);
	    
	    //Bun
	    this.hairBunCenter = new ModelRenderer(this, 14, 18);
	    this.hairBunCenter.addBox(-2.5F, -3.0F, 0.0F, 5, 3, 3);
	    this.hairBunCenter.setRotationPoint(0.0F, -6.0F, 3.0F);
	    this.hairTop.addChild(hairBunCenter);
	    
	    this.hairBunFront = new ModelRenderer(this, 12, 24);
	    this.hairBunFront.addBox(-1.5F, -3.0F, -1.0F, 3, 3, 1);
	    this.hairBunCenter.addChild(hairBunFront);
	    
	    this.hairBunBack = new ModelRenderer(this, 12, 24);
	    this.hairBunBack.addBox(-1.5F, -3.0F, 3.0F, 3, 3, 1);
	    this.hairBunCenter.addChild(hairBunBack);
	    
	    this.hairBunTop = new ModelRenderer(this, 0, 24);
	    this.hairBunTop.addBox(-1.5F, -4.0F, 0.0F, 3, 1, 3);
	    this.hairBunCenter.addChild(hairBunTop);
	    
	    this.hairBunBottom = new ModelRenderer(this, 0, 24);
	    this.hairBunBottom.addBox(-1.5F, 0.0F, 0.0F, 3, 1, 3);
	    this.hairBunCenter.addChild(hairBunBottom);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
		this.hairTop.render(scale);
		
        GlStateManager.popMatrix();
    }
	
	/**
	 * Defining custom animations for the hair.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	this.hairTop.rotateAngleY = netHeadYaw * 0.017453292F;
        this.hairTop.rotateAngleX = headPitch * 0.017453292F;

        //Bun Bounce (teehee)
        if(entityIn.onGround) {
        	this.hairBunCenter.rotateAngleX = -0.1F - MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.25F;
        } else {
        	this.hairBunCenter.rotateAngleX = -0.1F - (float)entityIn.motionY;
        }
	}
}
