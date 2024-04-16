package tests.testng;

import listeners.RetryListenerTestNG;
import models.People;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.junit5.allure_steps.CalcSteps;

import java.util.ArrayList;
import java.util.List;

public class NGTests {
    @BeforeSuite
    public void setAnalyzer(ITestContext context) {
        for (ITestNGMethod testMethod : context.getAllTestMethods()) {
            testMethod.setRetryAnalyzer(new RetryListenerTestNG());
        }
    }

    @AfterSuite
    public void saveFailed() {
        RetryListenerTestNG.saveFailedTests();
    }

    @Test(groups = {"sum1"})
    public void sumTestNGTest() {
        CalcSteps calcSteps = new CalcSteps();
        Assert.assertTrue(calcSteps.isPositive(-10));
    }

    @Test(groups = {"sum2"})
    public void sumTestNGTest2() {
        CalcSteps calcSteps = new CalcSteps();
        Assert.assertTrue(calcSteps.isPositive(-20));
    }

    @DataProvider(name = "testUsers")
    public Object[] dataWithUsers() {
        People stas = new People("Stas", 25, "male");
        People katya = new People("Katya", 20, "female");
        People oleg = new People("Oleg", 30, "male");
        return new Object[]{stas, katya, oleg};
    }

    @Test(dataProvider = "testUsers")
    public void testUsersWithRole(People people) {
        System.out.println(people.getName());
        Assert.assertTrue(people.getAge() > 18);
        //some magic
        Assert.assertTrue(people.getName().contains("a"));
    }

    @DataProvider(name = "ips")
    public Object[] testIpAddresses() {
        List<String> ips = new ArrayList<>();
        ips.add("127.0.0.1");
        ips.add("localhost");
        ips.add("58.43.121.90");

        return ips.toArray();
    }

    @Test(dataProvider = "ips")
    public void ipsTest(String ip) {
        System.out.println(ip);
        Assert.assertTrue(ip.matches("^([0-9]+(\\.|$)){4}"));
    }

    @Test(dataProviderClass = DataTestArguments.class, dataProvider = "argsForCalc")
    public void calcTest(int a, int b, int c) {
        Assert.assertEquals(a + b, c);
    }

    @Test(dataProviderClass = DataTestArguments.class, dataProvider = "diffArgs")
    public void someMagicTransform(int a, String b) {
        Assert.assertEquals(convert(a), b);
    }

    private String convert(int a) {
        switch (a) {
            case 1:
                return "one";
            case 2:
                return "two";
            case 5:
                return "five";
            default:
                return null;
        }
    }



/*
Gradle и Maven — это два популярных инструмента для автоматизации сборки, тестирования и развертывания проектов Java (и не только)
Вот основные отличия между ними:
1) Язык описания проекта: Maven использует формат XML для описания проекта, в то время как Gradle использует свой DSL
на базе Groovy или Kotlin. Это делает синтаксис Gradle более гибким и легким для чтения и написания.

2) Гибкость и расширяемость: Gradle обладает большей гибкостью и расширяемостью по сравнению с Maven.
С помощью Gradle вы можете определять свои собственные задачи сборки и настраивать их поведение под конкретные потребности проекта.

3) Производительность: Gradle часто считается более быстрым и эффективным в сравнении с Maven.
Он имеет умную систему кэширования, которая позволяет избежать повторной сборки, если ничего не изменилось.
Кроме того, Gradle выполняет многопоточную обработку и может параллельно выполнять задачи, что также способствует ускорению сборки.

4) Экосистема плагинов: Оба инструмента имеют широкую экосистему плагинов, которые могут быть использованы для выполнения различных задач.
Однако Gradle обладает большей гибкостью в использовании плагинов, поскольку DSL позволяет легко настраивать их поведение.

Gradle обычно предоставляет более простой и интуитивно понятный интерфейс для работы с IDE.

Выбор между Gradle и Maven часто зависит от требований и предпочтений команды разработчиков.
Maven является более установленным и стабильным инструментом с большим сообществом,
 в то время как Gradle предлагает более современный и гибкий подход к сборке проектов.
 */


}
