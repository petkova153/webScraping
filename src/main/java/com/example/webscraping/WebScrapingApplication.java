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
		String url = "https://www.rimi.lt/e-parduotuve/lt/produktai/vaisiai-darzoves-ir-geles/vaisiai-ir-uogos/c/SH-15-3?page=1&pageSize=80&query=%3Arelevance%3AallCategories%3ASH-15-3%3AassortmentStatus%3AinAssortment";
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

//			//barbora
//			Elements products  = doc.select("div.b-product--wrap2");
//
//			for (Element product : products){
//
//				Elements spans = product.select("img");
//				for (Element link : spans) {
//					String href = link.attr("alt");
//					System.out.println(href);
//				}
//
//				Elements prices = product.select("span.b-product-price-current-number");
//				for (Element price : prices) {
//					String prc = price.attr("content");
//					System.out.println(prc);
//				}
//			}

//iki
//			Elements products  = doc.select("div.css-jpvoog");
//			System.out.println(doc);
//			for (Element product : products){
//
//				Elements spans = product.select("p.css-abaudk");
//				for (Element link : spans) {
//					System.out.println(link);
//				}
//
//				Elements prices = product.select("p.css-lrnbrk");
//				for (Element price : prices) {
//					System.out.println(price);
//				}
//			}

//rimi
			Elements products  = doc.select("li.product-grid__item");
			for (Element product : products){

				Elements spans = product.select("p.card__name");
				for (Element link : spans) {
					String pName = link.toString();
					String picture = pName.substring(pName.indexOf("e\">")+3,pName.indexOf("</"));
					System.out.println(picture);
				}

				Elements prices = product.select("p.card__price-per");
				for (Element price : prices) {
					String pPrice = price.toString();
					String cleanPrice = pPrice.substring(pPrice.indexOf("r\">")+3,pPrice.indexOf("</"));
					System.out.println(cleanPrice);
				}
			}

		}
		catch(Exception e){
			e.getMessage();
		}
	}

}
