package frames;

import java.awt.*;

/**
 * color and hex conversion class
 * used for converting color to hex or hex to color
 * contains static methods for conversion
 * @author Matej Kesl
 */
public class ColorHex {

    /**
     * method used for converting a Color type object to a String type hex
     * @param color - color for conversion
     * @return String - hex from color
     */
    public static String colorToHex(Color color) {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    /**
     * method used for converting a String type hex to a Color type object
     * @param hex - hex for conversion
     * @return Color - color from hex
     */
    public static Color hexToColor(String hex) {
        return Color.decode(hex);
    }
}
