package com.actions;

import com.pages.BuzzPage;

public class BuzzActions extends BaseActions {

	BuzzPage buzzPage = new BuzzPage();

	public void navigateToBuzz() {

		buzzPage.clickBuzzMenu();
	}

	public void enterBuzzContent(String content) {

		buzzPage.enterBuzzContent(content);
	}

	public void clickPostButton() {

		buzzPage.clickPostButton();
	}

	public boolean verifyPostCreated(String content) {

		return buzzPage.isPostDisplayed(content);
	}

	public boolean verifyPostButtonDisabled() {

		return !buzzPage.isPostButtonEnabled();
	}
	
	public int getBuzzPostCount() {

	    return buzzPage.getPostCount();
	}
}