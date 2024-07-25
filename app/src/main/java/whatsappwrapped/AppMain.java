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

public class AppMain {

    /////////////////
    // Main method
    ////////////////

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter chat file path: ");
        String path = scanner.nextLine();
        Chat chat = new Chat(path);
        chat.printStats();
        // chat.printChatLog();
        // chat.printRandom();
        scanner.close();
    }
}
