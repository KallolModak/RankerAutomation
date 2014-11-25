package com.testsuite.navbar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.AppGlobalVariables;
import com.base.BaseSetup;
import com.paeobjects.home.Home;
import com.paeobjects.navbar.NavBar;

public class ProfileImage extends BaseSetup{
	
	@Test(priority=1)
	public void ProfileImage_profilepage(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.enterCredentels(AppGlobalVariables.USER, AppGlobalVariables.PASSWORD);
		Home home=navbar.clickSignInBtn();
		home.clickUserLogo();
		Assert.assertTrue(home.veriifyUserLogo(),"User Image");
	}
	
	@Test
	public void ProfileImageDropdown_mylatestlists(){
		
	}

}
