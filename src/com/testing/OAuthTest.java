package com.testing;

import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OAuthTest {
	
	public static void main(String[] args) {
		
		/*
		 * System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		 * WebDriver driver = new ChromeDriver(); driver.manage().window().maximize();
		 * String url=
		 * "https://accounts.google.com/o/oauth2/v2/auth/identifier?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyfjdss&service=lso&o2v=2&flowName=GeneralOAuthFlow";
		 * driver.get(url);
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(
		 * "manojk.eligeti");
		 * driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER
		 * ); driver.findElement(By.cssSelector("input[type='password']")).sendKeys(
		 * "Alpha_132");
		 * driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.
		 * ENTER);
		 */
		String currentUrl="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0AVHEtk4-7USB6eKjhMU3s1UyEKhcWJI9Z5gglD-AdUjdNO2uP0uWPNamz8lDyzwvHEf64g&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=2&prompt=consent";
		String partialcode=currentUrl.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		
		String accessKeyResponse=given().urlEncodingEnabled(false).queryParams("code",code).
		queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php").
		queryParams("grant_type","authorization_code").
		when().log().all().post("https://www.googleapis.com/oauth2/v4/token").asString();
		
		JsonPath js = new JsonPath(accessKeyResponse);
		String accessToken=js.getString("access_token");
		
		
		String response=given().queryParam("access_token", accessToken).
		when().log().all().get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(response);
	}

}
