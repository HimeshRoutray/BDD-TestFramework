package com.test.utilities;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONObject;

import org.json.simple.parser.JSONParser;

public class TestDataLoader {

	public JSONObject getTestData(String testDataRef) {

		JSONObject testData = null;

		JSONParser parser = new JSONParser();

		String fileName = "src/test/resources/testData/" + testDataRef + ".json";

		try {

			testData = (JSONObject) parser.parse(new FileReader(fileName));

		} catch (Exception e) {

			throw new RuntimeException("Failed to read test data from " + fileName, e);

		}

		return testData;

	}

	public JSONObject getTestData(String testDataSheet, String dataType) {

		JSONObject testData = null;

		JSONObject fullDataSheet = null;

		JSONParser parser = new JSONParser();

		String fileName = "src/test/resources/testData/" + testDataSheet + ".json";

		try {

			fullDataSheet = (JSONObject) parser.parse(new FileReader(fileName));

			testData = (JSONObject) fullDataSheet.get(dataType);

		} catch (Exception e) {

			throw new RuntimeException("Failed to read test data from " + fileName, e);

		}

		return testData;

	}

	@SuppressWarnings("unchecked")

	public Set<String> getKeySet(String testDataSheet, String dataType) {

		JSONObject testData = null;

		JSONObject fullDataSheet = null;

		Set<String> keys = null;

		JSONParser parser = new JSONParser();

		String fileName = "src/test/resources/testData/" + testDataSheet + ".json";

		try {

			fullDataSheet = (JSONObject) parser.parse(new FileReader(fileName));

			testData = (JSONObject) fullDataSheet.get(dataType);

			keys = testData.keySet();

		} catch (Exception e) {

			throw new RuntimeException("Failed to read test data from " + fileName, e);

		}

		return keys;

	}

	@SuppressWarnings("unchecked")

	public HashMap<String, String> getKeyValuePairFromJson(String testDataSheet, String dataType) {

		JSONObject testData = null;

		JSONObject fullDataSheet = null;

		Set<String> keys = null;

		String key = null;

		HashMap<String, String> keyValueFromJSON = new HashMap<>();

		JSONParser parser = new JSONParser();

		String fileName = "src/test/resources/testData/" + testDataSheet + ".json";

		try {

			fullDataSheet = (JSONObject) parser.parse(new FileReader(fileName));

			testData = (JSONObject) fullDataSheet.get(dataType);

			keys = testData.keySet();

			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {

				key = it.next();

				keyValueFromJSON.put(key, testData.get(key).toString());

			}

		} catch (Exception e) {

			throw new RuntimeException("Failed to read test data from " + fileName, e);

		}

		return keyValueFromJSON;

	}

	public JSONObject getData(String dataType) {

		JSONObject testData = null;

		JSONObject fullDataSheet = null;

		JSONParser parser = new JSONParser();

		String folderPath = "src/test/resources/testData/";

		for (String fileName : listFilesForFolder(folderPath)) {

			String filePath = folderPath + fileName;

			try {

				fullDataSheet = (JSONObject) parser.parse(new FileReader(filePath));

				testData = (JSONObject) fullDataSheet.get(dataType);

				if (testData != null) {

					System.out.println(filePath);

					return testData;

				}

			} catch (Exception e) {

				throw new RuntimeException("Failed to read test data from " + filePath, e);

			}

		}

		return testData;

	}

	public static void main(String[] arg) {

		try {

			TestDataLoader dataLoader = new TestDataLoader();

			JSONObject data = new JSONObject();

			data = dataLoader.getData("queryModificationGoutam");

			System.out.println("Test Data is : " + data.get("VAR_LOGIN_1").toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/**
	 * 
	 * ------------------------------------ Framework Enhancement
	 * -----------------------------------------------------
	 * 
	 */

	@SuppressWarnings("unchecked")

	public HashMap<String, String> getKeyValuePairFromJson(String dataType) {

		JSONObject testData = null;

		Set<String> keys = null;

		String key = null;

		HashMap<String, String> keyValueFromJSON = new HashMap<>();

		try {

			testData = (JSONObject) getData(dataType);

			keys = testData.keySet();

			Iterator<String> it = keys.iterator();

			while (it.hasNext()) {

				key = it.next();

				keyValueFromJSON.put(key, testData.get(key).toString());

			}

		} catch (Exception e) {

			throw new RuntimeException("Failed to read test data from block name: " + dataType, e);

		}

		return keyValueFromJSON;

	}

	public List<String> listFilesForFolder(String folderPath) {

		File folder = new File(folderPath);

		List<String> fileNames = new ArrayList<String>();

		for (File fileEntry : folder.listFiles()) {

			if (fileEntry.isDirectory()) {

				System.out.println(fileEntry.getName() + " is a folder not a file");

			} else {

				if (fileEntry.isFile()) {

					fileNames.add(fileEntry.getName());

				}

			}

		}

		return fileNames;

	}

}