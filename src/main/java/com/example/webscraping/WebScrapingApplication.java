package com.example.webscraping;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootApplication
public class WebScrapingApplication {

	public static void main(String[] args) {

		//SpringApplication.run(WebScrapingApplication.class, args);
		try{
		String url = "https://www.barbora.lt/darzoves-ir-vaisiai/vaisiai-ir-uogos";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
// optional request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		System.out.println("Response code: " + responseCode);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);}
		in.close();
		String html = response.toString();
			Document doc = Jsoup.parse(html);
//			Elements links = doc.select("a[href]");
//			//System.out.println(doc);
//			Elements prices = doc.select("span[content]");
//			for (Element link : links) {
//				String href = link.attr("href");
//				System.out.println(href);
//			}
//			for (Element price : prices) {
//				System.out.println(price);
//				String prc = price.attr("content");
//				System.out.println(prc);
//			}

						Elements spans = doc.select("img");
			for (Element link : spans) {
				String href = link.attr("alt");
				System.out.println(href);
			}
		}
		catch(Exception e){
			e.getMessage();
		}
	}

}
