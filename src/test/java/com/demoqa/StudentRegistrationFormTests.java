package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTests {

	@BeforeAll
	static void SetUp() {
		Configuration.holdBrowserOpen = true;
		Configuration.baseUrl = "https://demoqa.com";
		Configuration.browserSize = "1600x1800";

	}

	@Test
	void FillRegistrationForm() {
		Selenide.open("/automation-practice-form");

		String firstname = "Elon";
		String lastname = "Musk";
		String email = "elonmusk@spacex.com";
		String mobile = "7900123456";
		String address = "Hawthorne, California, USA";

		$("#stateCity-wrapper .css-2b097c-container").click();
		$(byText("Uttar Pradesh")).click();

		$("#stateCity-wrapper .css-1wa3eu0-placeholder").click();
		$(byText("Agra")).click();

		$("[id=firstName]").setValue(firstname);
		$("[id=lastName]").setValue(lastname);
		$("[id=userEmail]").setValue(email);
		$("[id=currentAddress]").setValue(address);
		$(byText("Other")).click();
		$("[id=userNumber]").setValue(mobile);

		$("[id=subjectsContainer]").click();
		$("[id=subjectsInput]").setValue("E");
		$(byText("English")).click();
		$(byText("Music")).click();

		$(By.cssSelector("[id=dateOfBirthInput]")).click();
		$(By.className("react-datepicker__month-select")).click();
		$(byText("April")).click();
		$(By.className("react-datepicker__year-select")).click();
		$(byText("1995")).click();

		$(By.className("btn btn-primary")).click();
	}
}
