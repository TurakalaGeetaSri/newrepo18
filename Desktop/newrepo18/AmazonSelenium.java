import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;	
public class AmazonSelenium {
public static void main(String[] args) throws InterruptedException {
System.setProperty("webdriver.chrome.driver", "C:\\Users\\Geetu\\Desktop\\Selenium\\chromedriver.exe");
WebDriver driver=new ChromeDriver();
driver.get("https://www.amazon.com");
driver.findElement(By.id("twotabsearchtextbox")).sendKeys("Refrigerator");
driver.findElement(By.id("nav-search-submit-button")).click();
driver.get("https://www.amazon.in/s?k=refrigerator&ref=nb_sb_noss_2");
Thread.sleep(200);
driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]")).click();
Thread.sleep(200);
String refrigaratorString, searchPagePrice = "", productPageUrl = "";	//
for(int i=5; i<19; i++) { 
						//*[@id="HLCXComparisonTable"]/tbody/tr[20]/th/span
						//*[@id="HLCXComparisonTable"]/tbody/tr[19]/th/span
String xPath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[2]/div[2]/div/div/div[1]/h2/a/span";
if( driver.findElements(By.xpath(xPath)).size() > 0 ) {
WebElement searchResult = driver.findElement(By.xpath(xPath));
refrigaratorString = searchResult.getText();
System.out.println(refrigaratorString);
if(refrigaratorString.contains("Samsung 198 L 4")) {
String priceXpath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[2]/div[2]/div/div/div[3]/div[1]/div/div[1]/div[1]/a/span[1]/span[2]/span[2]";
WebElement searchPriceResult = driver.findElement(By.xpath(priceXpath));
searchPagePrice = searchPriceResult.getText();
String searchPageAnchorXpath = "//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[" + i + "]/div/span/div/div/div[2]/div[2]/div/div/div[1]/h2/a";
productPageUrl = driver.findElement(By.xpath(searchPageAnchorXpath)).getAttribute("href");
 searchResult.click();
break;
}
Thread.sleep(100);
}}
ChromeOptions opt = new ChromeOptions();
 opt.addArguments("headless");
WebDriver chromeDriver1 = new ChromeDriver(opt);
chromeDriver1.manage().timeouts();
chromeDriver1.get(productPageUrl);
String productXpath = "//*[@id=\"priceblock_ourprice\"]";
//String productXpath="span.a-size-medium.a-color-base.a-text-normal";

String productPagePrice = chromeDriver1.findElement(By.xpath(productXpath)).getText().substring(1,7);
System.out.println();
						
						System.out.println( "Search Page Price = " + searchPagePrice);
						System.out.println();
						System.out.println( "Product Page Price = " + productPagePrice);
						System.out.println();
						
						if( searchPagePrice.equals(productPagePrice)) {
							System.out.println("Test Pass");
						}else {
							System.out.println("Test Fail");
						}
						
						System.out.println();
						//
						String comparisionString = "";
						for(int i=2; i<25; i++) { 
							//*[@id="HLCXComparisonTable"]/tbody/tr[19]/th/span
							String xPath = "//*[@id=\"HLCXComparisonTable\"]/tbody/tr[" + i + "]/th/span";
							if( chromeDriver1.findElements(By.xpath(xPath)).size() > 0 ) {
									WebElement weightResult = chromeDriver1.findElement(By.xpath(xPath));
									comparisionString = weightResult.getText();
									if(comparisionString.equals("Capacity")) {
										System.out.print("Capacity =  ");
										String capacityXpath = "//*[@id=\"HLCXComparisonTable\"]/tbody/tr[" + i + "]/td[1]/span";
										System.out.println(chromeDriver1.findElement(By.xpath(capacityXpath)).getText());
										System.out.println();
									}
									if(comparisionString.equals("Item Weight")) {
										String weights = "";
										System.out.print("weights of all the Items from Comparision Table  -  ");
										for(int j=1; j<=4; j++) {
											String weightXpath = "//*[@id=\"HLCXComparisonTable\"]/tbody/tr[" + i + "]/td[" + j + "]/span";
											if(chromeDriver1.findElements(By.xpath(weightXpath)).size() > 0) {
												weights = chromeDriver1.findElement(By.xpath(weightXpath)).getText();
												System.out.print( weights + ",  ");
											}
										}
										break;
									}
									Thread.sleep(100);
							}
						}
						System.out.println();
					


	}

}
