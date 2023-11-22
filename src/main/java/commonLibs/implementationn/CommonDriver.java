package commonLibs.implementationn;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

public class CommonDriver {
	private WebDriver driver;
	private int pageLoadTimeout;
	private int elementDetectionTimeout;
	private String currentWorkingDirectory;
	
	public CommonDriver(String browserType) throws Exception {
		pageLoadTimeout = 20;
		elementDetectionTimeout=20;
		
		currentWorkingDirectory=System.getProperty("user.dir");
		
		if(browserType.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", currentWorkingDirectory + "/driver/chromedriver");
			
			ChromeOptions options = new ChromeOptions();
			options.setPageLoadStrategy(PageLoadStrategy.NONE); // https://www.skptricks.com/2018/08/timed-out-receiving-message-from-renderer-selenium.html
			options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
			options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
			//options.addArguments("--headless"); // only if you are ACTUALLY running headless
			options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
			options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
			options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
			driver = new ChromeDriver(options);
			
			//driver = new ChromeDriver();
		}else if(browserType.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.chrome.driver", 
					currentWorkingDirectory + "/driver/Google Chrome for Testing");
			driver = new EdgeDriver();
		}else {
			throw new Exception("Invalid Browser Type"+ browserType);
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
	}
	
	public void navigateToUrl(String url) {
		driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectionTimeout, TimeUnit.SECONDS);
		
		driver.get(url);
	}
	
	public WebDriver getDriver() {
		return driver;
	}

	public void setPageLoadTimeout(int pageLoadTimeout) {
		this.pageLoadTimeout = pageLoadTimeout;
	}

	public void setElementDetectionTimeout(int elementDetectionTimeout) {
		this.elementDetectionTimeout = elementDetectionTimeout;
	}
	
	public void closeAllBrowser() {
		driver.quit();
	}
	
	public String getTitleOfThePage() {
		return driver.getTitle();
	}
}
