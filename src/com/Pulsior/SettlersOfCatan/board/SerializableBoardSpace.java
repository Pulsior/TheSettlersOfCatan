package com.Pulsior.SettlersOfCatan.board;

import java.io.Serializable;

/**
 * The serializable equivalent of the BoardSpace. Used in SerializableSPlayer.
 * @author Pulsior
 *
 */

public class SerializableBoardSpace implements Serializable{

	private static final long serialVersionUID = 1L;
	Resource resource;
	double x;
	double y;
	double z;
	int number;


	public SerializableBoardSpace(Resource resource, double x, double y, double z, int number){
		this.resource = resource;
		this.x = x;
		this.y = y;
		this.z = z;
		this.number = number;
	}
	
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}
	
	public Resource getResource(){
		return resource;
	}
	
	public int getNumber(){
		return number;
	}
}
