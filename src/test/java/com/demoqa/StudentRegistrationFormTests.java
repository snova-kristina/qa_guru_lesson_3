package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class StudentRegistrationFormTests {

	@BeforeAll
	static void SetUp() {
		Configuration.holdBrowserOpen = false;
		Configuration.baseUrl = "https://demoqa.com";
		Configuration.browserSize = "1920x1080";

	}

	String firstname = "Elon";
	String lastname = "Musk";
	String email = "elonmusk@spacex.com";
	String mobile = "7900123456";
	String address = "Hawthorne, California, USA";
	String gender = "Other";
	String state = "Uttar Pradesh";
	String city = "Agra";
	//String imgPath = "/home/rudik/IdeaProjects/qa_guru_lesson_3/src/resources/test.png";

	@Test
	void FillRegistrationForm() {
		open("/automation-practice-form");

		// Selenide.zoom(0.65);
		// executeJavaScript("document.querySelector(\"footer\").hidden = 'true';document.querySelector(\"#fixedban\").hidden = 'true'");-удаление футера
		// executeJavaScript("$('footer').remove()");
		// executeJavaScript("$('#fixedban').remove()"); - еще вариант убрать футер и баннер рекламы

		$("#firstName").setValue(firstname);
		$("#lastName").setValue(lastname);
		$("#userEmail").setValue(email);
		$("#currentAddress").setValue(address);
		$("#genterWrapper").$(byText(gender)).click();
		$("#userNumber").setValue(mobile);

		//Start of Birthday block
		$("#dateOfBirthInput").click();
		$(".react-datepicker__month-select").selectOption("April");
		$(".react-datepicker__year-select").selectOption("1995");
		$("[aria-label$='April 9th, 1995']").click();
		//End of Birthday block

		$("#subjectsInput").setValue("E");
		$(byText("English")).click(); // или так, короче на строку $("#subjectsInput").setValue("History").pressEnter();
		$(byText("Music")).click();

		File file = $("#uploadPicture").uploadFile(new File("/home/rudik/IdeaProjects/qa_guru_lesson_3/src/resources/test.png"));
		//$("input#uploadPicture").uploadFromClasspath("test.png");
		//$("#uploadPicture").uploadFromClasspath(imgPath);

		$("#state").click();
		$(byText(state)).click(); //  $("#react-select-3-input").setValue(state).pressEnter();
		$("#city").click();
		$(byText(city)).click();

		$("#submit").pressEnter();

		//Asserts
		$("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
		// .parent().shouldHave(text(firstname + lastname));

		//$(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstname + " " + lastname));
		$(".table-responsive").shouldHave(
				text(firstname + " " + lastname),
				text(email),
				text(gender),
				text(mobile),
				text("09 April,1995"),
				text("English"),
				text("Music"),
				text("test.png"),
				text(address),
				text(state + " " + city));
	}
}