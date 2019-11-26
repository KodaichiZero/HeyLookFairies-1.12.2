package com.kodaichizero.heylookfairies.entity.model;

import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Deprecated
public class ModelTrapezoid {
    /** The (x,y,z) vertex positions and (u,v) texture coordinates for each of the 8 points on a cube */
    private final PositionTextureVertex[] vertexPositions;
    /** An array of 6 TexturedQuads, one for each face of a cube */
    private final TexturedQuad[] quadList;
    /** X vertex coordinate of lower box corner */
    public final float posX1;
    /** Y vertex coordinate of lower box corner */
    public final float posY1;
    /** Z vertex coordinate of lower box corner */
    public final float posZ1;
    /** X vertex coordinate of upper box corner */
    public final float posX2;
    /** Y vertex coordinate of upper box corner */
    public final float posY2;
    /** Z vertex coordinate of upper box corner */
    public final float posZ2;
    public String boxName;

    public ModelTrapezoid(ModelRendererTrapezoid renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta) {
        this(renderer, texU, texV, x, y, z, dx, dy, dz, delta, renderer.mirror);
    }

    public ModelTrapezoid(ModelRendererTrapezoid renderer, int texU, int texV, float x, float y, float z, int dx, int dy, int dz, float delta, boolean mirror) {
        this.posX1 = x;
        this.posY1 = y;
        this.posZ1 = z;
        this.posX2 = x + (float)dx;
        this.posY2 = y + (float)dy;
        this.posZ2 = z + (float)dz;
        this.vertexPositions = new PositionTextureVertex[8];
        this.quadList = new TexturedQuad[6];
        float f = x + (float)dx;
        float f1 = y + (float)dy;
        float f2 = z + (float)dz;
        x = x - delta;
        y = y - delta;
        z = z - delta;
        f = f + delta;
        f1 = f1 + delta;
        f2 = f2 + delta;

        if (mirror)
        {
            float f3 = f;
            f = x;
            x = f3;
        }

        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x, y, z, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f, y, z, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f, f1, z, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x, f1, z, 8.0F, 0.0F);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x, y, f2, 0.0F, 0.0F);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f, y, f2, 0.0F, 8.0F);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f, f1, f2, 8.0F, 8.0F);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x, f1, f2, 8.0F, 0.0F);
        this.vertexPositions[0] = positiontexturevertex7;
        this.vertexPositions[1] = positiontexturevertex;
        this.vertexPositions[2] = positiontexturevertex1;
        this.vertexPositions[3] = positiontexturevertex2;
        this.vertexPositions[4] = positiontexturevertex3;
        this.vertexPositions[5] = positiontexturevertex4;
        this.vertexPositions[6] = positiontexturevertex5;
        this.vertexPositions[7] = positiontexturevertex6;
        this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, texU, texV + dz, texU + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, texU + dz, texV, texU + dz + dx, texV + dz, renderer.textureWidth, renderer.textureHeight);
        this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, renderer.textureWidth, renderer.textureHeight);
        this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] {positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);

        if (mirror)
        {
            for (TexturedQuad texturedquad : this.quadList)
            {
                texturedquad.flipFace();
            }
        }
    }
    
    /**
     * Adjust the expansion of the top half of the model
     * @param front
     * @param back
     * @param left
     * @param right
     * @return itself
     */
    public ModelTrapezoid adjustTopExpansion(float front, float back, float left, float right) {
    	
    	//Adjust the position of the vertices;
    	int indices[] = {0, 4, 1, 5};
    	for(int i = 0; i < 4; i++) {
        	if(i / 2 == 0) {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(-left, 0D, 0D);
        	} else {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(right, 0D, 0D);
        	}
        	
        	if(i % 2 == 0) {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(0D, 0D, -front);
        	} else {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(0D, 0D, back);
        	}
        }
        
        //Now readjust the quads.
        this.adjustQuads();
        return this;
    }
    
    /**
     * Adjust the expansion of the bottom half of the model
     * @param front
     * @param back
     * @param left
     * @param right
     * @return itself
     */
    public ModelTrapezoid adjustBottomExpansion(float front, float back, float left, float right) {
    	
    	//Adjust the position of the vertices;
    	int indices[] = {3, 7, 2, 6};
        for(int i = 0; i < 4; i++) {
        	if(i / 2 == 0) {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(-left, 0D, 0D);
        	} else {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(right, 0D, 0D);
        	}
        	
        	if(i % 2 == 0) {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(0D, 0D, -front);
        	} else {
        		vertexPositions[indices[i]].vector3D = vertexPositions[indices[i]].vector3D.addVector(0D, 0D, back);
        	}
        }
        
        //Now readjust the quads.
        this.adjustQuads();
        return this;
    }
    
    /**
     * Adjust the vertices of the already-defined quads to the new positions we recalculated.
     */
    private void adjustQuads() {
    	/*
        this.vertexPositions[0] = positiontexturevertex7;
        this.vertexPositions[1] = positiontexturevertex;
        this.vertexPositions[2] = positiontexturevertex1;
        this.vertexPositions[3] = positiontexturevertex2;
        this.vertexPositions[4] = positiontexturevertex3;
        this.vertexPositions[5] = positiontexturevertex4;
        this.vertexPositions[6] = positiontexturevertex5;
        this.vertexPositions[7] = positiontexturevertex6;
        */
    	int indices[][] = {{5, 1, 2, 6}, {0, 4, 7, 3}, {5, 4, 0, 1}, {2, 3, 7, 6}, {1, 0, 3, 2}, {4, 5, 6, 7}};
    	
    	for(int i = 0; i < 6; i++) {
    		for(int j = 0; j < 4; j++) {
    			this.quadList[i].vertexPositions[j].vector3D = this.vertexPositions[indices[i][j]].vector3D;
    		}
    	}
    	
    	/*
    	this.quadList[0] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[5], vertexPositions[1], vertexPositions[2], vertexPositions[6]}, texU + dz + dx, texV + dz, texU + dz + dx + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[1] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[0], vertexPositions[4], vertexPositions[7], vertexPositions[3]}, texU, texV + dz, texU + dz, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[2] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[5], vertexPositions[4], vertexPositions[0], vertexPositions[1]}, texU + dz, texV, texU + dz + dx, texV + dz, renderer.textureWidth, renderer.textureHeight);
        this.quadList[3] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[2], vertexPositions[3], vertexPositions[7], vertexPositions[6]}, texU + dz + dx, texV + dz, texU + dz + dx + dx, texV, renderer.textureWidth, renderer.textureHeight);
        this.quadList[4] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[1], vertexPositions[0], vertexPositions[3], vertexPositions[2]}, texU + dz, texV + dz, texU + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
        this.quadList[5] = new TexturedQuad(new PositionTextureVertex[] {vertexPositions[4], vertexPositions[5], vertexPositions[6], vertexPositions[7]}, texU + dz + dx + dz, texV + dz, texU + dz + dx + dz + dx, texV + dz + dy, renderer.textureWidth, renderer.textureHeight);
    	*/
    }

    @SideOnly(Side.CLIENT)
    public void render(BufferBuilder renderer, float scale) {
        for (TexturedQuad texturedquad : this.quadList)
        {
            texturedquad.draw(renderer, scale);
        }
    }

    public ModelTrapezoid setBoxName(String name) {
        this.boxName = name;
        return this;
    }
}