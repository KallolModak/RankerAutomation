package com.testsuite.navbar;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.base.AppGlobalVariables;
import com.base.BaseSetup;
import com.dataprovider.ConfigManager;
import com.paeobjects.home.Home;
import com.paeobjects.navbar.NavBar;
import com.pageobject.login.AuthLoginPage;
import com.pageobject.login.FBAPI;
import com.pageobject.login.ForgotPassword;
import com.pageobject.login.GooglePlusAPI;
import com.pageobject.login.TwitterAPI;
import com.utilities.UtilityMethods;
import com.utilities.UtilityMethods.Mode;

public class SigninAndRegistration extends BaseSetup{
	
	ConfigManager config=new ConfigManager();
	
	
	String uname="";
	String email="";
	String pwd=AppGlobalVariables.PASSWORD;
	
	@BeforeClass()
	public void classSetUp(){
		
		getDriver().manage().deleteAllCookies();
		AuthLoginPage authlpg=new AuthLoginPage(getDriver());
		getDriver().get(new ConfigManager().getProperty("Url"));
		authlpg.enterCredentels(config.getProperty("Authuname"), config.getProperty("Authpwd"));
		authlpg.clickSubmit();
	}
	
	@BeforeMethod
	public void beforeMethod(){
		if(!getDriver().getCurrentUrl().equals(new ConfigManager().getProperty("Url")))
		getDriver().get(new ConfigManager().getProperty("Url"));
	}
	
	@Test(priority=1)
	public void createNewAccounstInvalidCredentials(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickOnJoining();
		navbar.clickSignupByEmail();
		uname=AppGlobalVariables.USERS_AUTOMATIONUSER1;
		email=UtilityMethods.generateRandomString(11, Mode.ALPHA);
		pwd=UtilityMethods.generateRandomString(5, Mode.ALPHA);
		navbar.enterregistrationdetails(uname,email,pwd);
		
		//Verify error messages
		
		Assert.assertEquals(navbar.verifyUnameError(), AppGlobalVariables.ERROR_EXISTINGUSER, "Existing username Error");
		Assert.assertEquals(navbar.verifyEmailError(), AppGlobalVariables.ERROR_INVALIDEMAIL, "Invalid email Error");
		Assert.assertEquals(navbar.verifyPWDError(), AppGlobalVariables.ERROR_INVALIDPWD, "Invalid password Error");
		
	}
	
	@Test(priority=2)
	public void createNewAccounstValidCredentials(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickOnJoining();
		navbar.clickSignupByEmail();
		uname=UtilityMethods.generateRandomString(1, Mode.ALPHA)+UtilityMethods.generateRandomString(5, Mode.ALPHANUMERIC);
		email=uname+AppGlobalVariables.EMAILEXTENCTION;
		pwd=AppGlobalVariables.PASSWORD;
		navbar.enterregistrationdetails(uname,email,pwd);
		Home home=navbar.clickOnSinup();
		//Verify signup
		Assert.assertTrue(home.veriifyUserLogo(),"Verify user logo(SignUp)");
		//logout
		home.doclose();
		home.doLogout();
	}
	
	@Test(priority=13)
	public void createNewAccountWithFacebook(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickOnJoining();
		FBAPI fbapi=navbar.clickSinupByFB();
		//Verify Facebook API page URL
		Assert.assertTrue(fbapi.verifyFBHeader(), "FB header");
		Assert.assertTrue(fbapi.getCurrentURL().toLowerCase().contains(AppGlobalVariables.API_FACEBOOKURL), "Verify FB API url");
		getDriver().close();
		getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[0].toString());
	}
	
	@Test(priority=4)
	public void createNewAccountWithTwitter() throws InterruptedException{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickOnJoining();
		TwitterAPI twitterapi=navbar.clickSinupBytwitter();
		//Verify Twitter API page URL
		Assert.assertTrue(twitterapi.verifyTwitterHeader(), "Twitter header");
		Assert.assertTrue(twitterapi.getCurrentURL().toLowerCase().contains(AppGlobalVariables.API_TWITTERURL), "Verify Twitter API url");
		getDriver().close();
		getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[0].toString());
	}
	
	@Test(priority=5)
	public void createNewAccountWithGooglePlus(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clickOnJoining();
		GooglePlusAPI gplusapi=navbar.clickSinupByGPlus();
		//Verify Gooleplus API page URL
		Assert.assertTrue(gplusapi.verifyGPluslogo(), "GPlus logo");
		Assert.assertTrue(gplusapi.getCurrentURL().toLowerCase().contains(AppGlobalVariables.API_GPLUS), "Verify Google plus API url");
		getDriver().close();
		getDriver().switchTo().window(getDriver().getWindowHandles().toArray()[0].toString());
	}
	
	@Test(priority=6)
	public void forgotpassword(){
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		ForgotPassword forgotPwd=navbar.clickForgotPwd();
		Assert.assertTrue(forgotPwd.verifyForgotEmailBox(),"Forgot email box");
	}
	
	@Test(priority=7)
	public void SignIn_validcredentials() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.enterCredentels(AppGlobalVariables.USER,AppGlobalVariables.PASSWORD);
		Home home=navbar.clickSignIn();
		//verify login
		Assert.assertTrue(home.veriifyUserLogo(), "verify login");
		home.doLogout();
	}
	 
	@Test(priority=8)
	public void SignIn_invalidcredentials() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		
		navbar.enterCredentels(AppGlobalVariables.USER, UtilityMethods.generateRandomString(6, Mode.ALPHANUMERIC));
		navbar.clicksignin();
		//verify
		Assert.assertEquals(navbar.verifyPWDError(), AppGlobalVariables.ERROR_WRONG_PASSWORD, "verify error message");
	}
	
	@Test(priority=9)
	public void SignIn_leavingpasswordblank() throws Exception{
		NavBar navbar=new NavBar(getDriver());
		navbar.clickSignin();
		navbar.clicksignin();
		//verify
		Alert alert=getDriver().switchTo().alert();
		Assert.assertEquals(alert.getText(), AppGlobalVariables.WARNING_EMPTY_CREDENTIALS, "verify warning message");
		alert.accept();
	}
	
//	@Test(priority=10)
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
