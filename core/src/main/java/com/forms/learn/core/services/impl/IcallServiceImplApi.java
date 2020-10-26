package com.forms.learn.core.services.impl;

import java.io.BufferedReader; 
import java.io.IOException; 
import java.io.InputStream; 
import java.io.InputStreamReader; 
import java.io.OutputStream; 
import java.net.HttpURLConnection; 
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;

import com.forms.learn.core.services.IcallServiceApi;



@Component(
	immediate = true,
	service  = IcallServiceApi.class
	)

public class IcallServiceImplApi implements IcallServiceApi{
	@Override
	public JSONObject myGetRequest() throws JSONException
	{
		try
		{
			//https://api.github.com/users/mojombo
			//System.out.println("GET NOT WORKED");
	        // print result
	        //System.out.println("JSON String Result " + response.toString());
			URL urlForGetRequest = new URL("https://api.github.com/users/mojombo");
			String readLine = null;
			String out_value="Fixed value trying";
			HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
			conection.setRequestMethod("GET");
			int responseCode = conection.getResponseCode();
			
			if (responseCode == HttpURLConnection.HTTP_OK) 
			{

				BufferedReader in = new BufferedReader(
				new InputStreamReader(conection.getInputStream()));
				StringBuffer response = new StringBuffer();
				while ((readLine = in .readLine()) != null) 
				{
					response.append(readLine);
				} 
				in .close();
			
				out_value="Json String Result"+response.toString(); 
				
				return new JSONObject(response.toString());
			} else 
				return new JSONObject("Not Working"); 
			
		}
		catch(Exception e)
		{
    			//e.printStackTrace(); 
			/*
			 * try { return new JSONObject("Error Code:Invalid output"); } catch
			 * (JSONException e1) { // TODO Auto-generated catch block e1.printStackTrace();
			 * }
			 *	//System.out.println("GET NOT WORKED");
        		// print result
        		//System.out.println("JSON String Result " + response.toString());
			 */
		}
		return new JSONObject("Not Working"); 
		}
}
