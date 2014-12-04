package com.pageobjects.slideshow;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.SafeActions;

public class Slideshow extends SafeActions{
	
	private WebDriver driver;
	
	private By currentSlideNo(){
		return By.xpath("//strong[@class='pos']");
	}
	
	private By nextSlide(){
		return By.xpath("//div[@id='slideImgNext']//span");
	}
	
	private By nodeImg(){
		return By.xpath("//a[contains(@href, 'pics')]/img[contains(@src, 'user_node_img')]");
	}
	
	private By nodeImgzoom(){
		return By.xpath("//span[contains(@class, 'slideMagnify')]");
	}
	
	public Slideshow(WebDriver driver) {
		super(driver);
		this.driver=driver;
	}
	
	public String getSlideNo(){
		return waitForText(currentSlideNo(), LONGWAIT);
	}
	
	public void clickNext(){
		safeClick(nextSlide(), VERYLONGWAIT);
	}
	
	public void mousehovernodeimg(){
		mouseHover(nodeImg(), VERYLONGWAIT);
	}
	
	public String nodeImageAttribute(){
		isElementVisible(nodeImgzoom(), VERYLONGWAIT);
		return driver.findElement(nodeImgzoom()).getAttribute("style");
	}
	
	public PicsPage clickOnNodepic(){
		safeClick(nodeImg());
		return new PicsPage(driver);
	}
	
//	public void waitFoDisapperiance(){
//		waitUntilElementDisappears(locator, optionWaitTime);
//	}
}
