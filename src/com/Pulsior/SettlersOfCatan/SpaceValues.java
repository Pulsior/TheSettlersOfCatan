package com.Pulsior.SettlersOfCatan;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

/**
 * Class to get the number associated with a space
 * @author Pulsior
 *
 */

public class SpaceValues {
	static World world = Bukkit.getServer().getWorld("world");
	public static Location locBR2 = new Location(world, -899.00, 4.00, -935.00);
	public static Location firstLocBR4 = new Location(world, -871.00, 4.00, -919.00);
	public static Location secondLocBR4  = new Location(world, -871.00, 4.00, -966.00);
	public static Location locOR3 = new Location(world, -885.00, 4.00, -927.00);
	public static Location locOR6 = new Location(world, -885.00, 4.00, -975.00);
	public static Location locOR8  = new Location(world, -858.00, 4.00, -943.00);
	public static Location locSH10 = new Location(world, -885.00, 4.00, -943.00);
	public static Location locSH12 = new Location(world, -857.00, 4.00, -975.00);
	public static Location locSH3  = new Location(world, -899.00, 4.00, -967.00);
	public static Location locSH5  = new Location(world, -843.00, 4.00, -951.00);
	public static Location locWO5 = new Location(world, -844.00, 4.00, -935.00);
	public static Location locWO8 = new Location(world, -885.00, 4.00, -958.00);
	public static Location locWO9  = new Location(world, -856.00, 4.00, -959.00);
	public static Location locWO10  = new Location(world, -871.00, 4.00, -935.00);
	public static Location locWH6 = new Location(world, -843.00, 4.00, -968.00);
	public static Location locWH9 = new Location(world, -871.00, 4.00, -983.00);
	public static Location firstLocWH11  = new Location(world, -857.00, 4.00, -927.00);
	public static Location secondLocWH11  = new Location(world, -900.00, 4.00, -951.00);

	/**
	 * Get the corresponding number by a space. A switch would've been better,
	 * but cannot be used because of the different variable scope.
	 * @param res
	 * @param loc
	 * @return
	 */

	public int getNumber(Resource res, Location loc){
		if(res.equals(Resource.BRICKS)){
			double[] values = new double[3];
			double d1 = loc.distance(locBR2);
			double d2 = loc.distance(firstLocBR4);
			double d3 = loc.distance(secondLocBR4);
			values[0] = d1;
			values[1] = d2;
			values[2] = d3;
			double minValue = 10000.00; //Absurdly high value to make sure the first value will always be lower
			for(int x = 0; x < 3; x++){
				if(values[x] < minValue){
					minValue = values[x];
				}
			}
			if(minValue == d1){
				return 2;
			}
			if(minValue == d2 || minValue == d3){
				return 4;
			}

		}
		if(res.equals(Resource.ORE)){
			double[] values = new double[3];
			double d1 = loc.distance(locOR3);
			double d2 = loc.distance(locOR6);
			double d3 = loc.distance(locOR8);
			values[0] = d1;
			values[1] = d2;
			values[2] = d3;
			double minValue = 10000.00; //Absurdly high value to make sure the first value will always be lower
			for(int x = 0; x < 3; x++){
				if(values[x] < minValue){
					minValue = values[x];
				}
			}
			if(minValue == d1){
				return 3;
			}
			if(minValue == d2){
				return 6;
			}
			if(minValue == d3){
				return 8;
			}
		}

		if( res.equals(Resource.SHEEP) ){
			double[] values = new double[4];
			double d1 = loc.distance(locSH10);
			double d2 = loc.distance(locSH12);
			double d3 = loc.distance(locSH3);
			double d4 = loc.distance(locSH5);
			values[0] = d1;
			values[1] = d2;
			values[2] = d3;
			values[3] = d4;
			double minValue = 10000.00; //Absurdly high value to make sure the first value will always be lower
			for(int x = 0; x < 4; x++){
				if(values[x] < minValue){
					minValue = values[x];
				}
			}
			if(minValue == d1){
				return 10;
			}
			if(minValue == d2){
				return 12;
			}
			if(minValue == d3){
				return 3;
			}
			if(minValue == d4){
				return 5;
			}
		}
		if( res.equals(Resource.WOOD) ){
			double[] values = new double[4];
			double d1 = loc.distance(locWO10);
			double d2 = loc.distance(locWO8);
			double d3 = loc.distance(locWO9);
			double d4 = loc.distance(locWO5);
			values[0] = d1;
			values[1] = d2;
			values[2] = d3;
			values[3] = d4;
			double minValue = 10000.00; //Absurdly high value to make sure the first value will always be lower
			for(int x = 0; x < 4; x++){
				if(values[x] < minValue){
					minValue = values[x];
				}
			}
			if(minValue == d1){
				return 10;
			}
			if(minValue == d2){
				return 8;
			}
			if(minValue == d3){
				return 9;
			}
			if(minValue == d4){
				return 5;
			}
		}
		if( res.equals(Resource.WHEAT)){
			double[] values = new double[4];
			double d1 = loc.distance(locWH6);
			double d2 = loc.distance(locWH9);
			double d3 = loc.distance(firstLocWH11);
			double d4 = loc.distance(secondLocWH11);
			values[0] = d1;
			values[1] = d2;
			values[2] = d3;
			values[3] = d4;
			double minValue = 10000.00; //Absurdly high value to make sure the first value will always be lower
			for(int x = 0; x < 4; x++){
				if(values[x] < minValue){
					minValue = values[x];
				}
			}
			if(minValue == d1){
				return 6;
			}
			if(minValue == d2){
				return 9;
			}
			if(minValue == d3){
				return 11;
			}
			if(minValue == d4){
				return 11;
			}
		}
		return 0;
	}

}