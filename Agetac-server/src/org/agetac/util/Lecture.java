package org.agetac.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lecture {
	private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public String askInput(String text) throws IOException{
		System.out.print(text);
        return reader.readLine();
	}
	
}
