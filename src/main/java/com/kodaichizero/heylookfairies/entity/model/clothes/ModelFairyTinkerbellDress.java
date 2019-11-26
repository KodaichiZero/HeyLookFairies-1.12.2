package com.kodaichizero.heylookfairies.entity.model.clothes;

import com.kodaichizero.heylookfairies.entity.EntityFairy;
import com.kodaichizero.heylookfairies.entity.model.ModelFairyBase;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

//This was an idea for clothes which unfortunately failed.
@Deprecated
public class ModelFairyTinkerbellDress extends ModelFairyBase {
	public static float root2 = (float)Math.sqrt(2.0D);
	
	public ModelRenderer dressTop;
	public ModelRenderer leftChestPiece;
	public ModelRenderer rightChestPiece;
	
	public ModelRenderer skirtBaseFront;
	public ModelRenderer skirtBaseBack;
	public ModelRenderer skirtBaseLeft;
	public ModelRenderer skirtBaseRight;
	
	public ModelRenderer skirtFrontLeft;
	public ModelRenderer skirtFrontRight;
	
	public ModelFairyTinkerbellDress() {
		this.renderStage = 0;
        this.textureWidth = 32;
        this.textureHeight = 32;
    
        this.dressTop = new ModelRenderer(this, 0, 0);
        this.dressTop.addBox(-2.0F, 1.75F, -1.0F, 4, 4, 2, 0.11F);
        super.bodyBase.addChild(dressTop);
        
        this.leftChestPiece = new ModelRenderer(this, 15, 9);
        this.leftChestPiece.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 0, 0.01F);
        this.leftChestPiece.setRotationPoint(-0.75F, 1.75F, -1.1F);
        this.leftChestPiece.rotateAngleZ = (float)Math.PI / 4F;
        super.bodyBase.addChild(leftChestPiece);
        
        this.rightChestPiece = new ModelRenderer(this, 17, 9);
        this.rightChestPiece.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 0, 0.01F);
        this.rightChestPiece.setRotationPoint(0.75F, 1.75F, -1.1F);
        this.rightChestPiece.rotateAngleZ = (float)Math.PI / 4F;
        super.bodyBase.addChild(rightChestPiece);
        
        this.skirtBaseFront = new ModelRenderer(this, 0, 16);
        this.skirtBaseFront.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.skirtBaseFront.setRotationPoint(0.0F, 5.85F, -1.0F);
        super.bodyBase.addChild(skirtBaseFront);
        
        this.skirtBaseBack = new ModelRenderer(this, 0, 16);
        this.skirtBaseBack.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.skirtBaseBack.setRotationPoint(0.0F, 5.85F, 1.0F);
        super.bodyBase.addChild(skirtBaseBack);
        
        this.skirtBaseLeft = new ModelRenderer(this, 0, 16);
        this.skirtBaseLeft.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.skirtBaseLeft.setRotationPoint(-2.0F, 5.85F, 0.0F);
        super.bodyBase.addChild(skirtBaseLeft);
        
        this.skirtBaseRight = new ModelRenderer(this, 0, 16);
        this.skirtBaseRight.addBox(0.0F, 0.0F, 0.0F, 0, 0, 0);
        this.skirtBaseRight.setRotationPoint(2.0F, 5.85F, 0.0F);
        super.bodyBase.addChild(skirtBaseRight);
        
        this.skirtFrontLeft = new ModelRenderer(this, 15, 2);
        this.skirtFrontLeft.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtFrontLeft.setRotationPoint(-0.75F, 0.0F, 0.0F);
        this.skirtFrontLeft.rotateAngleZ = (float)Math.PI / 4F;
        this.skirtBaseFront.addChild(skirtFrontLeft);
        
        this.skirtFrontRight = new ModelRenderer(this, 17, 2);
        this.skirtFrontRight.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 0, 0.01F);
        this.skirtFrontRight.setRotationPoint(0.75F, 0.0F, 0.0F);
        this.skirtFrontRight.rotateAngleZ = (float)Math.PI / 4F;
        this.skirtBaseFront.addChild(skirtFrontRight);
	}
	
	/**
	 * Override because we need to scale the model down before rendering it.
	 */
	@Override
	public void childRender(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		bodyBase.isHidden = (this.renderStage != 1);		
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
		
		this.skirtBaseFront.rotateAngleX = -(0.5F + skirtDisplacement) - MathHelper.cos(ageInTicks * 0.1F) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
		
		this.skirtBaseBack.rotateAngleX = 0.5F + MathHelper.cos(ageInTicks * 0.1F) * 0.125F - (float)Math.min(0.0D, fairy.motionY);

		this.skirtBaseLeft.rotateAngleZ = 0.5F + MathHelper.cos(ageInTicks * 0.1F) * 0.125F - (float)Math.min(0.0D, fairy.motionY);
		
		this.skirtBaseRight.rotateAngleZ = -0.5F - MathHelper.cos(ageInTicks * 0.1F) * 0.125F + (float)Math.min(0.0D, fairy.motionY);
	}
}
