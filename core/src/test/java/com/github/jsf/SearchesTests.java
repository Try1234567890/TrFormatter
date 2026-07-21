package com.github.jsf;

import com.github.jsf.scanners.delimiters.Delimiter;
import com.github.jsf.text.Text;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchesTests {

    @Test
    public void sequence_search_test() {
        TestHelper.sendHeader();
        Text text = Text.of("This is an (${{do_not_capture}}) ${{example}}");
        String sequence = "}}";

        Assertions.assertEquals(43, text.indexOfNonBetween(sequence, Delimiter.of("(", ")")));
        TestHelper.sendFooter();
    }

    @Test
    public void last_index_of_test() {
        TestHelper.sendHeader();
        Text text = Text.of("This is an ${{example}}a (${{do_not_capture}})");
        String sequence = "}}";

        int index = text.lastIndexOfNonBetween(sequence, Delimiter.of("(", ")"));
        System.out.println(index);
        System.out.println(text.charAt(index));
        TestHelper.sendFooter();
    }

    @Test
    public void check_if_from_index_is_inclusive() {
        TestHelper.sendHeader();
        Text text = Text.of("This is an (${{do_not_capture}}) ${{[]}} ${{example}}");
        String sequence = "}}";

        Assertions.assertEquals(51, text.indexOfFromNonBetween(sequence, 39, Delimiter.of("(", ")")));
        TestHelper.sendFooter();
    }

    @Test
    public void check_if_to_index_is_exclusive() {
        TestHelper.sendHeader();
        Text text = Text.of("This is an (${{do_not_capture}}) ${{example}}");
        String sequence = "}}";

        Assertions.assertEquals(-1, text.indexOfToNonBetween(sequence, 43, Delimiter.of("(", ")")));
        TestHelper.sendFooter();
    }

}
