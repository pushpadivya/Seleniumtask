package task.selenium;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class Flipkart_task {
	WebDriver driver;
	By mobilenumber=By.className("VJZDxU");
	//By loginoption=By.className("_3AWRsL");
	By password=By.className("_3mctLh");
	By loginsubmit=By.className("_3AWRsL");
	By searchbox=By.name("q");
	By selectitem1=By.xpath("//div[text()='HP Ryzen 3 Dual Core - (8 GB/1 TB HDD/Windows 11 Home) 255 G8 Laptop']");
	By selectitem2=By.xpath("//div[text()='SAMSUNG Galaxy F12 (Celestial Black, 64 GB)']");
	By addcart1=By.className("_3v1-ww");
	By addcart2=By.className("_3v1-ww");
	By removehplaptop=By.xpath("//div[text()='Remove']");
	By profileoption=By.className("exehdJ");
	By cart=By.className("_3SkBxJ");
	By hpcart=By.xpath("//a[text()='HP Ryzen 3 Dual Core - (8 GB/1 TB HDD/Windows 11 Home) 255 G8 Laptop']");
	By removepopup=By.className("FhkMJZ");
	String hptext="HP Ryzen 3 Dual Core - (8 GB/1 TB HDD/Windows 11 Home) 255 G8 Laptop";
	@Test
	public void setup() throws IOException
	{
		try
		{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\prade\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://filpkart.com");
		driver.manage().timeouts().implicitlyWait(150, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		File src=new File(".//TestData//config.properties");
		FileInputStream fis=new FileInputStream(src);
		Properties properties=new Properties();
		properties.load(fis);
		//driver.findElement(loginoption).click();
		driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String mn=properties.getProperty("mobile");
		driver.findElement(mobilenumber).sendKeys(mn);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String pass=properties.getProperty("password");
		driver.findElement(password).sendKeys(pass);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(loginsubmit).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String item1=properties.getProperty("product1");
		Actions action=new Actions(driver);
		driver.findElement(searchbox).sendKeys(item1);
		action.sendKeys(Keys.ENTER).build().perform();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(driver,15);
		wait.until(ExpectedConditions.presenceOfElementLocated(selectitem1));
		WebElement p1=driver.findElement(selectitem1);
		p1.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String parent=driver.getWindowHandle();
		Set<String>s1=driver.getWindowHandles();
		Iterator<String> I1=s1.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
		if(!parent.equals(child_window))
		{
			driver.switchTo().window(child_window);
		}
		}
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		driver.findElement(addcart1).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.close();
        driver.switchTo().window(parent);
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement search=driver.findElement(searchbox);
        String item2=properties.getProperty("product2");
        search.sendKeys(Keys.CONTROL +"a");
        search.sendKeys(Keys.DELETE);
        search.sendKeys(item2); 
        action.sendKeys(Keys.ENTER).build().perform();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	wait.until(ExpectedConditions.presenceOfElementLocated(selectitem2));
        WebElement p2=driver.findElement(selectitem2);
        p2.click(); 
        String parent1=driver.getWindowHandle();
		Set<String>s2=driver.getWindowHandles();
		Iterator<String> I2=s2.iterator();
		while(I2.hasNext())
		{
			String child_window=I2.next();
		if(!parent.equals(child_window))
		{
			driver.switchTo().window(child_window);
		}
		}
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.findElement(addcart2).click();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	//	driver.findElement(cart).click();
        driver.findElement(removehplaptop).click();
        driver.findElement(By.className("_10vWcL"));
        driver.findElement(By.xpath("//div[text()='Remove']")).click();
        driver.switchTo().alert().accept();
        driver.findElement(removepopup).click();
        WebElement hplaptopproduct=driver.findElement(hpcart);
        boolean contains=hplaptopproduct.getText().contains("HP Ryzen 3 Dual Core - (8 GB/1 TB HDD/Windows 11 Home) 255 G8 Laptop");
        Assert.assertFalse(contains);
        driver.close();
        String hpitem=driver.findElement(addcart1);
        driver.switchTo().window(parent);
        WebElement profile=driver.findElement(profileoption);
        Select s=new Select(profile);
        String logout="Logout";
        s.selectByVisibleText(logout);
        driver.close();
        System.out.println("Pass");
		}
		catch(Exception e)
		{
			e.printStackTrace();
	        System.out.println("Fail");
		}
	}
}
