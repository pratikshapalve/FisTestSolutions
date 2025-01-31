package testselenium;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Ebay {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//1. Open browser		
		WebDriver driver = new ChromeDriver();
		
		//2. Navigate to ebay.com
		driver.navigate().to("https://www.ebay.com/");
		driver.manage().window().maximize();
		
		//3.Search for ‘book’
		WebElement Search_Box = driver.findElement(By.xpath("//input[@id='gh-ac']"));
		Search_Box.sendKeys("book");
		
		WebElement Search_btn = driver.findElement(By.xpath("//span[@class='gh-search-button__label']"));
		Search_btn.click();

		//4. Click on the first book in the list
		WebElement First_book = driver.findElement(By.xpath("//span[contains(text(),'3 BOOKS: QUOTATIONS FROM CHAIRMAN MAO+FIVE ARTICLE')]"));
		First_book.click();
	
		Set<String> windowIDs = driver.getWindowHandles();
		Iterator<String> it = windowIDs.iterator();
		String Parent_Tab = it.next();
		String child_Tab = it.next();
		
		//5. In the item listing page, click on ‘Add to cart’
		driver.switchTo().window(child_Tab);
		System.out.println(driver.getTitle());
		WebElement Add_to_Cart = driver.findElement(By.xpath("//span[contains(text(),'Add to cart')]"));
		Add_to_Cart.click();
		
		
		//6. verify the cart has been updated and displays the number of items in the cart as shown below in yellow.
		WebElement book = driver.findElement(By.xpath("//a[contains(text(),'3 BOOKS: QUOTATIONS FROM CHAIRMAN MAO+FIVE ARTICLE')]"));
		System.out.println( book.isDisplayed());
		System.out.println("Book successfully added to the cart.");
	
		WebElement cart = driver.findElement(By.xpath("//a[@href='https://cart.ebay.com']"));
		System.out.println("number of items in the cart - "+ cart.getText());
		Assert.assertEquals(cart.getText(),"1");
		
		driver.quit();
	}

}
