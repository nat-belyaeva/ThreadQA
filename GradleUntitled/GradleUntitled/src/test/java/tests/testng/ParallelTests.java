package tests.testng;

import com.codeborne.selenide.Selenide;
import org.testng.annotations.Test;

public class ParallelTests {

    @Test
    public void selenide1(){
        Selenide.open("https://heisenbug.ru/");
    }

    @Test
    public void selenide2(){
        Selenide.open("https://heisenbug.ru/speakers/");
    }

    @Test
    public void selenide3(){
        Selenide.open("https://heisenbug.ru/persons/f41932ba6d98472aba9018dbab5fdef2/");
    }

    @Test
    public void selenide4(){
        Selenide.open("https://heisenbug.ru/experts/");
    }

}
