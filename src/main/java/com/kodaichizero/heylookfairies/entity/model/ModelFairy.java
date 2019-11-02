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
    
    //Hair 1: Bouncy Pigtails
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
	
	public ModelFairy() {
		float expandAmount = 0;
		float verticalOffset = 0F;
        this.textureWidth = 64;
        this.textureHeight = 32;
        
        //Define body pieces
        this.head = new ModelRenderer(this, 0, 0);
        this.head.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6, expandAmount);
        this.head.setRotationPoint(0.0F, 0.0F + verticalOffset, 0.0F);
        
        this.leftEar = new ModelRenderer(this, 50, 0);
        this.leftEar.addBox(-3.0F, -1.0F, -0.5F, 3, 1, 1);
        this.leftEar.setRotationPoint(-3.0F, -3.0F + verticalOffset, 0.0F);
        this.head.addChild(leftEar);
        this.leftEarLobe = new ModelRenderer(this, 58, 0);
        this.leftEarLobe.addBox(-1.5F, 0F, -0.5F, 2, 1, 1);
        this.leftEar.addChild(leftEarLobe);
        
        this.rightEar = new ModelRenderer(this, 50, 0);
        this.rightEar.addBox(0F, -1.0F, -0.5F, 3, 1, 1);
        this.rightEar.setRotationPoint(3.0F, -3.0F + verticalOffset, 0.0F);
        this.rightEar.mirror = true;
        this.head.addChild(rightEar);
        this.rightEarLobe = new ModelRenderer(this, 58, 0);
        this.rightEarLobe.addBox(-0.5F, 0F, -0.5F, 2, 1, 1);
        this.rightEarLobe.mirror = true;
        this.rightEar.addChild(rightEarLobe);
        
        this.body = new ModelRenderer(this, 24, 0);
        this.body.addBox(-2.0F, 0.0F, -1.0F, 4, 6, 2, expandAmount);
        this.body.setRotationPoint(0.0F, 0.0F + verticalOffset, 0.0F);
        
        this.rightArm = new ModelRenderer(this, 36, 0);
        this.rightArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, expandAmount);
        this.rightArm.setRotationPoint(-3.0F, 1.0F + verticalOffset, 0.0F);
        
        this.leftArm = new ModelRenderer(this, 36, 0);
        this.leftArm.mirror = true;
        this.leftArm.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2, expandAmount);
        this.leftArm.setRotationPoint(3.0F, 1.0F + verticalOffset, 0.0F);
        
        this.rightLeg = new ModelRenderer(this, 44, 0);
        this.rightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, expandAmount);
        this.rightLeg.setRotationPoint(-1F, 6.0F + verticalOffset, 0.0F);
        
        this.leftLeg = new ModelRenderer(this, 44, 0);
        this.leftLeg.mirror = true;
        this.leftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 6, 2, expandAmount);
        this.leftLeg.setRotationPoint(1F, 6.0F + verticalOffset, 0.0F);
        
        this.leftWingMajora = new ModelRenderer(this, 29, 16);
        this.leftWingMajora.addBox(-9.0F, -5.0F, 0F, 9, 9, 0, expandAmount + 0.01F);
        this.leftWingMajora.setRotationPoint(-0.5F, 2F + verticalOffset, 1.0F);
        this.body.addChild(leftWingMajora);
        
        this.leftWingMinora = new ModelRenderer(this, 48, 16);
        this.leftWingMinora.addBox(-7.0F, 0.0F, 0F, 7, 9, 0, expandAmount + 0.01F);
        this.leftWingMinora.setRotationPoint(-0.5F, 2F + verticalOffset, 1.0F);
        this.body.addChild(leftWingMinora);
        
        this.rightWingMajora = new ModelRenderer(this, 29, 16);
        this.rightWingMajora.mirror = true;
        this.rightWingMajora.addBox(0F, -5.0F, 0F, 9, 9, 0, expandAmount + 0.01F);
        this.rightWingMajora.setRotationPoint(0.5F, 2F + verticalOffset, 1.0F);
        this.body.addChild(rightWingMajora);
        
        this.rightWingMinora = new ModelRenderer(this, 48, 16);
        this.rightWingMinora.mirror = true;
        this.rightWingMinora.addBox(0F, 0.0F, 0F, 7, 9, 0, expandAmount + 0.01F);
        this.rightWingMinora.setRotationPoint(0.5F, 2F + verticalOffset, 1.0F);
        this.body.addChild(rightWingMinora);
        
        //Define Bouncy Pigtails Pieces
        
        //Top
        this.hairTop = new ModelRenderer(this, 24, 8);
        this.hairTop.addBox(-3.0F, -7.0F, -3.0F, 6, 1, 6);
        this.head.addChild(hairTop);
        
        //Front
        this.hairFrontLeft = new ModelRenderer(this, 0, 12);
        this.hairFrontLeft.addBox(-3.0F, -6.0F, -4.0F, 2, 2, 1);
        this.head.addChild(hairFrontLeft);
        
        this.hairFrontMid = new ModelRenderer(this, 7, 12);
        this.hairFrontMid.addBox(-1.0F, -6.0F, -4.0F, 1, 1, 1);
        this.head.addChild(hairFrontMid);
        
        this.hairFrontRight = new ModelRenderer(this, 1, 12);
        this.hairFrontRight.addBox(0.0F, -6.0F, -4.0F, 3, 2, 1);
        this.head.addChild(hairFrontRight);
        
        //Left
        this.hairLeftFront = new ModelRenderer(this, 0, 15);
        this.hairLeftFront.addBox(-4.0F, -6.0F, -3.0F, 1, 3, 3);
        this.head.addChild(hairLeftFront);
        
        this.hairLeftBack = new ModelRenderer(this, 8, 15);
        this.hairLeftBack.addBox(-4.0F, -6.0F, 0.0F, 1, 4, 3);
        this.head.addChild(hairLeftBack);
        
        //Right
        this.hairRightFront = new ModelRenderer(this, 0, 15);
        this.hairRightFront.addBox(3.0F, -6.0F, -3.0F, 1, 3, 3);
        this.head.addChild(hairRightFront);
        
        this.hairRightBack = new ModelRenderer(this, 8, 15);
        this.hairRightBack.addBox(3.0F, -6.0F, 0.0F, 1, 4, 3);
        this.head.addChild(hairRightBack);
        
        //Back
        this.hairBack = new ModelRenderer(this, 0, 22);
        this.hairBack.addBox(-3.0F, -6.0F, 3.0F, 6, 5, 1);
        this.head.addChild(hairBack);
        
        //Left Pigtail
        this.hairPigtailLeftBig = new ModelRenderer(this, 16, 20);
        this.hairPigtailLeftBig.addBox(-3.0F, -3.0F, -1.5F, 3, 3, 3);
        this.hairPigtailLeftBig.setRotationPoint(-2.5F, -5.5F, 2F);
        this.head.addChild(hairPigtailLeftBig);
        
        this.hairPigtailLeftSmall = new ModelRenderer(this, 16, 15);
        this.hairPigtailLeftSmall.addBox(-3.5F, -2.5F, -1F, 1, 3, 2);
        this.hairPigtailLeftBig.addChild(hairPigtailLeftSmall);
        
        //Right Pigtail
        this.hairPigtailRightBig = new ModelRenderer(this, 16, 20);
        this.hairPigtailRightBig.addBox(0F, -3.0F, -1.5F, 3, 3, 3);
        this.hairPigtailRightBig.setRotationPoint(2.5F, -5.5F, 2F);
        this.head.addChild(hairPigtailRightBig);
        
        this.hairPigtailRightSmall = new ModelRenderer(this, 16, 15);
        this.hairPigtailRightSmall.addBox(2.5F, -2.5F, -1F, 1, 3, 2);
        this.hairPigtailRightBig.addChild(hairPigtailRightSmall);
    }
	
	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix();
		GlStateManager.translate(0.0F, 18.0F * scale, 0.0F);
		GlStateManager.scale(fairyScale, fairyScale, fairyScale);
        
		this.head.render(scale);
		this.body.render(scale);
        this.rightArm.render(scale);
        this.leftArm.render(scale);
        this.rightLeg.render(scale);
        this.leftLeg.render(scale);
		
        GlStateManager.popMatrix();
    }
	
	//@SuppressWarnings("incomplete-switch")
    public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
    	this.head.rotateAngleY = netHeadYaw * 0.017453292F;
        this.head.rotateAngleX = headPitch * 0.017453292F;

        this.body.rotateAngleY = 0.0F;
        
        float f = 1.0F;
        this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
        this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
        this.rightArm.rotateAngleZ = 0.0F;
        this.leftArm.rotateAngleZ = 0.0F;
        this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
        this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
        this.rightLeg.rotateAngleY = 0.0F;
        this.leftLeg.rotateAngleY = 0.0F;
        this.rightLeg.rotateAngleZ = 0.0F;
        this.leftLeg.rotateAngleZ = 0.0F;

        //Check if the fairy is riding on someone's shoulder.
        boolean shoulderRiding = false;
        if(entityIn instanceof EntityFairy && ((EntityFairy)entityIn).isShoulderRiding() ) {
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

        this.rightArm.rotateAngleY = 0.0F;
        this.rightArm.rotateAngleZ = 0.0F;

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

        this.body.rotateAngleX = 0.0F;

        this.rightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.leftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
        this.rightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        this.leftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
        
        //Pigtail Bounce
        if(entityIn.onGround) {
        	this.hairPigtailLeftBig.rotateAngleZ = -MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.375F / f;
        	this.hairPigtailRightBig.rotateAngleZ = MathHelper.cos(limbSwing * 2.0F * 0.6662F + (float)Math.PI) * limbSwingAmount * 0.375F / f;
        } else {
        	this.hairPigtailLeftBig.rotateAngleZ = -(float)entityIn.motionY;
        	this.hairPigtailRightBig.rotateAngleZ = (float)entityIn.motionY;
        }
        
        //Wing Rotations
        this.leftWingMajora.rotateAngleX = -(float)Math.PI * (8F / 9F);
        this.rightWingMajora.rotateAngleX = -(float)Math.PI * (8F / 9F);
        this.leftWingMinora.rotateAngleX = (float)Math.PI * (1F / 10F);
        this.rightWingMinora.rotateAngleX = (float)Math.PI * (1F / 10F);
        
        this.leftWingMajora.rotateAngleZ = -(float)Math.PI / 5F;
        this.rightWingMajora.rotateAngleZ = (float)Math.PI / 5F;
        
        this.leftWingMajora.rotationPointZ = 2.5F;
        this.rightWingMajora.rotationPointZ = 2.5F;
        this.leftWingMinora.rotationPointZ = 2.0F;
        this.rightWingMinora.rotationPointZ = 2.0F;
        
        this.leftWingMajora.rotationPointX = 1.5F;
        this.rightWingMajora.rotationPointX = -1.5F;
        this.leftWingMinora.rotationPointX = 1.5F;
        this.rightWingMinora.rotationPointX = -1.5F;
        //this.leftWingMajora.rotateAngleX = (float)Math.PI / 8F;
        //this.rightWingMajora.rotateAngleX = (float)Math.PI / 8F;
        
        //this.leftWingMajora.rotateAngleZ = (float)Math.PI / 8F;
        //this.rightWingMajora.rotateAngleZ = -(float)Math.PI / 8F;
	}
	
	//Copied from ModelBiped.
	protected ModelRenderer getArmForSide(EnumHandSide side) {
        return side == EnumHandSide.LEFT ? this.leftArm : this.rightArm;
    }

	//Copied from ModelBiped.
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
