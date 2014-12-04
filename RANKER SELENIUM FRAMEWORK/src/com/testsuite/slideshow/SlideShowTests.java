package com.testsuite.slideshow;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseSetup;
import com.paeobjects.home.Commonpage;
import com.pageobjects.slideshow.PicsPage;
import com.pageobjects.slideshow.Slideshow;

public class SlideShowTests extends BaseSetup{
	
	@Test(priority=1)
	public void Node_Ranking(){
		Commonpage common=new Commonpage(getDriver());
		Slideshow slideshow=common.clickOnAList("2");
		for(int i=1;i<5;i++){
			if(i>1){
				slideshow.clickNext();
			}
			System.out.println("Slide No :- "+slideshow.getSlideNo());
			Assert.assertEquals(String.valueOf(i), slideshow.getSlideNo());
		}
		
	}
	
//	@Test(priority=1)
	public void Node_Name(){
		Commonpage common=new Commonpage(getDriver());
		Slideshow slideshow=common.clickOnAList("1");
		
	}
	
	@Test(priority=3)
	public void NodeImage_hover(){
		Slideshow slideshow=new Slideshow(getDriver());
		slideshow.mousehovernodeimg();
		String s=slideshow.nodeImageAttribute();
//		System.out.println(s);
		Assert.assertTrue(s.contains("display: inline"), "Img Zoom button");
		
	}
	
	@Test(priority=4)
	public void NodeImage_click(){
//		Commonpage common=new Commonpage(getDriver());
//		Slideshow slideshow=common.clickOnAList("1");
		Slideshow slideshow=new Slideshow(getDriver());
		PicsPage picspge=slideshow.clickOnNodepic();
		Assert.assertTrue(picspge.verifyPicsGallery(), "Pics Gallery in Pics page");
		picspge.hoveronSlideNxt();
		String s=picspge.nxtBGround();
		System.out.println(s);
		
	}

}
