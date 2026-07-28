package com.github.jsf;

import com.github.jsf.dynamic_placeholders.phases.Formatter;
import com.github.jsf.text.Text;
import org.junit.jupiter.api.Test;

public class FormatterTests {

    @Test
    public void text_formatting_test() {
        TestHelper.sendHeader();

        Text text = Text.of("This text contains some placeholder. " +
                "This is the 1: {#[read_file(path='C:\\Development\\Java\\Libraries\\JSF (Java String Formatter)\\core\\src\\test\\resources\\read-file.txt')]} " +
                "This is the 2: Date: {#[now_date()]} Time: {#[now_time]} " +
                "This is the 3: {#[random_number(min=1, max=100)]} ");
        System.out.println(text.format());

        TestHelper.sendFooter();
    }

    @Test
    void matches_action_test() {
        TestHelper.sendHeader();

        Formatter formatter = Text.of("#[random_number(min=1, max=100)]").formatter();
        System.out.println(formatter.matchesAction());
        System.out.println(formatter.asAction());

        TestHelper.sendFooter();
    }

    @Test
    void matches_condition_test() {
        TestHelper.sendHeader();

        Formatter formatter = Text.of("@[if_date(date='25/12/2026',comp='>=')]").formatter();
        System.out.println(formatter.matchesCondition());
        System.out.println(formatter.asCondition());

        TestHelper.sendFooter();
    }

    @Test
    void matches_function_test() {
        TestHelper.sendHeader();

        Formatter formatter = Text.of("/[truncate(s=0,e=10)]").formatter();
        System.out.println(formatter.matchesFunction());
        System.out.println(formatter.asFunction());

        TestHelper.sendFooter();
    }
}
