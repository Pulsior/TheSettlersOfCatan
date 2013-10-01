package com.Pulsior.SettlersOfCatan.game;

import java.util.Random;

/**
 * Class to roll a dice, similar to how an actual dice works
 */
public class Dice {
	
	public static int lastValue = 0;
		
	public void roll(){
		Random rnd = new Random();
		int firstDice = rnd.nextInt(6)+1;
		int secondDice = rnd.nextInt(6)+1;
		int result = firstDice + secondDice;
		lastValue = result;
	}
	
}
