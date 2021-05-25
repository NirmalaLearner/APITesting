package Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import resource.ReadExcelData;

public class payLoadConverter {
	
	public static String generatePayLoadString(String filename) throws IOException {
		String filepath = "C:\\Users\\Nimmi\\OneDrive\\Documents\\FullStackTraining\\CucumberLearning\\CaseStudy-eRegister\\RACaseStudyFramework\\src\\main\\java\\resource\\"+ filename;
		return new String(Files.readAllBytes(Paths.get(filepath)));		
	}

}
