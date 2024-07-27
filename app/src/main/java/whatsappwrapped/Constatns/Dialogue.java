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

package whatsappwrapped.Constatns;

import org.checkerframework.checker.guieffect.qual.SafeEffect;

import whatsappwrapped.Enums.Language;

public class Dialogue {

    /////////////////
    // Variables
    ////////////////

    private static Language currLanguage = Language.ENGLISH;

    /////////////////
    // Constants
    ////////////////

    public static String[] ASCII_ART = {
            " __      __.__            __                                __      __                                       .___",
            "/  \\    /  |  |__ _____ _/  |_ __________  ______ ______   /  \\    /  ____________  ______ ______   ____   __| _/",
            "\\   \\/\\/   |  |  \\\\__  \\\\   __/  ___\\__  \\ \\____ \\\\____ \\  \\   \\/\\/   \\_  __ \\__  \\ \\____ \\\\____ \\_/ __ \\ / __ | ",
            " \\        /|   Y  \\/ __ \\|  | \\___ \\ / __ \\|  |_> |  |_> >  \\        / |  | \\// __ \\|  |_> |  |_> \\  ___// /_/ | ",
            "  \\__/\\  / |___|  (____  |__|/____  (____  |   __/|   __/    \\__/\\  /  |__|  (____  |   __/|   __/ \\___  \\____ | ",
            "       \\/       \\/     \\/         \\/     \\/|__|   |__|            \\/              \\/|__|   |__|        \\/     \\/ " };

    /////////////////
    // Dialogue Accesors
    ////////////////

    /**
     * ENGLISH - "Enter chat file path"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String enterChatPath() {
        switch (currLanguage) {
            case ENGLISH:
                return "Enter chat file path";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Chat log"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String chatLog() {
        switch (currLanguage) {
            case ENGLISH:
                return "Chat log";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Chat Information"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String chatInformation() {
        switch (currLanguage) {
            case ENGLISH:
                return "Chat information";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Random messages"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String randomMessages() {
        switch (currLanguage) {
            case ENGLISH:
                return "Random messages";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Debug"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String debugWord() {
        switch (currLanguage) {
            case ENGLISH:
                return "Debug";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Total messages"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String totalMessages() {
        switch (currLanguage) {
            case ENGLISH:
                return "Total messages";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Time"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String time() {
        switch (currLanguage) {
            case ENGLISH:
                return "Time";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Date"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String date() {
        switch (currLanguage) {
            case ENGLISH:
                return "Date";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Sender"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String sender() {
        switch (currLanguage) {
            case ENGLISH:
                return "Sender";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Content"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String contents() {
        switch (currLanguage) {
            case ENGLISH:
                return "Content";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "Error"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String error() {
        switch (currLanguage) {
            case ENGLISH:
                return "Error";

            default:
                return "";
        }
    }

    /**
     * ENGLISH - "File doesn't exist"
     * 
     * @return {@code String} depending on the current language.
     */
    public static String fileDoesntExist() {
        switch (currLanguage) {
            case ENGLISH:
                return "File doesn't exist";

            default:
                return "";
        }
    }

    /////////////////
    // Language methods
    ////////////////

    /**
     * Accesor method for the current program language.
     * 
     * @return {@code Language} enum value.
     */
    public static Language getCurrentLanguage() {
        return currLanguage;
    }

    /**
     * Modifier method to change the current language value.
     * 
     * @param language - new {@code Language} enum value.
     */
    public static void setCurrentLanguge(Language language) {
        currLanguage = language;
    }
}
