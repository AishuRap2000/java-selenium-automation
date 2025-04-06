package com.javaselenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SwagLabs {
	static WebDriver driver;

	public void Browser() {

		// set path to chrome driver
		System.setProperty("webdriver.chrome.driver",
				"E:\\Aishwarya\\eclipse\\drivers\\chromedriver-win32\\chromedriver.exe");

		// Open Chrome Browser
		driver = new ChromeDriver();
	}

	public void OpenWebsite() {
		// Navigate to wevsite
		driver.get("https://www.saucedemo.com/v1/");
		// print the title of the webpage
		System.out.println("Page Title : " + driver.getTitle());

		// maximize the win
		driver.manage().window().maximize();

	}

	public void Login() {
		// username
		WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
		username.sendKeys("standard_user");

		// password
		driver.findElement(By.name("password")).sendKeys("secret_sauce");

		// clicking on login button
		driver.findElement(By.id("login-button")).click();
	}

	public void VerifyHeader() {
		String Actualheader = driver.findElement(By.xpath("//div[@class='product_label']")).getText();
		System.out.println("Header - " + Actualheader);
		String expectedheader = "Products";
		Assert.assertEquals(Actualheader, expectedheader);
	}

	public void SelectSortDropdown() {
		// select sort dropdown using select method
		WebElement dropdown = driver.findElement(By.className("product_sort_container"));
		Select dd = new Select(dropdown);

		// select by visible txt
		dd.selectByVisibleText("Name (Z to A)");
		// select by value
		dd.selectByValue("hilo");
		// select by index
		dd.selectByIndex(2);

		// verify selected op tion
		String selectDDOption = dd.getFirstSelectedOption().getText();
		Assert.assertEquals(selectDDOption, "Price (low to high)");

		// wait until page fully loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState")
				.equals("complete"));
	}

	public void ClickProductDetail() throws InterruptedException {
		/*
		 * To click on the product link details Getting the size of the total products
		 * and click on the expected product using the for loop
		 */

		String Product = "Sauce Labs Bolt T-Shirt";
		int productsSize = driver.findElements(By.xpath("//div[@class='inventory_item_label']/a/div")).size();
		for (int i = 1; i <= productsSize; i++) {
			WebElement product = driver.findElement(By.xpath("(//div[@class='inventory_item_label']/a/div)[" + i + "]"));
			String ActualProduct = product.getText();
			Thread.sleep(1000);
			if(ActualProduct.equalsIgnoreCase(Product)) {
				product.click();
				break;
			}
		}
		// verify the product -> whether it clicked the correct product
		String ActualProduct = driver.findElement(By.xpath("//div[@class='inventory_details_name']")).getText();
		System.out.println("Header - " + ActualProduct);
		Assert.assertEquals(ActualProduct, Product);

	}
	
	public void AddToCart() {
		// Clicking on "Add To Cart" Button
		WebElement cartButton = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
		cartButton.click();
		
	}
	
	public void logout() {
		// Opening the menu and clicking on logout then verify whether redirected to the login page 
		WebElement openMenu = driver.findElement(By.xpath("//button[text()='Open Menu']"));
		openMenu.click();
		
		WebElement logout = driver.findElement(By.xpath("//a[text()='Logout']"));
		logout.click();
		
		WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
		username.isDisplayed();
	}

	public void closeBroswer() {
		// To close the browser window
		driver.quit();
	}

	// Main Function
	public static void main(String[] args) throws InterruptedException {
		SwagLabs labs = new SwagLabs();
		labs.Browser();
		labs.OpenWebsite();
		labs.Login();
		labs.VerifyHeader();
		labs.SelectSortDropdown();
		labs.ClickProductDetail();
		labs.AddToCart();
		labs.logout();
		labs.closeBroswer();
	}

}
