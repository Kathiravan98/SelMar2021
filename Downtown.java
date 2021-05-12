package AutomationTestingTask;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVWriter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Downtown {


	public static void main(String[] args) throws IOException {

		//setup for the chrome driver
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		 //to get url
		 driver.get("https://downtowndallas.com/experience/stay/");
		
		 //introducing csv writer with path
		 CSVWriter write = new CSVWriter(new FileWriter("data/sample1.csv"));
		 String area;
		//to close the popup
		 WebElement element = driver.findElement(By.xpath("//a[@href='/wearedowntown/']"));
		 element.click();
		 driver.navigate().back();
		
		  //to get the details of the hotels
		  int size = driver.findElements(By.xpath("//a[@class='place-square__btn']")).size();
		  List<String[]> list1 = new ArrayList<String[]>();
		 
		  for (int i = 1; i <= size; i++)
		  {
			
			 //taking snap of the hotel
			 File file = driver.getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(file, new File ("./Hotelsnaps/snaps.jpg"));
			 driver.findElement(By.xpath("(//a[@class='place-square__btn'])["+i+"]")).click();
			 //to get place
			 String place = driver.findElement(By.className("place-name")).getText();
			 //to get address
			 String address = driver.findElement(By.xpath("//div[@class='place-info-address']//a")).getText();
			 //to get phone number
			 String phone = driver.findElement(By.xpath("(//div[@class='place-info-address']//a)[2]")).getText();
			 //to get image
			 WebElement image = driver.findElement(By.xpath("//div[@class='place-info-image']//img[1]"));
			 String url=image.getAttribute("src");
			 //to taking screenshot
			 File memory = driver.getScreenshotAs(OutputType.FILE);
			 FileUtils.copyFile(memory, new File("./Hotelsnaps/snap.jpg"));

			/*if(driver.findElement(By.xpath("(//div[@class='place-info-address']//a)[3]"))!= null)
			 			 {
				  area  = driver.findElement(By.xpath("(//div[@class='place-info-address']//a)[3]")).getText();
				 } 
			 else
			 {
				  area = " ";
				 }*/
			String lists[] = {place, address, phone, url};
			list1.add(lists);
			 
			/* System.out.println("test is "+list1); */
			// list1.add(place);
			 //list1.add(address);
			 //list1.add(phone);
			 //list.add(area);
			 //list1.add(url);
			 write.writeAll(list1);
			 write.flush();
			// Thread.sleep(2000);
			 driver.navigate().back();
			 
		 	}
		 		
		 		System.out.println(list1);
	}

}