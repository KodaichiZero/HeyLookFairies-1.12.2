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
		//float expandAmount = 0;
		float verticalOffset = 0F;
        this.textureWidth = 64;
        this.textureHeight = 32;
    
	    //Top
	    this.hairTop = new ModelRenderer(this, 24, 8);
	    this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
	    this.hairTop.setRotationPoint(0.0F, 0.0F + verticalOffset, 0.0F);
	    
	    //Front
	    this.hairFrontLeft = new ModelRenderer(this, 0, 12);
	    this.hairFrontLeft.addBox(-3.0F, -6.0F, -4.0F, 2, 2, 1);
	    this.hairTop.addChild(hairFrontLeft);
	    
	    this.hairFrontMid = new ModelRenderer(this, 7, 12);
	    this.hairFrontMid.addBox(-1.0F, -6.0F, -4.0F, 1, 1, 1);
	    this.hairTop.addChild(hairFrontMid);
	    
	    this.hairFrontRight = new ModelRenderer(this, 1, 12);
	    this.hairFrontRight.addBox(0.0F, -6.0F, -4.0F, 3, 2, 1);
	    this.hairTop.addChild(hairFrontRight);
	    
	    //Left
	    this.hairLeftFront = new ModelRenderer(this, 0, 15);
	    this.hairLeftFront.addBox(-4.0F, -6.0F, -3.0F, 1, 3, 3);
	    this.hairTop.addChild(hairLeftFront);
	    
	    this.hairLeftBack = new ModelRenderer(this, 8, 15);
	    this.hairLeftBack.addBox(-4.0F, -6.0F, 0.0F, 1, 4, 3);
	    this.hairTop.addChild(hairLeftBack);
	    
	    //Right
	    this.hairRightFront = new ModelRenderer(this, 0, 15);
	    this.hairRightFront.addBox(3.0F, -6.0F, -3.0F, 1, 3, 3);
	    this.hairTop.addChild(hairRightFront);
	    
	    this.hairRightBack = new ModelRenderer(this, 8, 15);
	    this.hairRightBack.addBox(3.0F, -6.0F, 0.0F, 1, 4, 3);
	    this.hairTop.addChild(hairRightBack);
	    
	    //Back
	    this.hairBack = new ModelRenderer(this, 0, 22);
	    this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
	    this.hairTop.addChild(hairBack);
	    
	    //Left Pigtail
	    this.hairPigtailLeftBig = new ModelRenderer(this, 16, 20);
	    this.hairPigtailLeftBig.addBox(-3.0F, -3.0F, -1.5F, 3, 3, 3);
	    this.hairPigtailLeftBig.setRotationPoint(-2.5F, -5.5F, 2F);
	    this.hairTop.addChild(hairPigtailLeftBig);
	    
	    this.hairPigtailLeftSmall = new ModelRenderer(this, 16, 15);
	    this.hairPigtailLeftSmall.addBox(-3.5F, -2.5F, -1F, 1, 3, 2);
	    this.hairPigtailLeftBig.addChild(hairPigtailLeftSmall);
	    
	    //Right Pigtail
	    this.hairPigtailRightBig = new ModelRenderer(this, 16, 20);
	    this.hairPigtailRightBig.addBox(0F, -3.0F, -1.5F, 3, 3, 3);
	    this.hairPigtailRightBig.setRotationPoint(2.5F, -5.5F, 2F);
	    this.hairTop.addChild(hairPigtailRightBig);
	    
	    this.hairPigtailRightSmall = new ModelRenderer(this, 16, 15);
	    this.hairPigtailRightSmall.addBox(2.5F, -2.5F, -1F, 1, 3, 2);
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
	 * Defining custom animations for ther hair.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	this.hairTop.rotateAngleY = netHeadYaw * 0.017453292F;
        this.hairTop.rotateAngleX = headPitch * 0.017453292F;

        //Pigtail Bounce
        if(entityIn.onGround) {
        	this.hairPigtailLeftBig.rotateAngleZ = -MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.25F;
        	this.hairPigtailRightBig.rotateAngleZ = MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.25F;
        } else {
        	this.hairPigtailLeftBig.rotateAngleZ = -(float)entityIn.motionY * 1F;
        	this.hairPigtailRightBig.rotateAngleZ = (float)entityIn.motionY * 1F;
        }
	}
}
