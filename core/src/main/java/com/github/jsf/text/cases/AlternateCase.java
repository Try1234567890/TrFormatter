package com.github.jsf.text.cases;

public class AlternateCase implements TextCase {

    public static boolean isAlternateCase(String text) {
        if (text.isEmpty()) return false;
        byte[] bytes = text.getBytes();
        boolean isUpper = Character.isUpperCase(text.charAt(0));
        for (byte curr : bytes) {
            if (Character.isLetter(curr)) {
                if (isUpper) {
                    if (!Character.isUpperCase(curr)) return false;
                } else {
                    if (!Character.isLowerCase(curr)) return false;
                }
                isUpper = !isUpper;
            }
        }
        return true;
    }

    @Override
    public String[] toSpaceCase(String text) {
        return getWords(text, ' ');
    }

    @Override
    public String fromSpaceCase(String[] spaceCase) {
        StringBuilder sb = new StringBuilder();

        for (String word : spaceCase) {
            convertWord(word, sb);
            sb.append(" ");
        }

        return sb.toString();
    }


    private void convertWord(String text, StringBuilder sb) {
        if (text.isEmpty()) return;
        boolean asUpper = false;
        for (char ch : text.toCharArray()) {
            if (asUpper) sb.append(Character.toUpperCase(ch));
            else sb.append(Character.toLowerCase(ch));
            asUpper = !asUpper;
        }

    }
}



















