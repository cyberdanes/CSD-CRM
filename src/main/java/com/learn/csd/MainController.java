package com.learn.csd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
	
	private MongoDAOImpl mongoDaoImpl = MongoDAOImpl.getInstance();
	
	@RequestMapping(value="/findByCourse",method = RequestMethod.GET)
    public List<Customer> findByCourse(@RequestParam("courseName") String courseName) {
		return mongoDaoImpl.findByCourse(courseName);
	}
	
	@RequestMapping(value="/findByLocation",method = RequestMethod.GET)
    public List<Customer> findByLocation(@RequestParam("location") String location) {
		return mongoDaoImpl.findByLocation(location);
	}
	
	@RequestMapping(value="/exportExcelToDB",method = RequestMethod.GET)
	public String getJSONfromExcel() {
		try {
			File file = new File("CustomerInfo.xls");
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
			
			return jSONArray.toString();

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
