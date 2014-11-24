package com.paeobjects.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.paeobjects.navbar.NavBar;
import com.selenium.SafeActions;

public class Home extends SafeActions{
	
	public Home(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}



	private WebDriver driver;
	
	
	
	private By userLogo(){
		return By.xpath("//img[@id='userProfileImg']");
	}
	
	private By close(){
		return By.xpath("//button[@title='close']/span");
	}
	
	private By logout(){
		return By.linkText("logout");
	}
	
	public boolean veriifyUserLogo(){
		return isElementPresent(userLogo());
	}
	
	public void doclose(){
		safeClick(close(), MEDIUMWAIT);
	}
	public NavBar doLogout(){
		try{Thread.sleep(5000);}catch (Exception e) {}
		mouseHover(userLogo(), MEDIUMWAIT);
		safeClick(logout(), MEDIUMWAIT);
		return new NavBar(driver);
	}
	
	

}
