package resources;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utils.ExcelReader;

public class DataReader {

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {

		ExcelReader reader = new ExcelReader();

		return reader.getData("loginData");

	}
}
