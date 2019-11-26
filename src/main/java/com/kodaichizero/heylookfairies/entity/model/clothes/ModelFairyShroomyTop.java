package com.kodaichizero.heylookfairies.entity.model.clothes;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBase;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFairyShroomyTop extends ModelFairyBase {
	
    public ModelRenderer shirtTop;
    public ModelRenderer shirtFrontBack;
    public ModelRenderer shirtLeftRight;
    
    public ModelRenderer spotsTop;
    public ModelRenderer spotsFrontBack;
    public ModelRenderer spotsLeftRight;
    
    public ModelRenderer skirtFrontLeft;
    public ModelRenderer skirtFrontMid;
    public ModelRenderer skirtFrontRight;
    public ModelRenderer skirtBackLeft;
    public ModelRenderer skirtBackMid;
    public ModelRenderer skirtBackRight;
    public ModelRenderer skirtLeft;
    public ModelRenderer skirtRight;
	
	public ModelFairyShroomyTop() {
		this.renderStage = 0;
        this.textureWidth = 32;
        this.textureHeight = 32;
    
        //Shirt Top
        this.shirtTop = new ModelRenderer(this, 0, 14);
        this.shirtTop.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 2, 0.05F);
        super.bodyBase.addChild(shirtTop);
        
        this.shirtFrontBack = new ModelRenderer(this, 0, 0);
        this.shirtFrontBack.addBox(-2.0F, 1.0F, -1.5F, 4, 4, 3, 0.05F);
        super.bodyBase.addChild(shirtFrontBack);
        
        this.shirtLeftRight = new ModelRenderer(this, 14, 0);
        this.shirtLeftRight.addBox(-2.5F, 1.0F, -1.0F, 5, 4, 2, 0.05F);
        super.bodyBase.addChild(shirtLeftRight);
        
        //Mushroom Spots
        this.spotsTop = new ModelRenderer(this, 0, 17);
        this.spotsTop.addBox(-2.0F, 0.0F, -1.0F, 4, 1, 2, 0.06F);
        super.bodyBase.addChild(spotsTop);
        
        this.spotsFrontBack = new ModelRenderer(this, 0, 7);
        this.spotsFrontBack.addBox(-2.0F, 1.0F, -1.5F, 4, 4, 3, 0.06F);
        super.bodyBase.addChild(spotsFrontBack);
        
        this.spotsLeftRight = new ModelRenderer(this, 14, 7);
        this.spotsLeftRight.addBox(-2.5F, 1.0F, -1.0F, 5, 4, 2, 0.06F);
        super.bodyBase.addChild(spotsLeftRight);
        
        //Skirt
        this.skirtFrontLeft = new ModelRenderer(this, 15, 15);
        this.skirtFrontLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtFrontLeft.setRotationPoint(-1.0F, 5.0F, -1.0F);
        super.bodyBase.addChild(skirtFrontLeft);
        
        this.skirtFrontMid = new ModelRenderer(this, 17, 15);
        this.skirtFrontMid.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtFrontMid.setRotationPoint(0.0F, 5.0F, -1.0F);
        super.bodyBase.addChild(skirtFrontMid);
        
        this.skirtFrontRight = new ModelRenderer(this, 19, 15);
        this.skirtFrontRight.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtFrontRight.setRotationPoint(1.0F, 5.0F, -1.0F);
        super.bodyBase.addChild(skirtFrontRight);
        
        this.skirtBackLeft = new ModelRenderer(this, 15, 15);
        this.skirtBackLeft.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtBackLeft.setRotationPoint(-1.0F, 5.0F, 1.0F);
        super.bodyBase.addChild(skirtBackLeft);
        
        this.skirtBackMid = new ModelRenderer(this, 17, 15);
        this.skirtBackMid.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtBackMid.setRotationPoint(0.0F, 5.0F, 1.0F);
        super.bodyBase.addChild(skirtBackMid);
        
        this.skirtBackRight = new ModelRenderer(this, 19, 15);
        this.skirtBackRight.addBox(-1.0F, 0.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtBackRight.setRotationPoint(1.0F, 5.0F, 1.0F);
        super.bodyBase.addChild(skirtBackRight);
        
        this.skirtLeft = new ModelRenderer(this, 17, 15);
        this.skirtLeft.addBox(0.0F, 0.0F, -1.0F, 0, 2, 2, 0.01F);
        this.skirtLeft.setRotationPoint(-2.0F, 5.0F, 0.0F);
        super.bodyBase.addChild(skirtLeft);
        
        this.skirtRight = new ModelRenderer(this, 19, 15);
        this.skirtRight.addBox(0.0F, 0.0F, -1.0F, 0, 2, 2, 0.01F);
        this.skirtRight.setRotationPoint(2.0F, 5.0F, 0.0F);
        super.bodyBase.addChild(skirtRight);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		spotsTop.isHidden = 
		spotsFrontBack.isHidden = 
		spotsLeftRight.isHidden = (this.renderStage != 0);
		
		shirtTop.isHidden = 
		shirtFrontBack.isHidden = 
		shirtLeftRight.isHidden = (this.renderStage != 1);
		
		skirtFrontLeft.isHidden = 
		skirtFrontMid.isHidden = 
		skirtFrontRight.isHidden = 
		skirtBackLeft.isHidden = 
		skirtBackMid.isHidden = 
		skirtBackRight.isHidden = 
		skirtLeft.isHidden = 
		skirtRight.isHidden = (this.renderStage != 2);
		
		super.bodyBase.render(scale);
    }
	
	@Override
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);
		
		if(!(entityIn instanceof EntityFairy)) {
			return;
		}
		
		EntityFairy fairy = (EntityFairy)entityIn;
		float skirtDisplacement = 0.0F;
		if(fairy.getFlightMode()) {
			skirtDisplacement = 0.65F;
		} else if(fairy.isShoulderRiding() || fairy.isRiding()) {
			skirtDisplacement = 1.25F;
		}
		
		this.skirtFrontMid.rotateAngleX = -(0.625F + skirtDisplacement) - MathHelper.cos(ageInTicks * 0.1F) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		this.skirtFrontLeft.rotateAngleX = -(0.5F + skirtDisplacement) - MathHelper.cos((ageInTicks * 0.1F) + (float)(Math.PI * (2D / 3D))) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		this.skirtFrontRight.rotateAngleX = -(0.5F + skirtDisplacement) - MathHelper.cos((ageInTicks * 0.1F) + (float)(Math.PI * (4D / 3D))) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		
		this.skirtBackMid.rotateAngleX = 0.625F - MathHelper.cos(ageInTicks * 0.1F) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		this.skirtBackLeft.rotateAngleX = 0.5F - MathHelper.cos((ageInTicks * 0.1F) + (float)(Math.PI * (2D / 3D))) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		this.skirtBackRight.rotateAngleX = 0.5F - MathHelper.cos((ageInTicks * 0.1F) + (float)(Math.PI * (4D / 3D))) * 0.125F + (float)Math.min(0.0D, fairy.motionY);

		this.skirtLeft.rotateAngleZ = 0.5F + MathHelper.cos(ageInTicks * 0.1F) * 0.125F - (float)Math.min(0.0D, fairy.motionY);
		
		this.skirtRight.rotateAngleZ = -0.5F - MathHelper.cos(ageInTicks * 0.1F) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
	}
}
