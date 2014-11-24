package com.testsuite.navbar;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.AppGlobalVariables;
import com.base.BaseSetup;
import com.dataprovider.ConfigManager;
import com.paeobjects.home.Home;
import com.paeobjects.navbar.NavBar;
import com.pageobject.login.ForgotPassword;
import com.utilities.UtilityMethods;
import com.utilities.UtilityMethods.Mode;

public class SignIn extends BaseSetup{
	
	
	String uname="";
	String email="";
	String pwd=AppGlobalVariables.PASSWORD;
				
	@BeforeMethod
	public void beforeMethod(){
		if(!getDriver().getCurrentUrl().equals(new ConfigManager().getProperty("Url")))
		getDriver().get(new ConfigManager().getProperty("Url"));
	}
	
	
	@Test(priority=1)
	public void forgotpassword(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		ForgotPassword forgotPwd=navbar.clickForgotPwd();
		Assert.assertTrue(forgotPwd.verifyForgotEmailBox(),"Forgot email box");
	}
	
	@Test(priority=2)
	public void SignIn_leavingpasswordblank() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickSignIn();
		//verify
		Alert alert=getDriver().switchTo().alert();
		Assert.assertEquals(alert.getText(), AppGlobalVariables.WARNING_EMPTY_CREDENTIALS, "verify warning message");
		alert.accept();
	}
	
	@Test(priority=3)
	public void SignIn_invalidcredentials() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		if(navbar.isSigninNotPresent())
		navbar.clickSignin();
		navbar.enterCredentels(AppGlobalVariables.USER, UtilityMethods.generateRandomString(6, Mode.ALPHANUMERIC));
		navbar.clicksignin();
		//verify
		Assert.assertEquals(navbar.verifyPWDError(), AppGlobalVariables.ERROR_WRONG_PASSWORD, "verify error message");
	}
	
	@Test(priority=4)
	public void SignIn_validcredentials() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		if(navbar.isSigninNotPresent())
		navbar.clickSignin();
		navbar.enterCredentels(AppGlobalVariables.USER,AppGlobalVariables.PASSWORD);
		Home home=navbar.clickSignIn();
		//verify login
		Assert.assertTrue(home.veriifyUserLogo(), "verify login");
//		home.doLogout();
	}
	
//	@Test(priority=5)
	public void SignIn_Facebook() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickSinupByFB();
		
		navbar.clicksignin();
		//verify
		Alert alert=getDriver().switchTo().alert();
		Assert.assertEquals(alert.getText(), AppGlobalVariables.WARNING_EMPTY_CREDENTIALS, "verify warning message");
		alert.accept();
	}
}
