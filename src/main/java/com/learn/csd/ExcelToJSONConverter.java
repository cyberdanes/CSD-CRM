package com.learn.csd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ExcelToJSONConverter {
	
	private static MongoDAOImpl mongoDaoImpl = MongoDAOImpl.getInstance();
/*
public static void main(String[] args) {
		File file = new File("D:/Study/sts-bundle/PS/SpringJPAHibernate/CSDCRM/CSD-CRM/CustomerInfo.xls");
		getJSONfromExcel(file);
	}
*/
	public static JSONArray getJSONfromExcel(File file) {
		try {

			Workbook workbook;

			workbook = Workbook.getWorkbook(file);

			// Get the first Sheet.
			Sheet sheet = workbook.getSheet(0);

			// Iterate through the rows.
			JSONArray jSONArray = new JSONArray();
			Cell[] titlesArray=sheet.getRow(0);
			
			for (int row = 1; row < sheet.getRows(); row++) {

				JSONObject colData = new JSONObject();

				Cell[] cellsArray=sheet.getRow(row);
		
				if(cellsArray!=null && titlesArray!=null){
					for(int i=0; i<titlesArray.length ;i++){			
						try {
							if(i<cellsArray.length){
								colData.put(titlesArray[i].getContents(), cellsArray[i].getContents());
							}else{
								colData.put(titlesArray[i].getContents(), "");
							}
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				mongoDaoImpl.insertCustomer("customer", colData);
				jSONArray.put(colData);
			}
			
			return jSONArray;

		} catch (BiffException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return null;

	}
}
