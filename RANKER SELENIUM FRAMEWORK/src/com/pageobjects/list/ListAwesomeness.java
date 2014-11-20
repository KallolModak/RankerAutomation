package com.pageobjects.list;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.base.AppGlobalVariables;
import com.selenium.SafeActions;

public class ListAwesomeness extends SafeActions{

	private WebDriver driver;
	
	private By nameMyList(String lstName){
		return By.xpath("//span[text()='"+lstName+"']");
	}
	
	public ListAwesomeness(WebDriver driver){
		super(driver);
		this.driver=driver;
	}
	
	public boolean verifyListName(String...lstName){
		String lstNm="";
		if(lstName.length<=0)
			{
			lstNm=AppGlobalVariables.CREATELIST_DEFAULTLISTNAME;
			}
		else
			lstNm=lstName[0];
		return isElementPresent(nameMyList(lstNm), LONGWAIT);
	}
}
