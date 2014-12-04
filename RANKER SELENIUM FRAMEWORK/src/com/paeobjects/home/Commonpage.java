package com.paeobjects.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pageobjects.slideshow.Slideshow;
import com.selenium.SafeActions;

public class Commonpage extends SafeActions{
	
	private WebDriver driver;
	public Commonpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	private By rankerLogo(){
		return By.xpath("//a[@id='logo']");
	}
	

	private By list(String i){
		return By.xpath("//section/article[contains(@class, 'relative ')]["+i+"]//img");
	}
	
	public void clickOnLogo() throws Exception{
		safeClick(rankerLogo());
		waitForPageToLoad();
	}
	
	public boolean verifyRankerLogo(){
		return isElementPresent(rankerLogo());
	}
	
	public Slideshow clickOnAList(String i){
		safeClick(list(i));
		return new Slideshow(driver);
	}
	

}
