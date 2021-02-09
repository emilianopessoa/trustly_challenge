package com.trustly.emiliano.commons;

import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * Utility class that contains helper methods for handling files files or input
 * stream of bytes.
 * 
 * @author Emiliano Pessôa
 *
 */
public class FileUtils {

	/**
	 * Count lines from a given file.
	 * 
	 * @param in An input stream
	 * @return The number of lines of the given file. If it is a binary the value
	 *         will be 0.
	 * @throws IOException - If an I/O exception occurs.
	 */
	public synchronized static long countLines(InputStream is) throws IOException {
		long lines = 0;

		byte[] c = new byte[1024];
		int count = 0;
		int readChars = 0;
		boolean endsWithoutNewLine = false;
		while ((readChars = is.read(c)) != -1) {
			for (int i = 0; i < readChars; ++i) {
				if (c[i] == '\n')
					++count;
			}
			endsWithoutNewLine = (c[readChars - 1] != '\n');
		}
		if (endsWithoutNewLine) {
			++count;
		}
		lines = count;

		return lines;
	}

}
