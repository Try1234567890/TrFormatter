package com.github.jsf;

import com.github.jsf.text.Text;
import org.junit.jupiter.api.Test;

public class TextCasesTests {

    @Test
    public void alternate_text_recognition_test() {
        Text text = Text.of("This text contains multiple {#[console(msg='PLACEHOLDER!')] @[if_date(comparator='=', date='21/07/2026')]}. " +
                "This is the second {#[console(msg='PLACEHOLDER!')] @[if_date(comp='>', date='21/07/2026')]}.").format();
        System.out.println("This is text: " + text.quoteWithDouble());
    }
}
