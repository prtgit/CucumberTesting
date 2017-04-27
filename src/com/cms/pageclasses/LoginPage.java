package com.cms.pageclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	@FindBy(id="userName")
	private WebElement uname;
	
	@FindBy(id="password")
	private WebElement password;
	
	@FindBy(id="btnLogin")
	private WebElement buttonLogin;
	
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterUsername(String username){
		uname.clear();
		uname.sendKeys(username);
	}
	
	public void enterPassword(String password){
		this.password.clear();
		this.password.sendKeys(password);
	}
	
	public void hitLogin(){
		buttonLogin.click();
	}

}
