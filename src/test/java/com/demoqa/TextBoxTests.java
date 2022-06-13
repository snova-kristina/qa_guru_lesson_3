package com.demoqa;

import com.codeborne.selenide.Config;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxTests {

	@BeforeAll
	static void SetUp() {
		Configuration.holdBrowserOpen = true;
		Configuration.baseUrl = "https://demoqa.com";
		Configuration.browserSize = "1920x1080";
	}

	@Test
	void FillForm() {
		String username = "ElonMusk";
		String email = "elonmusk@spacex.com";
		String address = "Hawthorne, California, USA";
		String permanentaddress = "USA";

		Selenide.open("/text-box");

		$("[id=userName]").setValue(username);
		$("[id=userEmail]").setValue(email);
		$("[id=currentAddress]").setValue(address);
		$("[id=permanentAddress]").setValue(permanentaddress);
		$("[id=submit]").click();
		// проверяем, что после submit данные совпадают с вводом
		$("[id=output]").shouldHave(text(username), text(email),
				text(address), text(permanentaddress));

		//или можно проверить так
		$("[id=output] [id=name]").shouldHave(text(username));
		$("[class=mb-1]").shouldHave(text(username)); // или так
		$(".mb-1").shouldHave(text(username)); // или так
		$("[id=output]").$("[id=email]").shouldHave(text(email));
		// $(byName("currentAddress")).shouldHave(text(address)); или так, если бы у элемента быол name
		$("[id=output]").$("[id=currentAddress]").shouldHave(text(address));
		$("p[id=permanentAddress]").shouldHave(text(permanentaddress));
		$("[id=permanentAddress]", 1).shouldHave(text(permanentaddress)); // или так
	}
}
