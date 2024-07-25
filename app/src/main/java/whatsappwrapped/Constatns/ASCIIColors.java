/*
 * Author: Matěj Šťastný
 * Date created: 5/20/2024
 * Github link: Not published
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package whatsappwrapped.Constatns;

/**
 * A color picker class. Contains methods to return an ASCII color code.
 * 
 */
public class ASCIIColors {

    /////////////////
    // Class fields
    ////////////////

    private static int textIndex = 0;
    private static final String[] TEXT_COLORS = {
            "\u001B[35m", // Purple
            "\u001B[31m", // Red
            "\u001B[32m", // Green
            "\u001B[33m", // Yellow
            "\u001B[34m", // Blue
            "\u001B[36m", // Cyan
    };

    private static int backroundIndex = 0;
    private static final String[] BACKROUND_COLORS = {
            "\u001B[41m", // Red
            "\u001B[42m", // Green
            "\u001B[43m", // Yellow
            "\u001B[44m", // Blue
            "\u001B[45m", // Purple
            "\u001B[46m", // Cyan
            "\u001B[47m", // White
    };

    /////////////////
    // Public methods
    ////////////////

    /**
     * Returns the ASCII code of the next text color in the text color array, and
     * than
     * increments the text color index. If the text color index is higher than the
     * size of the array, the
     * methods sets it back to zero and restarts the cycle.
     * 
     * @return a {@code String} ASCII color code.
     */
    public static String getNextColor() {
        String color = TEXT_COLORS[textIndex];
        if (textIndex >= (TEXT_COLORS.length - 1)) {
            textIndex = 0;
        } else {
            textIndex++;
        }
        return color;
    }

    /**
     * Returns the ASCII code of the next backround color in the backround color
     * array, and than
     * increments the backround color index. If the backround color index is higher
     * than the arrray, the
     * methods sets it back to zero and restarts the cycle.
     * 
     * @return a {@code String} ASCII color code.
     */
    public static String getNextBackroundColor() {
        String color = BACKROUND_COLORS[backroundIndex];
        if (backroundIndex >= (BACKROUND_COLORS.length - 1)) {
            backroundIndex = 0;
        } else {
            backroundIndex++;
        }
        return color;
    }
}
