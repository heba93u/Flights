package com.demo;


import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
class DemoApplicationTests {

	@Test
	void contextLoads() throws IOException, InterruptedException {
		boolean res = Boolean.parseBoolean(sendGET("http://localhost:8080/api/v1/flights/checkTicketAvailability/?ticketId=99"));
		Assert.isTrue(res,"checkTicketAvailability was failed");
		sendPut("http://localhost:8080/api/v1/flights/checkIn/?destId=200&baggageId=50");
		Assert.isTrue(res,"checkIn was failed");

	}

	private static String sendGET(String GET_URL) throws IOException {
		URL obj = new URL(GET_URL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		StringBuffer response = null;
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} else {
			System.out.println("GET request not worked");
		}
		return response.toString();
	}

	private static String sendPut(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("PUT");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write("".getBytes());
		os.flush();
		os.close();
		// For POST only - END

		int responseCode = con.getResponseCode();
		StringBuffer response = null;
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} else {
			System.out.println("POST request not worked");
		}
		return response.toString() ;
	}

}
