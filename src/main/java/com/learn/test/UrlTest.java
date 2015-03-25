package com.learn.test;

import junit.framework.TestCase;

public class UrlTest extends TestCase{
	
	
	public void testUrl()
	{
		String inputUrl="http://localhost:8080/greeting";
		JunitController junit = new JunitController();
		try
		{
			String result = junit.callUrl(inputUrl);
			assertNotNull(result);
		}
		catch(Exception e)
		{
			fail();
		}
	}

	public void testUrlFilterByLocation()
	{
		String inputUrl="http://localhost:8080/findByLocation?location=Pune";
		JunitController junit = new JunitController();
		try
		{
			String output = junit.callUrl(inputUrl);
			CharSequence cs= "Rohin Patel";
			boolean rslt = output.contains(cs);
			assertTrue(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
	
	
	public void testUrlFilterByCourse()
	{
		String inputUrl="http://localhost:8080//findByCourse?courseName=CSD";
		JunitController junit = new JunitController();
		try
		{
			String output = junit.callUrl(inputUrl);
			CharSequence cs= "Rohin Patel";
			boolean rslt = output.contains(cs);
			assertTrue(rslt);
		}
		catch(Exception e)
		{
			fail();
		}
	}
}
