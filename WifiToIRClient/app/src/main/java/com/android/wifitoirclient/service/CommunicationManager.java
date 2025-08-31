package com.android.wifitoirclient.service;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import com.android.wifitoirclient.LoginActivity;
import com.android.wifitoirclient.StartActivity;



public class CommunicationManager
{
	private DataOutputStream out = null;
	private DataInputStream in = null;
	private Socket servSocket = null;

	public CommunicationManager () throws Exception
	{    	   	
		servSocket = new Socket(StartActivity.serverIp,80);
		out = new DataOutputStream(servSocket.getOutputStream());
		in = new DataInputStream(servSocket.getInputStream());
	}

	public void testAction(String sData)
	{
		try
		{
			out.writeBytes(sData.split(",").length + "," + sData);
			out.flush();
			out.close();
			servSocket.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public String trainAction()
	{
		try
		{
				out.writeBytes("a");
				out.flush();

				String sData = in.readLine() ; //new String(data);
				sData = sData.substring(sData.indexOf("{")+1, sData.indexOf("}"));
				sData = sData.replaceAll(" ", "");
				
				out.close();
				servSocket.close();
				return sData;
		}
		catch(Exception e)
		{
			System.out.println(e);
			e.printStackTrace();
		}
		return null;
	}   

	public static void main(String[] args) throws Exception
	{		
		//To Train
		CommunicationManager c = new CommunicationManager();
		String sData = c.trainAction();    	
		sData = sData.substring(sData.indexOf("{")+1, sData.indexOf("}"));
		sData = sData.replaceAll(" ", "");
		System.out.println(sData);

		Thread.sleep(5000);
		//To Test
		CommunicationManager cc = new CommunicationManager();
		cc.testAction(sData);

	}
}
