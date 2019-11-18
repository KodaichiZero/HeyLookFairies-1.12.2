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
public abstract class ModelFairyBase extends ModelBase {
	
	public static final float fairyScale = 0.5F;
	
	//The base for the body pieces
	public ModelRenderer headBase;
    public ModelRenderer bodyBase;
    public ModelRenderer leftArmBase;
    public ModelRenderer rightArmBase;
    public ModelRenderer leftLegBase;
    public ModelRenderer rightLegBase;
	
	public ModelFairyBase() {
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        //Define body pieces
        this.headBase = new ModelRenderer(this, 0, 0);
        this.headBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.headBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        
        this.bodyBase = new ModelRenderer(this, 0, 0);
        this.bodyBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.bodyBase.setRotationPoint(0.0F, 0.0F, 0.0F);
        
        this.rightArmBase = new ModelRenderer(this, 0, 0);
        this.rightArmBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.rightArmBase.setRotationPoint(-3.0F, 1.0F, 0.0F);
        
        this.leftArmBase = new ModelRenderer(this, 0, 0);
        this.leftArmBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.leftArmBase.setRotationPoint(3.0F, 1.0F, 0.0F);
        
        this.rightLegBase = new ModelRenderer(this, 0, 0);
        this.rightLegBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.rightLegBase.setRotationPoint(-1.0F, 6.0F, 0.0F);
        this.bodyBase.addChild(rightLegBase);
        
        this.leftLegBase = new ModelRenderer(this, 0, 0);
        this.leftLegBase.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.leftLegBase.setRotationPoint(1.0F, 6.0F, 0.0F);
        this.bodyBase.addChild(leftLegBase);
    }
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
        this.childRender(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		
        GlStateManager.popMatrix();
    }
	
	public abstract void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale);
	
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
		this.headBase.rotateAngleY = netHeadYaw * 0.017453292F;
		this.headBase.rotateAngleX = headPitch * 0.017453292F;
        
        //Reset Limb Rotations
        this.rightArmBase.rotateAngleX = 0.0F;
        this.leftArmBase.rotateAngleX = 0.0F;
        this.rightArmBase.rotateAngleY = 0.0F;
        this.leftArmBase.rotateAngleY = 0.0F;
        this.rightArmBase.rotateAngleZ = 0.0F;
        this.leftArmBase.rotateAngleZ = 0.0F;
        this.rightLegBase.rotateAngleX = 0.0F;
        this.leftLegBase.rotateAngleX = 0.0F;
        this.rightLegBase.rotateAngleY = 0.0F;
        this.leftLegBase.rotateAngleY = 0.0F;
        this.rightLegBase.rotateAngleZ = 0.0F;
        this.leftLegBase.rotateAngleZ = 0.0F;
        
        //Limb swinging during walking.
        if(!fairy.getFlightMode()) {
	        this.rightArmBase.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
	        this.leftArmBase.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
	        this.rightLegBase.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
	        this.leftLegBase.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        }

        //Check if the fairy is riding on someone's shoulder.
        boolean shoulderRiding = false;
        if(fairy.isShoulderRiding() ) {
        	shoulderRiding = true;
        }
        
        //Show the sitting animation.
        if(this.isRiding || shoulderRiding) {
            this.rightArmBase.rotateAngleX = -((float)Math.PI / 5F);
            this.leftArmBase.rotateAngleX = -((float)Math.PI / 5F);
            this.rightLegBase.rotateAngleX = -1.4137167F;
            this.rightLegBase.rotateAngleY = ((float)Math.PI / 10F);
            this.rightLegBase.rotateAngleZ = 0.07853982F;
            this.leftLegBase.rotateAngleX = -1.4137167F;
            this.leftLegBase.rotateAngleY = -((float)Math.PI / 10F);
            this.leftLegBase.rotateAngleZ = -0.07853982F;
        }

        //Arm Swinging Stuff.
        this.bodyBase.rotateAngleY = 0.0F;
        if (this.swingProgress > 0.0F)
        {
            EnumHandSide enumhandside = this.getMainHand(entityIn);
            ModelRenderer modelrenderer = this.getArmForSide(enumhandside);
            float f1 = this.swingProgress;
            this.bodyBase.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;

            if (enumhandside == EnumHandSide.LEFT)
            {
                this.bodyBase.rotateAngleY *= -1.0F;
            }

            this.rightArmBase.rotateAngleY += this.bodyBase.rotateAngleY;
            this.leftArmBase.rotateAngleY += this.bodyBase.rotateAngleY;
            this.leftArmBase.rotateAngleX += this.bodyBase.rotateAngleY;
            f1 = 1.0F - this.swingProgress;
            f1 = f1 * f1;
            f1 = f1 * f1;
            f1 = 1.0F - f1;
            float f2 = MathHelper.sin(f1 * (float)Math.PI);
            float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.headBase.rotateAngleX - 0.7F) * 0.75F;
            modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
            modelrenderer.rotateAngleY += this.bodyBase.rotateAngleY * 2.0F;
            modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
        }

        //Flight Mode Body and limb rotation stuff
        if(fairy.getFlightMode()) {
        	this.bodyBase.rotateAngleX = (float)Math.PI / 3F;
        	this.leftLegBase.rotateAngleX -= (float)Math.PI / 4F;
        	this.rightLegBase.rotateAngleX -= (float)Math.PI / 4F;
        	
        	this.leftLegBase.rotateAngleZ = -0.15F;
        	this.rightLegBase.rotateAngleZ = 0.15F;
        	
        	//Adjust rotations according to move speed
        	this.bodyBase.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	this.leftLegBase.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	this.rightLegBase.rotateAngleX += ((float)Math.PI / 6F) * limbSwingAmount;
        	
        	this.leftArmBase.rotateAngleX += ((float)Math.PI / 4F) * limbSwingAmount;
        	this.rightArmBase.rotateAngleX += ((float)Math.PI / 4F) * limbSwingAmount;
        } else if(fairy.getWingCollapseFrames() > 8) {
        	//float amount = (float)Math.max(0, (8 - (fairy.getWingCollapseFrames() - 12))) / 8F;
        	float amount = (float)(fairy.getWingCollapseFrames() - 8) / 8F;
        	
        	this.bodyBase.rotateAngleX = (float)MathHelper.clampedLerp(0D, (float)Math.PI / 3F, amount);
        	this.leftLegBase.rotateAngleX = -(float)MathHelper.clampedLerp(0D, (float)Math.PI / 4F, amount);
        	this.rightLegBase.rotateAngleX = -(float)MathHelper.clampedLerp(0D, (float)Math.PI / 4F, amount);
        	
        	this.leftLegBase.rotateAngleZ = (float)MathHelper.clampedLerp(0F, -0.15F, amount);
        	this.rightLegBase.rotateAngleZ = (float)MathHelper.clampedLerp(0F, 0.15F, amount);
        } else {
        	this.bodyBase.rotateAngleX = 0F;
        	
        	this.leftLegBase.rotateAngleZ = 0F;
        	this.rightLegBase.rotateAngleZ = 0F;
        }

        //Idle swaying of arms.
        if(!fairy.getFlightMode()) {
	        this.rightArmBase.rotateAngleZ += MathHelper.cos(ageInTicks * 0.07F) * 0.035F + 0.5F;
	        this.leftArmBase.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.07F) * 0.035F + 0.5F;
	        this.rightArmBase.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.225F;
	        this.leftArmBase.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.225F;
        } else {
        	this.rightArmBase.rotateAngleZ += MathHelper.cos(ageInTicks * 0.07F) * 0.225F + 0.25F;
	        this.leftArmBase.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.07F) * 0.225 + 0.25F;
	        this.rightArmBase.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.035F;
	        this.leftArmBase.rotateAngleX -= MathHelper.sin(ageInTicks * 0.2F) * 0.035F;
        }
	}
	
	/**
	 * Don't really know what this does, copied it from ModelBiped.
	 */
	protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArmBase : this.rightArmBase;
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
