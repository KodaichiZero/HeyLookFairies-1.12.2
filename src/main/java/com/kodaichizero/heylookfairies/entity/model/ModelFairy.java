package com.kodaichizero.heylookfairies.entity.model;

import com.kodaichizero.heylookfairies.entity.EntityFairy;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFairy extends ModelFairyBase {
	
	//Regular Body Pieces
	public ModelRenderer head;
	public ModelRenderer leftEar;
	public ModelRenderer leftEarLobe;
	public ModelRenderer rightEar;
	public ModelRenderer rightEarLobe;
    public ModelRenderer body;
    public ModelRenderer leftArm;
    public ModelRenderer rightArm;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    
    //Wings
    public ModelRenderer leftWingMajora;
    public ModelRenderer rightWingMajora;
    public ModelRenderer leftWingMinora;
    public ModelRenderer rightWingMinora;
	
	public ModelFairy() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        //Define body pieces
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
        super.headBase.addChild(head);
        
        this.leftEar = new ModelRenderer(this, 50, 0);
        this.leftEar.addBox(-3.0F, -1.0F, -0.5F, 3, 1, 1);
        this.leftEar.setRotationPoint(-3.0F, -3.0F, 0.0F);
        this.head.addChild(leftEar);
        
        this.leftEarLobe = new ModelRenderer(this, 58, 0);
        this.leftEarLobe.addBox(-1.5F, 0F, -0.5F, 2, 1, 1);
        this.leftEar.addChild(leftEarLobe);
        
        this.rightEar = new ModelRenderer(this, 50, 0);
        this.rightEar.mirror = true;
        this.rightEar.addBox(0F, -1.0F, -0.5F, 3, 1, 1);
        this.rightEar.setRotationPoint(3.0F, -3.0F, 0.0F);
        this.head.addChild(rightEar);
        
        this.rightEarLobe = new ModelRenderer(this, 58, 0);
        this.rightEarLobe.mirror = true;
        this.rightEarLobe.addBox(-0.5F, 0F, -0.5F, 2, 1, 1);
        this.rightEar.addChild(rightEarLobe);
        
        this.body = new ModelRenderer(this, 24, 0);
        this.body.addBox(-2.0F, 0.0F, -1.0F, 4, 6, 2);
        super.bodyBase.addChild(body);
        
        this.rightArm = new ModelRenderer(this, 36, 0);
        this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
        super.rightArmBase.addChild(rightArm);
        
        this.leftArm = new ModelRenderer(this, 36, 0);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
        super.leftArmBase.addChild(leftArm);
        
        this.rightLeg = new ModelRenderer(this, 44, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        super.rightLegBase.addChild(rightLeg);
        
        this.leftLeg = new ModelRenderer(this, 44, 0);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        super.leftLegBase.addChild(leftLeg);
        
        //Wings
        this.leftWingMajora = new ModelRenderer(this, 1, 13);
        this.leftWingMajora.addBox(-9.0F, -5.0F, 0F, 9, 9, 0, 0.01F);
        this.leftWingMajora.setRotationPoint(-0.5F, 2.0F, 1.25F);
        this.bodyBase.addChild(leftWingMajora);
        
        this.leftWingMinora = new ModelRenderer(this, 20, 13);
        this.leftWingMinora.addBox(-7.0F, 0.0F, 0F, 7, 9, 0, 0.01F);
        this.leftWingMinora.setRotationPoint(-0.5F, 2.0F, 1.0F);
        this.bodyBase.addChild(leftWingMinora);
        
        this.rightWingMajora = new ModelRenderer(this, 1, 13);
        this.rightWingMajora.mirror = true;
        this.rightWingMajora.addBox(0F, -5.0F, 0F, 9, 9, 0, 0.01F);
        this.rightWingMajora.setRotationPoint(0.5F, 2.0F, 1.26F);
        this.bodyBase.addChild(rightWingMajora);
        
        this.rightWingMinora = new ModelRenderer(this, 20, 13);
        this.rightWingMinora.mirror = true;
        this.rightWingMinora.addBox(0F, 0.0F, 0F, 7, 9, 0, 0.01F);
        this.rightWingMinora.setRotationPoint(0.5F, 2.0F, 1.01F);
        this.bodyBase.addChild(rightWingMinora);
    }
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		this.head.isHidden =
		this.body.isHidden =
		this.leftArm.isHidden =
		this.rightArm.isHidden =
		this.leftLeg.isHidden = 
		this.rightLeg.isHidden = (renderStage != 0);
		
		this.leftWingMajora.isHidden =
		this.leftWingMinora.isHidden =
		this.rightWingMajora.isHidden =
		this.rightWingMinora.isHidden = (renderStage != 1);
		
		super.headBase.render(scale);
		super.bodyBase.render(scale);
		super.leftArmBase.render(scale);
		super.rightArmBase.render(scale);
    }
	
	/**
	 * Defining all of the custom animations for the fairy's body.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	
		//perform the calculations for the base animations.
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		if(!(entityIn instanceof EntityFairy)) {
    		//Why are we here if the entity isn't a fairy?!?
    		return;
    	}
		EntityFairy fairy = (EntityFairy)entityIn;
		
        
        //Wing Rotations
        if(fairy.getFlightMode()) {
        	
        	//If we are currently flying
        	float wingFlap = (float)((double)(System.currentTimeMillis() % 200L) / 200L) * (float)Math.PI * 2F;
            this.leftWingMajora.rotateAngleY = 0.35F + MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.leftWingMinora.rotateAngleY = 0.3F + MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.rightWingMajora.rotateAngleY = -0.35F + -MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.rightWingMinora.rotateAngleY = -0.3F + -MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.leftWingMajora.rotateAngleZ = 0.0F;
        	this.leftWingMinora.rotateAngleX = 0.0F;
        	this.rightWingMajora.rotateAngleZ = 0.0F;
        	this.rightWingMinora.rotateAngleX = 0.0F;
        } else if(fairy.getWingCollapseFrames() > 0) {
        	
        	//If the wings are actively collapsing
        	float amount = (float)(fairy.getWingCollapseFrames()) / 16.0F;
            this.leftWingMajora.rotateAngleY = (float)MathHelper.clampedLerp(0.4F, 0.35F + ((float)Math.PI / 7F), amount);
            this.leftWingMinora.rotateAngleY = (float)MathHelper.clampedLerp(0.0F, 0.3F + ((float)Math.PI / 7F), amount);
            this.rightWingMajora.rotateAngleY = (float)MathHelper.clampedLerp(-0.4F, -0.35F - ((float)Math.PI / 7F), amount);
            this.rightWingMinora.rotateAngleY = (float)MathHelper.clampedLerp(-0.0F, -0.3F - ((float)Math.PI / 7F), amount);
            this.leftWingMajora.rotateAngleZ = (float)MathHelper.clampedLerp(-(float)Math.PI / 2F, 0.0F, amount);
            this.leftWingMinora.rotateAngleX = (float)MathHelper.clampedLerp((float)Math.PI / 10F, 0.0F, amount);
        	this.rightWingMajora.rotateAngleZ = (float)MathHelper.clampedLerp((float)Math.PI / 2F, 0.0F, amount);
        	this.rightWingMinora.rotateAngleX = (float)MathHelper.clampedLerp((float)Math.PI / 10F, 0.0F, amount);
        } else {
        	
        	//If we are not flying.
        	this.leftWingMajora.rotateAngleY = 0.4F;
            this.leftWingMinora.rotateAngleY = 0.0F;
            this.rightWingMajora.rotateAngleY = -0.4F;
            this.rightWingMinora.rotateAngleY = -0.0F;
        	this.leftWingMajora.rotateAngleZ = -(float)Math.PI / 2F;
        	this.leftWingMinora.rotateAngleX = (float)Math.PI / 10F;
        	this.rightWingMajora.rotateAngleZ = (float)Math.PI / 2F;
        	this.rightWingMinora.rotateAngleX = (float)Math.PI / 10F;
        }
	}
}
