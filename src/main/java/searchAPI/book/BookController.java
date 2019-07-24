package searchAPI.book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import searchAPI.book.entity.Keyword;

@Controller
public class BookController {

	private final String API_URL = "https://dapi.kakao.com/v3/search/book", // 문서의 v2는 404발생
			API_KEY = "71980ed5e6a6c4f806d496a3fe016ce9", API_SUB_URL = "https://openapi.naver.com/v1/search/book.json";

	@Autowired
	private KeywordService keywordService;

	@RequestMapping("/search")
	@ResponseBody
	public String search(@RequestParam(required = false) String target, @RequestParam(required = false) String page, @RequestParam String keyword) {
		HttpURLConnection con = null;
		String result = "fail to search book";
		try {
			String url = API_URL + "?query=" + URLEncoder.encode(keyword, "UTF-8");
			if (target != null && !target.isEmpty())
				url += "&target=" + target;
			if (page != null && !page.isEmpty())
				url += "&page=" + page;
			System.out.println(url);

			URL object = new URL(url);
			con = (HttpURLConnection) object.openConnection();

			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept-Charset", "UTF-8");
			con.setRequestProperty("X-Requested-With", "XMLHttpRequest");
			con.setRequestProperty("Authorization", "KakaoAK " + API_KEY);
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
			String inputLine = null;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}

			con.disconnect();

			if (con.getResponseCode() == 200) {
				result = response.toString();
				keywordService.counting(keyword);
			}
			in.close();

		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			con.disconnect();
		}

		return result;
	}

	@RequestMapping("/keywordTop10")
	@ResponseBody
	public String keywordTop10() {
		Keyword[] top10 = keywordService.top10();
		String result = "[";
		for (Keyword k : top10) {
			result += "{\"keyword\":\"" + k.getKeyword() + "\", \"count\":\"" + k.getCount() + "\"},";
		}
		return result.substring(0, result.length() - 1) + "]";
	}

}
