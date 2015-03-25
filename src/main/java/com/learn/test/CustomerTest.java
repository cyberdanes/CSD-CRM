package com.learn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import junit.framework.TestCase;

public class CustomerTest extends TestCase{
	
	public void testExceltoDB()
	{
		try
		{
			String inputUrl="http://localhost:8080/exportExcelToDB";
			String json = callUrl(inputUrl);
			String expected = 
					"[{\"Course\":\"CSM\",\"Email\":\"rohin.patel@gmail.com\",\"Phone Number\":\"928398433\",\"Registration No\":\"CSD-PUN-15031\",\"Location\":\"Pune\",\"Company Name\":\"Bitwise\",\"Full Name\":\"Rohin Patel\"},{\"Course\":\"CSM\",\"Email\":\"adhitya24@gmail.com\",\"Phone Number\":\"943054358\",\"Registration No\":\"CSD-PUN-15032\",\"Location\":\"Pune\",\"Company Name\":\"SunGard\",\"Full Name\":\"Adithya\"},{\"Course\":\"CSD\",\"Email\":\"navdeep.ece@gmail.com\",\"Phone Number\":\"983249838\",\"Registration No\":\"CSD-PUN-15033\",\"Location\":\"Pune\",\"Company Name\":\"SunGard\",\"Full Name\":\"Navdeep\"},{\"Course\":\"CSD\",\"Email\":\"sandeep.cyberdanes@gmail.com\",\"Phone Number\":\"239847324\",\"Registration No\":\"CSD-PUN-15034\",\"Location\":\"Bengaluru\",\"Company Name\":\"SunGard\",\"Full Name\":\"Sandeep\"},{\"Course\":\"CSPO\",\"Email\":\"narendra86@gmail.com\",\"Phone Number\":\"2398702\",\"Registration No\":\"CSD-PUN-15035\",\"Location\":\"Chennai\",\"Company Name\":\"SunGard\",\"Full Name\":\"Narendra\"}]";
			assertEquals(expected, json);
		}
		catch (Exception ex)
		{
			fail();
		}
	}
	
	public void testUrlFilterByLocation()
	{
		String inputUrl="http://localhost:8080/findByLocation?location=Pune";
		try
		{
			String output = callUrl(inputUrl);
			CharSequence cs= "Pune";
			boolean rslt = output.contains(cs);
			assertTrue(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	public void testUrlFilterByLocNegativeScenario()
	{
		String inputUrl="http://localhost:8080/findByLocation?location=Pune";
		try
		{
			String output = callUrl(inputUrl);
			CharSequence cs= "Bengaluru";
			boolean rslt = output.contains(cs);
			assertFalse(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	
	public void testUrlFilterByCourse()
	{
		String inputUrl="http://localhost:8080//findByCourse?courseName=CSD";
		try
		{
			String output = callUrl(inputUrl);
			CharSequence cs= "CSD";
			boolean rslt = output.contains(cs);
			assertTrue(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	public void testUrlFilterByCourseNegativeScenario()
	{
		String inputUrl="http://localhost:8080//findByCourse?courseName=CSD";
		try
		{
			String output = callUrl(inputUrl);
			CharSequence cs= "CSM";
			boolean rslt = output.contains(cs);
			assertFalse(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	public String callUrl(String inputUrl) throws Exception{
		URLConnection urlCon = null;
		InputStreamReader in = null;
		BufferedReader br=null;
		StringBuilder strBuilder = new StringBuilder();
		URL url = new URL(inputUrl);
		urlCon = url.openConnection();
		if(urlCon != null && urlCon.getInputStream() != null)
		{
			in = new InputStreamReader(urlCon.getInputStream(), Charset.defaultCharset());
			br = new BufferedReader(in);
			if(br != null)
			{
				int cp;
				while((cp=br.read()) !=-1)
				{
					strBuilder.append((char)cp);
				}
				br.close();
			}
		}
		in.close();
		return strBuilder.toString();
	}

}
