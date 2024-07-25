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
