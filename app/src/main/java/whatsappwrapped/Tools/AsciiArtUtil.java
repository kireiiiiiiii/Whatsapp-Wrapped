/*
 * Author: Matěj Šťastný
 * Date created: 7/25/2024
 * Github link: https://github.com/kireiiiiiiii/Whatsapp-Wrapped
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

package whatsappwrapped.Tools;

import whatsappwrapped.Constatns.ASCIIColors;

/**
 * Util class for formating ASCII artsp
 * 
 */
public class AsciiArtUtil {

    /////////////////
    // Public methods
    ////////////////

    /**
     * Prints a boxed ASCII art.
     * 
     * @param asciiArt     - ASCII art array, each element is one line of the ASCII
     *                     art.
     * @param paddingRight - right padding with spaces.
     * @param paddingLeft  - left padding with spaces.
     */
    public static void printBoxedAsciiArt(String[] asciiArt, int paddingRight, int paddingLeft) {
        if (asciiArt == null || asciiArt.length == 0) {
            return;
        }

        // Find the maximum length of the lines in the ASCII art
        int maxLength = 0;
        for (String line : asciiArt) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        maxLength += paddingLeft + paddingRight;

        // Print the top border
        printBorder(maxLength + paddingRight);

        // Print each line of the ASCII art surrounded by vertical bars
        for (String line : asciiArt) {
            System.out
                    .println("|" + " ".repeat(paddingLeft) + line + " ".repeat(maxLength - line.length()) + "|");
        }

        System.out.println("|" + " ".repeat(paddingLeft) + " ".repeat(maxLength) + "|");

        // Print the bottom border
        printBorder(maxLength + paddingRight);
    }

    /**
     * Prints a boxed ASCII art.
     * 
     * @param asciiArt     - ASCII art array, each element is one line of the ASCII
     *                     art.
     * @param paddingRight - right padding with spaces.
     * @param paddingLeft  - left padding with spaces.
     * @param color        - color of the ascii art.
     */
    public static void printBoxedColoredAsciiArt(String[] asciiArt, String color, int paddingRight, int paddingLeft) {
        if (asciiArt == null || asciiArt.length == 0) {
            return;
        }

        // Find the maximum length of the lines in the ASCII art
        int maxLength = 0;
        for (String line : asciiArt) {
            if (line.length() > maxLength) {
                maxLength = line.length();
            }
        }

        maxLength += paddingLeft + paddingRight;

        // Print the top border
        printBorder(maxLength + paddingRight);

        // Print each line of the ASCII art surrounded by vertical bars
        for (String line : asciiArt) {
            System.out
                    .println("|" + " ".repeat(paddingLeft) + color + line + ASCIIColors.COLOR_RESET
                            + " ".repeat(maxLength - line.length()) + "|");
        }

        System.out.println("|" + " ".repeat(paddingLeft) + " ".repeat(maxLength) + "|");

        // Print the bottom border
        printBorder(maxLength + paddingRight);
    }

    /**
     * Adds a color code at the start of each line of the ASCII art, and appends the
     * color reset code at the end of every line of the art.
     * 
     * @param asciiArt - original array.
     * @param color    - ascii color code.
     * @return colored ascii art array.
     */
    public static String[] getColorAscii(String[] asciiArt, String color) {
        String[] coloredArt = new String[asciiArt.length];
        for (int i = 0; i < coloredArt.length; i++) {
            coloredArt[i] = color + asciiArt[i] + ASCIIColors.COLOR_RESET;
        }

        return coloredArt;
    }

    /////////////////
    // Helper methods
    ////////////////

    /**
     * Helper method, prints a standartized "+---+" line with a lenght.
     * 
     * @param length
     */
    private static void printBorder(int length) {
        System.out.println("+" + "-".repeat(length) + "+");
    }
}
