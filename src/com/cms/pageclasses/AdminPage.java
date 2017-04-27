package com.cms.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdminPage {
	
	WebDriver driver;
	
	@FindBy(xpath="//div[contains(text(),'First Name')]/label")
	private WebElement fname;
	
	@FindBy(xpath="//div[contains(text(),'Last Name')]/label")
	private WebElement lname;
	
	@FindBy(xpath="//div[contains(text(),'SSN')]/label")
	private WebElement ssn;
	
	@FindBy(id="edit")
	private WebElement editAdminBtn;
	
	@FindBy(id="btnStudentList")
	private WebElement manageStudentsBtn;
	
	@FindBy(id="btnCourseList")
	private WebElement manageCoursesBtn;
	
	@FindBy(id="btnCourseOfferingList")
	private WebElement manageCourseOfferingsBtn;
	
	@FindBy(id="btnViewAlerts")
	private WebElement managePermissionsBtn;
	
	@FindBy(id="btnEditSemester")
	private WebElement manageSemestersBtn;
	
	@FindBy(id="btnEnforceDropDeadline")
	private WebElement enforceBtn;

	public AdminPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getFirstName(){
		WebDriverWait wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.elementToBeClickable(fname));
		return fname.getText();
	}
	
	public String getLastName(){
		return lname.getText();
	}
	
	public String getSSN(){
		return ssn.getText();
	}
	
	public void editAdminDetails(){
		editAdminBtn.click();
	}
	
	public void manageStudents(){
		manageStudentsBtn.click();
	}
	
	public void manageCourses(){
		manageCoursesBtn.click();
	}
	
	public void manageCourseOfferings(){
		manageCourseOfferingsBtn.click();
	}
	
	public void managePermissions(){
		managePermissionsBtn.click();
	}
	
	public void manageSemesters(){
		this.manageSemestersBtn.click();
	}
	
	public void enforceDeadline(){
		this.enforceBtn.click();
	}
	
	

}
