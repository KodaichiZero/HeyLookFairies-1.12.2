package com.kodaichizero.heylookfairies.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyPigtails extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
    public ModelRenderer hairTop;
    public ModelRenderer hairFrontLeft;
    public ModelRenderer hairFrontMid;
    public ModelRenderer hairFrontRight;
    public ModelRenderer hairLeftFront;
    public ModelRenderer hairLeftBack;
    public ModelRenderer hairRightFront;
    public ModelRenderer hairRightBack;
    public ModelRenderer hairBack;
    public ModelRenderer hairPigtailLeftBig;
    public ModelRenderer hairPigtailLeftSmall;
    public ModelRenderer hairPigtailRightBig;
    public ModelRenderer hairPigtailRightSmall;
	
	public ModelFairyPigtails() {
        this.textureWidth = 32;
        this.textureHeight = 32;
    
	    //Top
	    this.hairTop = new ModelRenderer(this, 0, 16);
	    this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
	    this.hairTop.setRotationPoint(0.0F, 0.0F, 0.0F);
	    
	    //Front
	    this.hairFrontLeft = new ModelRenderer(this, 0, 0);
	    this.hairFrontLeft.addBox(-3.0F, -6.0F, -4.0F, 2, 2, 1);
	    this.hairTop.addChild(hairFrontLeft);
	    
	    this.hairFrontMid = new ModelRenderer(this, 7, 0);
	    this.hairFrontMid.addBox(-1.0F, -6.0F, -4.0F, 1, 1, 1);
	    this.hairTop.addChild(hairFrontMid);
	    
	    this.hairFrontRight = new ModelRenderer(this, 1, 0);
	    this.hairFrontRight.addBox(0.0F, -6.0F, -4.0F, 3, 2, 1);
	    this.hairTop.addChild(hairFrontRight);
	    
	    //Left
	    this.hairLeftFront = new ModelRenderer(this, 0, 3);
	    this.hairLeftFront.addBox(-4.0F, -6.0F, -3.0F, 1, 3, 3);
	    this.hairTop.addChild(hairLeftFront);
	    
	    this.hairLeftBack = new ModelRenderer(this, 8, 3);
	    this.hairLeftBack.addBox(-4.0F, -6.0F, 0.0F, 1, 4, 3);
	    this.hairTop.addChild(hairLeftBack);
	    
	    //Right
	    this.hairRightFront = new ModelRenderer(this, 0, 3);
	    this.hairRightFront.addBox(3.0F, -6.0F, -3.0F, 1, 3, 3);
	    this.hairTop.addChild(hairRightFront);
	    
	    this.hairRightBack = new ModelRenderer(this, 8, 3);
	    this.hairRightBack.addBox(3.0F, -6.0F, 0.0F, 1, 4, 3);
	    this.hairTop.addChild(hairRightBack);
	    
	    //Back
	    this.hairBack = new ModelRenderer(this, 0, 10);
	    this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
	    this.hairTop.addChild(hairBack);
	    
	    //Left Pigtail
	    this.hairPigtailLeftBig = new ModelRenderer(this, 16, 8);
	    this.hairPigtailLeftBig.addBox(-3.0F, -3.0F, -1.5F, 3, 3, 3);
	    this.hairPigtailLeftBig.setRotationPoint(-2.5F, -5.5F, 2.0F);
	    this.hairTop.addChild(hairPigtailLeftBig);
	    
	    this.hairPigtailLeftSmall = new ModelRenderer(this, 16, 3);
	    this.hairPigtailLeftSmall.addBox(-4.0F, -2.5F, -1.0F, 1, 3, 2);
	    this.hairPigtailLeftBig.addChild(hairPigtailLeftSmall);
	    
	    //Right Pigtail
	    this.hairPigtailRightBig = new ModelRenderer(this, 16, 8);
	    this.hairPigtailRightBig.addBox(0.0F, -3.0F, -1.5F, 3, 3, 3);
	    this.hairPigtailRightBig.setRotationPoint(2.5F, -5.5F, 2.0F);
	    this.hairTop.addChild(hairPigtailRightBig);
	    
	    this.hairPigtailRightSmall = new ModelRenderer(this, 16, 3);
	    this.hairPigtailRightSmall.addBox(3.0F, -2.5F, -1.0F, 1, 3, 2);
	    this.hairPigtailRightBig.addChild(hairPigtailRightSmall);
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

        //Pigtail Bounce
        if(entityIn.onGround) {
        	this.hairPigtailLeftBig.rotateAngleZ = -0.1F -MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.25F;
        	this.hairPigtailRightBig.rotateAngleZ = 0.1F + MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.25F;
        } else {
        	this.hairPigtailLeftBig.rotateAngleZ = -0.1F - (float)entityIn.motionY * 1.0F;
        	this.hairPigtailRightBig.rotateAngleZ = 0.1F + (float)entityIn.motionY * 1.0F;
        }
	}
}
