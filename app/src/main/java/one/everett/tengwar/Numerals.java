package one.everett.tengwar;

import java.util.HashMap;
import java.util.Map;

public final class Numerals {
    private static final Map<Integer, Glyph> DIGIT_GLYPHS = new HashMap<>();

    static {
        DIGIT_GLYPHS.put(0, Glyph.DIGIT_0);
        DIGIT_GLYPHS.put(1, Glyph.DIGIT_1);
        DIGIT_GLYPHS.put(2, Glyph.DIGIT_2);
        DIGIT_GLYPHS.put(3, Glyph.DIGIT_3);
        DIGIT_GLYPHS.put(4, Glyph.DIGIT_4);
        DIGIT_GLYPHS.put(5, Glyph.DIGIT_5);
        DIGIT_GLYPHS.put(6, Glyph.DIGIT_6);
        DIGIT_GLYPHS.put(7, Glyph.DIGIT_7);
        DIGIT_GLYPHS.put(8, Glyph.DIGIT_8);
        DIGIT_GLYPHS.put(9, Glyph.DIGIT_9);
    }

    public Numerals() {
    }

    public static String toTengwarString(int number) {
        if (number == 10)
            return Character.toString(Glyph.DIGIT_10.getKey());
        if (number == 11) {
            return Character.toString(Glyph.DIGIT_11.getKey());
        }

        int temp = number;
        StringBuilder sb = new StringBuilder();

        do {
            int digit = temp % 10;
            Glyph glyph = DIGIT_GLYPHS.get(digit);
            sb.append(glyph.getKey());
            temp /= 10;
        } while (temp != 0);

        return sb.toString();
    }
}
