package com.github.jsf;


import com.github.utilities.ClassUtils;

public class TestHelper {

    private TestHelper() {
    }

    public static void sendHeader() {
        System.out.println();
        System.out.println();
        String testName = " " + ClassUtils.getCallerInfo("Line: {{LineNumber}} | Name: {{ClassName}} >> {{MethodName}}") + " ";

        String separatorDel = "||";
        String separator = separatorDel + "-".repeat(testName.length()) + separatorDel;

        System.out.println(separator);
        System.out.println(separatorDel + testName + separatorDel);
        System.out.println(separator);
        System.out.println();
    }

    public static void sendFooter() {
        String testName = ClassUtils.getCallerInfo("{{MethodName}}");

        String separator = " " + "-".repeat((int) Math.round(testName.length() * 2.7)) + " ";
        System.out.println("Test \"" + testName + "\" passed!");
        System.out.println(separator);
    }
}
