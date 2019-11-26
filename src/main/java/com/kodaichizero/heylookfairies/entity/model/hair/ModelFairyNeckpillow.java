package com.kodaichizero.heylookfairies.entity.model.hair;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyNeckpillow extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
    public ModelRenderer hairTop;
    public ModelRenderer hairFront;
    public ModelRenderer hairFrontLeft;
    public ModelRenderer hairFrontRight;
    public ModelRenderer hairLeftFront;
    public ModelRenderer hairLeftMid;
    public ModelRenderer hairLeftBack;
    public ModelRenderer hairRightFront;
    public ModelRenderer hairRightMid;
    public ModelRenderer hairRightBack;
    public ModelRenderer hairBack;
    public ModelRenderer hairCurl;
    public ModelRenderer hairCurlLeft;
    public ModelRenderer hairCurlRight;
	
	public ModelFairyNeckpillow() {
        this.textureWidth = 32;
        this.textureHeight = 32;
    
	    //Top
	    this.hairTop = new ModelRenderer(this, 0, 0);
	    this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
	    this.hairTop.setRotationPoint(0.0F, 0.0F, 0.0F);
	    
	    //Front
	    this.hairFront = new ModelRenderer(this, 18, 0);
	    this.hairFront.addBox(-3.0F, -6.0F, -4.0F, 6, 2, 1);
	    this.hairTop.addChild(hairFront);
	    
	    this.hairFrontLeft = new ModelRenderer(this, 0, 0);
	    this.hairFrontLeft.addBox(-3.0F, -5.0F, -5.0F, 2, 1, 1);
	    this.hairTop.addChild(hairFrontLeft);
	    
	    this.hairFrontRight = new ModelRenderer(this, 0, 2);
	    this.hairFrontRight.addBox(1.0F, -5.0F, -5.0F, 2, 1, 1);
	    this.hairTop.addChild(hairFrontRight);
	    
	    //Left
	    this.hairLeftFront = new ModelRenderer(this, 0, 7);
	    this.hairLeftFront.addBox(-4.0F, -6.0F, -3.0F, 1, 2, 2);
	    this.hairTop.addChild(hairLeftFront);
	    
	    this.hairLeftMid = new ModelRenderer(this, 6, 7);
	    this.hairLeftMid.addBox(-4.0F, -6.0F, -1.0F, 1, 4, 1);
	    this.hairTop.addChild(hairLeftMid);
	    
	    this.hairLeftBack = new ModelRenderer(this, 10, 7);
	    this.hairLeftBack.addBox(-4.0F, -6.0F, 0.0F, 1, 5, 3);
	    this.hairTop.addChild(hairLeftBack);
	    
	    //Right
	    this.hairRightFront = new ModelRenderer(this, 0, 7);
	    this.hairRightFront.addBox(3.0F, -6.0F, -3.0F, 1, 2, 2);
	    this.hairTop.addChild(hairRightFront);
	    
	    this.hairRightMid = new ModelRenderer(this, 6, 7);
	    this.hairRightMid.addBox(3.0F, -6.0F, -1.0F, 1, 4, 1);
	    this.hairTop.addChild(hairRightMid);
	    
	    this.hairRightBack = new ModelRenderer(this, 10, 7);
	    this.hairRightBack.addBox(3.0F, -6.0F, 0.0F, 1, 5, 3);
	    this.hairTop.addChild(hairRightBack);
	    
	    //Back
	    this.hairBack = new ModelRenderer(this, 18, 7);
	    this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
	    this.hairTop.addChild(hairBack);
	    
	    //Hair Curl
	    this.hairCurl = new ModelRenderer(this, 14, 15);
	    this.hairCurl.addBox(-2.5F, 0.0F, 0.0F, 5, 3, 3);
	    this.hairCurl.setRotationPoint(0.0F, -2.0F, 2.5F);
	    this.hairTop.addChild(hairCurl);
	    
	    this.hairCurlLeft  = new ModelRenderer(this, 0, 15);
	    this.hairCurlLeft.addBox(-4.0F, 0.0F, -3.0F, 4, 3, 3);
	    this.hairCurlLeft.setRotationPoint(-2.5F, 0.0F, 3.0F);
	    this.hairCurl.addChild(hairCurlLeft);
	    
	    this.hairCurlRight  = new ModelRenderer(this, 0, 21);
	    this.hairCurlRight.addBox(0.0F, 0.0F, -3.0F, 4, 3, 3);
	    this.hairCurlRight.setRotationPoint(2.5F, 0.0F, 3.0F);
	    this.hairCurl.addChild(hairCurlRight);

	    //Permanent rotations.
        this.hairCurlLeft.rotateAngleY = -(float)Math.PI / 8F;
        this.hairCurlRight.rotateAngleY = (float)Math.PI / 8F;
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

        //Neck Pillow Bouncing
        this.hairCurl.rotationPointY = -2.0F;
        if(entityIn.onGround) {
        	this.hairCurl.rotateAngleX = -MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.5F;
        	this.hairCurl.rotationPointY += MathHelper.cos(limbSwing * 2.0F * 0.6662F + ((float)Math.PI / 2.0F)) * limbSwingAmount * 0.5F;
        } else {
        	this.hairCurl.rotateAngleX = -(float)entityIn.motionY * 0.75F;
        	this.hairCurl.rotationPointY += (float)entityIn.motionY * 1.5F;
        }
	}
}
