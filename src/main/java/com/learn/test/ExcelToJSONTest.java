package com.learn.test;

import java.io.File;

import org.json.JSONArray;

import com.learn.csd.ExcelToJSONConverter;

import junit.framework.TestCase;

public class ExcelToJSONTest extends TestCase {

	public void testNullInput()
	{
		try
		{
			ExcelToJSONConverter conv = new ExcelToJSONConverter();
			conv.getJSONfromExcel(null);
			fail();
		}
		catch (Exception e)
		{
			assertTrue(true);

		}
	}

	public void testConversion()
	{
		ExcelToJSONConverter conv = new ExcelToJSONConverter();
		try
		{
			JSONArray json = conv.getJSONfromExcel(new File("CustomerInfo.xls"));
			assertTrue(true);
		}
		catch (Exception ex)
		{
			fail();
		}
	}

	public void testConversionValue()
	{
		try
		{
			ExcelToJSONConverter conv = new ExcelToJSONConverter();
			JSONArray json = conv.getJSONfromExcel(new File("CustomerInfo.xls"));
			String expected = 
					"[{\"Email\":\"rohin.patel@gmail.com\",\"Phone Number\":\"928398433\",\"Registration No\":\"CSD-PUN-15031\",\"Location\":\"Pune\",\"Company Name\":\"Bitwise\",\"Full Name\":\"Rohin Patel\",\"Excel\":\"CSM\"},{\"Email\":\"adhitya24@gmail.com\",\"Phone Number\":\"943054358\",\"Registration No\":\"CSD-PUN-15032\",\"Location\":\"Pune\",\"Company Name\":\"SunGard\",\"Full Name\":\"Adithya\",\"Excel\":\"CSM\"},{\"Email\":\"navdeep.ece@gmail.com\",\"Phone Number\":\"983249838\",\"Registration No\":\"CSD-PUN-15033\",\"Location\":\"Pune\",\"Company Name\":\"SunGard\",\"Full Name\":\"Navdeep\",\"Excel\":\"CSD\"},{\"Email\":\"sandeep.cyberdanes@gmail.com\",\"Phone Number\":\"239847324\",\"Registration No\":\"CSD-PUN-15034\",\"Location\":\"Bengaluru\",\"Company Name\":\"SunGard\",\"Full Name\":\"Sandeep\",\"Excel\":\"CSD\"},{\"Email\":\"narendra86@gmail.com\",\"Phone Number\":\"2398702\",\"Registration No\":\"CSD-PUN-15035\",\"Location\":\"Chennai\",\"Company Name\":\"SunGard\",\"Full Name\":\"Narendra\",\"Excel\":\"CSPO\"}]";
			assertEquals(expected, json.toString());
		}
		catch (Exception ex)
		{
			fail();
		}
	}
	
	public void testSaveValueNull()
	{
		try
		{
			ExcelToJSONConverter conv = new ExcelToJSONConverter();
			JSONArray json = null;
			
			assertEquals(false, conv.saveToDB(json));
		}
		catch (Exception ex)
		{
			fail();
		}
	}
	
	public void testSaveValue()
	{
		try
		{
			ExcelToJSONConverter conv = new ExcelToJSONConverter();
			JSONArray json = new JSONArray();
			
			assertEquals(false, conv.saveToDB(json));
		}
		catch (Exception ex)
		{
			fail();
		}
	}
}
