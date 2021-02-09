package com.trustly.emiliano.commons;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * 
 * Utility class to assist in handling numbers.
 * 
 * @author Emiliano Pessôa
 *
 */
public class NumberUtils {

	/**
	 * Converts a double number to decimal format. Default pattern '#.##'
	 * 
	 * @param value Number to be converted.
	 * 
	 * @return the converted number.
	 */
	public synchronized static String toDecimalFormat(double value) {
		DecimalFormatSymbols symbol = DecimalFormatSymbols.getInstance();
		symbol.setDecimalSeparator('.');
		DecimalFormat numberFormat = new DecimalFormat("#.##", symbol);
		numberFormat.setRoundingMode(RoundingMode.UP);
		return numberFormat.format(value);
	}

}
