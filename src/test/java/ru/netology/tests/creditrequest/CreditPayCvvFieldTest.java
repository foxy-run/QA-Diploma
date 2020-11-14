package ru.netology.tests.creditrequest;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.pages.MainPage;
import ru.netology.pages.PaymentPage;
import ru.netology.tests.TestBase;

import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.data.Data.*;

public class CreditPayCvvFieldTest extends TestBase{

    MainPage mainPage = new MainPage();
    PaymentPage paymentPage = new PaymentPage();

    @BeforeEach
    void setUpForPayWithCredit() {
        mainPage.payWithCredit();
    }

    @Test
    public void shouldFailurePaymentIfEmptyCvv() {
        val cardData = getInvalidCvvIfEmpty();
        paymentPage.fillCardData(cardData);
        final ElementsCollection fieldSub = $$(".input__sub");
        final SelenideElement cvvFieldSub = fieldSub.get(2);
        cvvFieldSub.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldFailurePaymentIfCvvOneSym() {
        val cardData = getInvalidCvvIfOneSym();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }

    @Test
    public void shouldFailurePaymentIfCvvTwoSym() {
        val cardData = getInvalidCvvIfTwoSym();
        paymentPage.fillCardData(cardData);
        paymentPage.improperFormatNotification();
    }
}
