package com.cmstestcases;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.cms.pageclasses.AdminPage;
import com.cms.pageclasses.LoginPage;
import com.cmsdb.DAO;
import com.cmsdb.DAOImpl;
import com.ncsu.cms.bean.AdminBean;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;


public class LoginTest {
	WebDriver driver;
	LoginPage login;
	@Given("^CMS App is started in Google Chrome$")
	public void CMS_App_is_started_in_Google_Chrome() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\Selenium 2.44\\chromedriver.exe");
		
		ChromeOptions options = new ChromeOptions();
	     options.addArguments("disable-infobars");
	     options.addArguments("--disable-extensions");
	     options.addArguments("--disable-notifications");
	     options.addArguments("--start-maximized");
	     options.addArguments("--disable-web-security");
	     options.addArguments("--no-proxy-server");
	     options.addArguments("--enable-automation");
	     options.addArguments("--disable-save-password-bubble");

	     Map<String, Object> prefs = new HashMap<String, Object>();
	     prefs.put("credentials_enable_service", false);
	     prefs.put("profile.password_manager_enabled", false);
	     options.setExperimentalOption("prefs", prefs);
		
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		driver = new ChromeDriver(capabilities);
		driver.get("http://localhost:8081/CourseManagementSystem/");
		driver.manage().window().maximize();
	}

	@When("^Enter valid admin credentials$")
	public void Enter_valid_admin_credentials() throws Throwable {
		login = new LoginPage(driver);
		login.enterUsername("alby");
		login.enterPassword("hogwartso");
		
	    
	}

	@Then("^Admin should be able to login successfully$")
	public void Admin_should_be_able_to_login_successfully() throws Throwable {
		DAO cmsDB = new DAOImpl();
		AdminPage adminPage = new AdminPage(driver); 
		int adminId = cmsDB.getUserDetails("alby");
		AdminBean adminDetails = cmsDB.getAdminDetails(adminId);
		login.hitLogin();
        System.out.println(adminPage.getFirstName().equals(adminDetails.getFirstName()));
		assert(adminPage.getFirstName().equals(adminDetails.getFirstName()));
		
		
		
	}

}
