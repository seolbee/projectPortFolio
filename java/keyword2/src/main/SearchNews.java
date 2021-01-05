package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class SearchNews {
	public static void main(String[] args) {
		String ci = "lGRFJ5xnXrQZZjwdK12E";//클라이언트 아이디
		String cs = "EukgY7LSN0";//클라이언트 시크릿
		String apiURL = "https://openapi.naver.com/v1/search/news.json";
		
		Scanner in = new Scanner (System.in);
		
		System.out.println("검색할 뉴스 키워드를 입력하세요");
		String word = in.nextLine();
		
		try {
			
			word = URLEncoder.encode(word, "UTF-8");
			apiURL += "?query=" + word;
			
			URL url = new URL(apiURL);
		
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			
			con.setRequestMethod("GET");
			con.setRequestProperty("X-Naver-Client-Id", ci);
			con.setRequestProperty("X-Naver-client-Secret", cs);
		
			int resCode = con.getResponseCode();
		
			BufferedReader br;
			
			if(resCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			
			String inputLine = "";
			
			StringBuffer res = new StringBuffer();
			while((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
				
			String json = res.toString();
			
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(json);
			JsonArray items = element.getAsJsonObject().get("items").getAsJsonArray();
			for(JsonElement item:items) {
				String title = item.getAsJsonObject().get("title").getAsString();
				System.out.println(title);
			}
			
//			Gson gson = new Gson();
//				
//			ResponseVO resVO = gson.fromJson(json, ResponseVO.class);
//			
//			List<ItemVO> list = resVO.getItems();
//			for(ItemVO item:list) {
//				System.out.println(item.getTitle() + "[" + item.getPubDate() + "]");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		in.close();
	}
}
