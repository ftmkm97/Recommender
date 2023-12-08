package com.UserBased;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ConvertData {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader  br = new BufferedReader(new FileReader("data/new.data"));
		BufferedWriter  bw = new BufferedWriter(new FileWriter("data/news.csv"));
		String line;
		while ((line = br.readLine()) != null) {
			String[] values = line.split("\\t",-1);
			bw.write(values[0] + "," + values[1] + "," + values[2] + "," + values[3] + "," + values[4] +"\n");
		}
		br.close();
		bw.close();
	}

}
