/*
 * Author: Matěj Šťastný
 * Date created: 5/20/2024
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

package whatsappwrapped;

import java.util.Scanner;
import whatsappwrapped.Common.Chat;
import whatsappwrapped.Constatns.Dialogue;
import whatsappwrapped.Tools.AsciiArtUtil;

/**
 * Main class.
 * 
 */
public class AppMain {

    /////////////////
    // Main method
    ////////////////

    public static void main(String[] args) {
        System.out.println("\n");
        AsciiArtUtil.printBoxedColoredAsciiArt(Dialogue.ASCII_ART, "\u001B[32m", 2, 2);
        System.out.print("\n\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print(Dialogue.enterChatPath() + ": \n  > ");

        String path = scanner.nextLine();
        Chat chat = new Chat(path);

        /* PRINT METHODS */ // TODO: User console
        chat.printStats();
        // chat.printChatLog();
        chat.printRandomMessageBlock(10);

        scanner.close();
    }

}
