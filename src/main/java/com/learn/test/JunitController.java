package com.learn.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JunitController {

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
