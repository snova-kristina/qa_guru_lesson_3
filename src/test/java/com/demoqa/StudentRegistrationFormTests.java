package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class StudentRegistrationFormTests {

	@BeforeAll
	static void SetUp() {
		Configuration.holdBrowserOpen = true;
		Configuration.baseUrl = "https://demoqa.com";
		Configuration.browserSize = "1438x870";

	}

	@Test
	void FillRegistrationForm() {
		open("/automation-practice-form");

		String firstname = "Elon";
		String lastname = "Musk";
		String email = "elonmusk@spacex.com";
		String mobile = "7900123456";
		String address = "Hawthorne, California, USA";


		$("[id=firstName]").setValue(firstname);
		$("[id=lastName]").setValue(lastname);
		$("[id=userEmail]").setValue(email);
		$("[id=currentAddress]").setValue(address);
		$(byText("Other")).click();
		$("[id=userNumber]").setValue(mobile);

		$(By.cssSelector("[id=dateOfBirthInput]")).click();
		$(By.className("react-datepicker__month-select")).click();
		$(byText("April")).click();
		$(By.className("react-datepicker__year-select")).click();
		$(byText("1995")).click();
		$("[class=react-datepicker__year-select]").click();
		$("[id=userForm]").click();

		$("[id=subjectsContainer]").click();
		$("[id=subjectsInput]").setValue("E");
		$(byText("English")).click();
		$(byText("Music")).click();

		File file = $("#uploadPicture").uploadFile(new File("src/test/java/com/demoqa/test.png"));
		$("#stateCity-wrapper .css-2b097c-container").click();
		$(byText("Uttar Pradesh")).click();
		$("#stateCity-wrapper .css-1wa3eu0-placeholder").click();
		$(byText("Agra")).click();

		$(byText("Submit")).click();
	}
}
