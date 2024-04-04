package dev.cross.blissfulcore.ui.utils;

import java.util.HashMap;

public class StringFormatter {
    private static final HashMap<Character, String> customUnicode  = new HashMap<Character, String>() {{
        put('a', "ᴀ");
        put('b', "ʙ");
        put('c', "ᴄ");
        put('d', "ᴅ");
        put('e', "ᴇ");
        put('f', "ꜰ");
        put('g', "ɢ");
        put('h', "ʜ");
        put('i', "ɪ");
        put('j', "ᴊ");
        put('k', "ᴋ");
        put('l', "ʟ");
        put('m', "ᴍ");
        put('n', "ɴ");
        put('o', "ᴏ");
        put('p', "ᴘ");
        put('q', "ꞯ");
        put('r', "ʀ");
        put('s', "ꜱ");
        put('t', "ᴛ");
        put('u', "ᴜ");
        put('v', "ᴠ");
        put('w', "ᴡ");
        put('x', "x");
        put('y', "ʏ");
        put('z', "ᴢ");


        put('0', "₀");
        put('1', "₁");
        put('2', "₂");
        put('3', "₃");
        put('4', "₄");
        put('5', "₅");
        put('6', "₆");
        put('7', "₇");
        put('8', "₈");
        put('9', "₉");

    }};

    public static String customString(String s) {

        StringBuilder output = new StringBuilder();


        for(int i = 0; i < s.length(); i++) {
            if(customUnicode.containsKey(Character.toLowerCase(s.charAt(i)))) {
                output.append(customUnicode.get(Character.toLowerCase(s.charAt(i))));
                continue;
            }
            output.append(s.charAt(i));
        }

        return output.toString();
    }
}
