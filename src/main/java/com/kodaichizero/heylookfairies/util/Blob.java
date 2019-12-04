package com.kodaichizero.heylookfairies.util;

import java.util.Random;

/**
 * It's the Blob Engine baby! Hell yeah
 * @author Kodaichi
 *
 */
public class Blob {
	
	private static final float pi2 = (float)Math.PI * 2.0F;
	
	private static final int minVertices = 4;
	private static final int maxVertices = 99;
	
	private static final float minRadius = 1.0F;
	private static final float maxRadius = 1000.0F;
	
	private int numVertices;
	private float averageRadius;
	private float[][] polarCoordinates;
	private float horzVariance;
	private float vertVariance;
	private Random rand;
	
	public Blob(int numVertices, float radius, float horzVariance, float vertVariance) {
		
		//Argument Validation
		if(numVertices < minVertices || numVertices > maxVertices) {
			throw new IllegalArgumentException("Invalid argument specified, number of vertices must be between " + minVertices + " and " + maxVertices + ".");
		}
		
		if(radius < minRadius || radius > maxRadius) {
			throw new IllegalArgumentException("Invalid argument specified, number of vertices must be between " + minVertices + " and " + maxVertices + ".");
		}
		
		if(horzVariance < 0.0F || horzVariance > 1.0F) {
			throw new IllegalArgumentException("Invalid argument specified, horizontal variance must be between 0.0F and 1.0F");
		}
		
		if(vertVariance < 0.0F || vertVariance > 1.0F) {
			throw new IllegalArgumentException("Invalid argument specified, horizontal variance must be between 0.0F and 1.0F");
		}
		
		//Set internal values to the arguments.
		this.numVertices = numVertices;
		this.averageRadius = radius;
		this.horzVariance = horzVariance;
		this.vertVariance = vertVariance;
		this.polarCoordinates = new float[numVertices][2];
		this.rand = new Random();
		this.generate();
	}
	
	//Regen I guess?
	public void regenerate() {
		this.polarCoordinates = new float[numVertices][2];
		this.generate();
	}
	
	//Create our blob.
	private void generate() {
		
		//Constant used in the calculations.
		float anglePartition = (pi2 / (float)numVertices);
		
		//Start with a random horizontal offset
		float hOffset = anglePartition * rand.nextFloat();
		
		for(int i = 0; i < numVertices; i++) {
			
			//Angle generates with optional random deviation.
			float angle = anglePartition * (float)i;
			angle += (float)horzVariance * (rand.nextFloat() - 0.5F) * anglePartition;
			angle += hOffset;
			
			//Vector length generates with optional random deviation.
			float length = averageRadius + ((float)vertVariance * (rand.nextFloat() - 0.5F) * averageRadius * 2.0F);
			
			polarCoordinates[i][0] = angle;
			polarCoordinates[i][1] = length;
		}
	}
	
	//Check to see if a point lies within the bounds of the blob.
	public boolean pointWithinBounds(float pointX, float pointY) {
		return comparePointToBounds(pointX, pointY) <= 0.0F;
	}
	
	//Compare to see where a point lies in relation to the blob.
	//If outside, return greater than zero.
	//If inside, return less than zero.
	public float comparePointToBounds(float pointX, float pointY) {
		return comparePointToBoundsWithRadiusMod(pointX, pointY, 1.0F);
	}
	
	//Radius mod is used mainly to make spherical structures easier to make.
	public float comparePointToBoundsWithRadiusMod(float pointX, float pointY, float radiusMod) {
		
		//Convert the supplied point into an angle.
		float angle = (float)Math.atan2(pointY, pointX);
		while(angle < 0.0F) angle += pi2;
		while(angle > (float)Math.PI  * 2.0F) angle -= pi2;
		
		float coord1[] = {0.0F, 0.0F};
		float coord2[] = {0.0F, 0.0F};
		boolean foundAngle = false;
		
		//Loop through the blob's polar coordinates and figure out which two points the supplied point lies between.
		for(int i = 0; i < numVertices; i++) {
			int j = i + 1;
			if(j >= numVertices) j = 0;
			
			//Fish out coordinates
			coord1 = new float[] {polarCoordinates[i][0], polarCoordinates[i][1]};
			coord2 = new float[] {polarCoordinates[j][0], polarCoordinates[j][1]};
			
			while(coord2[0] < coord1[0]) coord2[0] += pi2;
			while(coord2[0] - coord1[0] > pi2) coord2[0] -= pi2;
			
			//Compare angles
			if(angle >= coord1[0] && angle <= coord2[0]) {
				foundAngle = true;
				break;
			}
			
			//Compare angles accounting for underflow
			coord1[0] -= pi2;
			coord2[0] -= pi2;
			if(angle >= coord1[0] && angle <= coord2[0]) {
				foundAngle = true;
				break;
			}
			
			//Compare angles accounting for overflow
			coord1[0] += pi2 * 2.0F;
			coord2[0] += pi2 * 2.0F;
			if(angle >= coord1[0] && angle <= coord2[0]) {
				foundAngle = true;
				break;
			}
		}
			
		//If this somehow failed we return a result of 0F.
		if(!foundAngle) {
			return 0.0F;
		}
		
		//Calculate mu (the interpolation factor)
		float delta = coord2[0] - coord1[0];
		float mu = (angle - coord1[0]) / delta;
		
		//Pythagorean Theorem
		float pointInRadius = (float)Math.sqrt((pointX * pointX) + (pointY * pointY));
		
		//Cosine Interpolation
		float blobEdgeRadius = CosineInterpolate(coord1[1], coord2[1], mu) * radiusMod;
		
		//The Result
		return pointInRadius - blobEdgeRadius;
	}
	
	//Get the position of a vertex if you want.
	public float[] getVertexPos(int i) {
		if(i < 0) i = 0;
		if(i >= numVertices) i = numVertices - 1;
		return new float[] {(float)Math.cos(polarCoordinates[i][0]) * polarCoordinates[i][1], (float)Math.sin(polarCoordinates[i][0]) * polarCoordinates[i][1]};
	}
	
	//The cosine interpolation algorithm, copypasted from Google.
	private float CosineInterpolate(float y1, float y2, float mu) {
		double mu2 = (1.0D - Math.cos(mu * Math.PI)) / 2.0D;
		return (float)((y1 * (1.0D - mu2)) + (y2 * mu2));
	}

	//Get how many vertices are in the blob.
	public int getNumVertices() {
		return numVertices;
	}
}
