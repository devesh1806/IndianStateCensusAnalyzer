package com.indianstatecensusanalyser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import com.opencsv.CSVReader;

public class StateCensusAnalyser {
	
	public static final String PathName = "./src/main/resources/IndiaStateCensusData.csv";
	public static List<CSVStateCensus> stateCensusArray = new ArrayList<>();
	
	public static void main(String[] args) {
		loadingData();
		System.out.println(stateCensusArray.size());
	}

	private static void loadingData() {
		try (

				FileReader readerFile = new FileReader(PathName); 
				CSVReader reader = new CSVReader(readerFile);
			){
				List<String[]> records = reader.readAll();
				records.stream().forEach(n->{
				Iterator<String> rotate = Arrays.stream(n).iterator();
				String state = rotate.next();
	            String population = rotate.next();
	            String areaInSqKm = rotate.next();
	            String densityPerSqKm = rotate.next();
	            if (!population.equals("Population")) stateCensusArray.add(new CSVStateCensus(state, Long.parseLong(population), Long.parseLong(areaInSqKm), Integer.parseInt(densityPerSqKm)));
			});
		}catch(IOException e) {}
	}
}
