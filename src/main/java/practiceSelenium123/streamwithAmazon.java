package practiceSelenium123;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class streamwithAmazon {
	public static WebDriver driver;
	public static void main(String[] args) {
		
		driver=WebDriverManager.chromedriver().create();
		
		driver.get("https://www.amazon.com");
		List<WebElement> eleList=driver.findElements(By.tagName("a"));
		List<String> linknames=eleList.stream().map(element->element.getText()).filter(x->x.startsWith("S")).collect(Collectors.toList());
		
		
		commenting for practice DEF
		//List<String> l2=linknames.stream().filter(x->x.startsWith("S")).collect(Collectors.toList());
		
		//linknames.forEach(System.out::println);
		List<String> l1=new ArrayList<>();
		for(WebElement e: eleList) {
			String url=e.getAttribute("href");
			l1.add(url);
			
		}
		l1.stream().parallel().forEach(e->checkBrokenLinks(e));
		//checkBrokenLinks(url);
		driver.close();
		System.out.println("Reached End");
		
	}
	public static void checkBrokenLinks(String linkurl) {
		
		try {
			URL url =new URL(linkurl);
			HttpURLConnection con=(HttpURLConnection) url.openConnection();
			con.setConnectTimeout(5000);
			con.connect();
			if(con.getResponseCode()>=400) {
				System.out.println(linkurl+": Link is broken"+con.getResponseMessage());
			}else {
				System.out.println(linkurl+" "+con.getResponseCode());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
