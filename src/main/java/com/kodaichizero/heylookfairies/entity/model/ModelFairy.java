package com.kodaichizero.heylookfairies.entity.model;

import com.kodaichizero.heylookfairies.entity.EntityFairy;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelFairy extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
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
        this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
        
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
        this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
        
        this.rightArm = new ModelRenderer(this, 36, 0);
        this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
        this.rightArm.setRotationPoint(-3.0F, 1.0F, 0.0F);
        
        this.leftArm = new ModelRenderer(this, 36, 0);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
        this.leftArm.setRotationPoint(3.0F, 1.0F, 0.0F);
        
        this.rightLeg = new ModelRenderer(this, 44, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        this.rightLeg.setRotationPoint(-1.0F, 6.0F, 0.0F);
        this.body.addChild(rightLeg);
        
        this.leftLeg = new ModelRenderer(this, 44, 0);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2);
        this.leftLeg.setRotationPoint(1.0F, 6.0F, 0.0F);
        this.body.addChild(leftLeg);
     
        
        //Wings
        this.leftWingMajora = new ModelRenderer(this, 1, 13);
        this.leftWingMajora.addBox(-9.0F, -5.0F, 0F, 9, 9, 0, 0.01F);
        this.leftWingMajora.setRotationPoint(-0.5F, 2.0F, 1.25F);
        this.body.addChild(leftWingMajora);
        
        this.leftWingMinora = new ModelRenderer(this, 20, 13);
        this.leftWingMinora.addBox(-7.0F, 0.0F, 0F, 7, 9, 0, 0.01F);
        this.leftWingMinora.setRotationPoint(-0.5F, 2.0F, 1.0F);
        this.body.addChild(leftWingMinora);
        
        this.rightWingMajora = new ModelRenderer(this, 1, 13);
        this.rightWingMajora.mirror = true;
        this.rightWingMajora.addBox(0F, -5.0F, 0F, 9, 9, 0, 0.01F);
        this.rightWingMajora.setRotationPoint(0.5F, 2.0F, 1.26F);
        this.body.addChild(rightWingMajora);
        
        this.rightWingMinora = new ModelRenderer(this, 20, 13);
        this.rightWingMinora.mirror = true;
        this.rightWingMinora.addBox(0F, 0.0F, 0F, 7, 9, 0, 0.01F);
        this.rightWingMinora.setRotationPoint(0.5F, 2.0F, 1.01F);
        this.body.addChild(rightWingMinora);
    }
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
		this.head.render(scale);
		this.body.render(scale);
        this.rightArm.render(scale);
        this.leftArm.render(scale);
		
        GlStateManager.popMatrix();
    }
	
	/**
	 * Defining all of the custom animations for the fairy's body.
	 */
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	if(!(entityIn instanceof EntityFairy)) {
    		//Why are we here if the entity isn't a fairy?!?
    		return;
    	}
		EntityFairy fairy = (EntityFairy)entityIn;
		
		//Head Look Stuff.
		this.head.rotateAngleY = netHeadYaw * 0.017453292F;
		this.head.rotateAngleX = headPitch * 0.017453292F;
        
        //Reset Limb Rotations
        this.rightArm.rotateAngleX = 0.0F;
        this.leftArm.rotateAngleX = 0.0F;
        this.rightArm.rotateAngleY = 0.0F;
        this.leftArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = 0.0F;
        this.leftLeg.rotateAngleX = 0.0F;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;
        
        //Limb swinging during walking.
        if(!fairy.getFlightMode()) {
	        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
	        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
	        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        }

        //Check if the fairy is riding on someone's shoulder.
        boolean shoulderRiding = false;
        if(fairy.isShoulderRiding() ) {
        	shoulderRiding = true;
        }
        
        //Show the sitting animation.
        if(this.isRiding || shoulderRiding) {
            this.rightArm.rotateAngleX = -((float)Math.PI / 5F);
            this.leftArm.rotateAngleX = -((float)Math.PI / 5F);
            this.rightLeg.rotateAngleX = -1.4137167F;
            this.rightLeg.rotateAngleY = ((float)Math.PI / 10F);
            this.rightLeg.rotateAngleZ = 0.07853982F;
            this.leftLeg.rotateAngleX = -1.4137167F;
            this.leftLeg.rotateAngleY = -((float)Math.PI / 10F);
            this.leftLeg.rotateAngleZ = -0.07853982F;
        }

        //Arm Swinging Stuff.
        this.body.rotateAngleY = 0.0F;
        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.body.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT)
            {
                this.body.rotateAngleY *= -1.0F;
            }

            this.rightArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleY += this.body.rotateAngleY;
            this.leftArm.rotateAngleX += this.body.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.head.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.body.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        //Flight Mode Body and limb rotation stuff
        if(fairy.getFlightMode()) {
        	this.body.rotateAngleX = (float)Math.PI / 3F;
        	this.leftLeg.rotateAngleX -= (float)Math.PI / 4F;
        	this.rightLeg.rotateAngleX -= (float)Math.PI / 4F;
        	
        	this.leftLeg.rotateAngleZ = -0.15F;
        	this.rightLeg.rotateAngleZ = 0.15F;
        	
        	//Adjust rotations according to move speed
        	this.body.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	this.leftLeg.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	this.rightLeg.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	
        	this.leftArm.rotateAngleX += ((float)Math.PI / 4F) * limbSwingAmount;
        	this.rightArm.rotateAngleX += ((float)Math.PI / 4F) * limbSwingAmount;
        } else if(fairy.getWingCollapseFrames() > 8) {
        	//float amount = (float)Math.max(0, (8 - (fairy.getWingCollapseFrames() - 12))) / 8F;
        	float amount = (float)(fairy.getWingCollapseFrames() - 8) / 8F;
        	
        	this.body.rotateAngleX = (float)MathHelper.clampedLerp(0D, (float)Math.PI / 3F, amount);
        	this.leftLeg.rotateAngleX = -(float)MathHelper.clampedLerp(0D, (float)Math.PI / 4F, amount);
        	this.rightLeg.rotateAngleX = -(float)MathHelper.clampedLerp(0D, (float)Math.PI / 4F, amount);
        	
        	this.leftLeg.rotateAngleZ = (float)MathHelper.clampedLerp(0F, -0.15F, amount);
        	this.rightLeg.rotateAngleZ = (float)MathHelper.clampedLerp(0F, 0.15F, amount);
        } else {
        	this.body.rotateAngleX = 0F;
        	
        	this.leftLeg.rotateAngleZ = 0F;
        	this.rightLeg.rotateAngleZ = 0F;
        }

        //Idle swaying of arms.
        if(!fairy.getFlightMode()) {
	        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.07F) * 0.035F + 0.5F;
	        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.07F) * 0.035F + 0.5F;
	        this.rightArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.225F;
	        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.225F;
        } else {
        	this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.07F) * 0.225F + 0.25F;
	        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.07F) * 0.225 + 0.25F;
	        this.rightArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.035F;
	        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.035F;
        }
        
        //Wing Rotations
        
        
        if(fairy.getFlightMode()) {
        	float wingFlap = (float)((double)(System.currentTimeMillis() % 200L) / 200L) * (float)Math.PI * 2F;
        	
            this.leftWingMajora.rotateAngleY = 0.35F + MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.leftWingMinora.rotateAngleY = 0.3F + MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.rightWingMajora.rotateAngleY = -0.35F + -MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.rightWingMinora.rotateAngleY = -0.3F + -MathHelper.cos(wingFlap) * ((float)Math.PI / 7F);
            this.leftWingMajora.rotateAngleZ = 0.0F;
        	this.leftWingMinora.rotateAngleZ = 0.0F;
        	this.rightWingMajora.rotateAngleZ = 0.0F;
        	this.rightWingMinora.rotateAngleZ = 0.0F;
        } else if(fairy.getWingCollapseFrames() > 0) {
        	//float amount = (float)(20 - fairy.getWingCollapseFrames()) / 20.0F;
        	float amount = (float)(fairy.getWingCollapseFrames()) / 16.0F;
        	//amount = 1.0F - amount;
        	
            this.leftWingMajora.rotateAngleY = (float)MathHelper.clampedLerp(0.35F, 0.35F + ((float)Math.PI / 7F), amount);
            this.leftWingMinora.rotateAngleY = (float)MathHelper.clampedLerp(0.3F, 0.3F + ((float)Math.PI / 7F), amount);
            this.rightWingMajora.rotateAngleY = (float)MathHelper.clampedLerp(-0.35F, -0.35F - ((float)Math.PI / 7F), amount);
            this.rightWingMinora.rotateAngleY = (float)MathHelper.clampedLerp(-0.3F, -0.3F - ((float)Math.PI / 7F), amount);
            this.leftWingMajora.rotateAngleZ = (float)MathHelper.clampedLerp(-(float)Math.PI / 2F, 0.0F, amount);
            this.leftWingMinora.rotateAngleZ = (float)MathHelper.clampedLerp(-(float)Math.PI / 10F, 0.0F, amount);
        	this.rightWingMajora.rotateAngleZ = (float)MathHelper.clampedLerp((float)Math.PI / 2F, 0.0F, amount);
        	this.rightWingMinora.rotateAngleZ = (float)MathHelper.clampedLerp((float)Math.PI / 10F, 0.0F, amount);
        } else {
        	this.leftWingMajora.rotateAngleY = 0.35F;
            this.leftWingMinora.rotateAngleY = 0.3F;
            this.rightWingMajora.rotateAngleY = -0.35F;
            this.rightWingMinora.rotateAngleY = -0.3F;
        	this.leftWingMajora.rotateAngleZ = -(float)Math.PI / 2F;
        	this.leftWingMinora.rotateAngleZ = -(float)Math.PI / 10F;
        	this.rightWingMajora.rotateAngleZ = (float)Math.PI / 2F;
        	this.rightWingMinora.rotateAngleZ = (float)Math.PI / 10F;
        }
	}
	
	/**
	 * Don't really know what this does, copied it from ModelBiped.
	 */
	protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }

	/**
	 * Don't really know what this does, copied it from ModelBiped.
	 */
    protected EnumHandSide getMainHand(Entity entityIn)
    {
        if (entityIn instanceof EntityLivingBase)
        {
            EntityLivingBase entitylivingbase = (EntityLivingBase)entityIn;
            EnumHandSide enumhandside = entitylivingbase.getPrimaryHand();
            return entitylivingbase.swingingHand == EnumHand.MAIN_HAND ? enumhandside : enumhandside.opposite();
        }
        else
        {
            return EnumHandSide.RIGHT;
        }
    }
}
