package com.paeobjects.navbar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.pageobjects.list.ListAwesomeness;
import com.selenium.SafeActions;

public class CreateList extends SafeActions{
	
	private WebDriver driver;
	
	private By skipCreate(){
		return By.id("createSkip");
	}
	
	private By createList(){
		return By.id("createSubmit");
	}
	
	public CreateList(WebDriver driver){
		super(driver);
		this.driver=driver;
		try{Thread.sleep(3000);}catch (Exception e){}
	}
	
	public ListAwesomeness clickOnSkip(){
		safeClick(skipCreate());
		return new ListAwesomeness(driver);
	}
	
	public ListAwesomeness clickOnCreate(){
		safeClick(createList());
		return new ListAwesomeness(driver);
	}
	

}
