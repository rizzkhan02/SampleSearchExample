package com.search.handler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.search.Descriptor.FinalOutput;

@PropertySource(value = { "classpath:application.properties" })

@Service(value="searchHandler")
public class SearchHandler {
	
	@Autowired
    private Environment environment;


	public FinalOutput searchKeywordInLogs(String key) throws IOException {

		HashMap<String, Integer> result = new HashMap<String, Integer>();
		FinalOutput finalOutput = new FinalOutput();
		int totalCount = 0;
		String logFileLocation = environment.getProperty("logs.location");
		File folder = null;
		try {
			folder = new File(logFileLocation);
			Set<String> fileNameSet = new HashSet<String>();
			for (File fileEntry : folder.listFiles()) {
				fileNameSet.add(fileEntry.getName());
			}
			if (!fileNameSet.isEmpty()) {

				for (String each : fileNameSet) {
					try {
						BufferedReader readEachLog = new BufferedReader(
								new FileReader(logFileLocation + "/" + each));
						String line;
						int count = 0;
						while ((line = readEachLog.readLine()) != null) {
							int totalwordInLine = line.length();
							do {
								if (line.contains(key)) {
									line = line.replaceFirst(key, "");
									totalwordInLine = line.length();
									count++;
								} else {
									totalwordInLine = 0;
								}
							} while (totalwordInLine > 0);

						}
						result.put(each, count);
						totalCount += count;
					} catch (IOException e) {
						finalOutput.setStatus(FinalOutput.STATUS_ERROR);
						finalOutput.setException(e.toString());
						return finalOutput;
					}

				}
			}
		} catch (Exception e) {
			finalOutput.setStatus(FinalOutput.STATUS_ERROR);
			finalOutput.setException(e.toString());
			return finalOutput;
		}

		finalOutput.setTotalCount(totalCount);
		finalOutput.setLogWiseMap(result);
		finalOutput.setStatus(FinalOutput.STATUS_SUCCESS);
		return finalOutput;
	}

}
