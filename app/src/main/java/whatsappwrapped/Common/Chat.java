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

package whatsappwrapped.Common;

import whatsappwrapped.Enums.MessageType;
import whatsappwrapped.Tools.*;
import java.io.File;
import java.util.ArrayList;

public class Chat {

    /////////////////
    // Constants
    ////////////////

    public static final String COLOR_RESET = "\u001B[0m";

    /////////////////
    // Class variables
    ////////////////

    private File chatFile;
    private ArrayList<Message> messages;
    private ArrayList<Sender> registered;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default contructor.
     * 
     * @param chatLogFile - {@code File} object of the chat file.
     */
    public Chat(File chatLogFile) {
        if (!chatLogFile.exists()) {
            System.out.println("ERROR - File doesn't exist");
            System.exit(0);
        }
        this.chatFile = chatLogFile;
        this.messages = ChatLogUtil.getMessageList(this.chatFile);
        refreshUserList();
    }

    /**
     * Contructor with a path parameter. Will create a new {@code File} object.
     * 
     * @param path - path of the chat file.
     */
    public Chat(String path) {
        this(new File(path));
    }

    /////////////////
    // Modifier methods
    ////////////////

    /**
     * Sets a new chat source file.
     * 
     * @param file - new {@code File} object.
     */
    public void setChatFile(File file) {
        this.chatFile = file;
    }

    /**
     * Sets a new chat source file from a path.
     * 
     * @param path - path of the chat file.
     */
    public void setChatFile(String path) {
        setChatFile(new File(path));
    }

    /////////////////
    // Print methods
    ////////////////

    /**
     * Prits the whole chat log.
     * 
     */
    public void printChatLog() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("CHAT LOG");
        System.out.println("-----------------------------------------------------\n");
        for (int currMsgIndex = 0; currMsgIndex < this.messages.size(); currMsgIndex++) {
            System.out.println(getMessagePrintString(currMsgIndex));
        }
    }

    /**
     * Prints the statistics of the chat.
     * 
     */
    public void printStats() {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("LOG STATS");
        System.out.println("-----------------------------------------------------\n");

        System.out.println("Total messages: " + this.messages.size() + "\n");

        for (Sender s : this.registered) {
            System.out.println("    > " + s.getColor() + s.getUsername() + ": " + s.getMessagesSend() + COLOR_RESET);
        }

    }

    /**
     * Prints a message block of given size starting at a random place in the chat
     * log.
     * </p>
     * If the lenght given is the same of bigger than the lenght of the chat, the
     * whole chat log will be printed.
     * </p>
     * If the lenght is 0 or smaller, nothing will be printed.
     * 
     * @param blockLenght
     */
    public void printRandomMessageBlock(int blockLenght) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println(blockLenght + " RANDOM MESSAGES");
        System.out.println("-----------------------------------------------------\n");

        if (blockLenght >= this.messages.size()) {
            printChatLog();
        } else if (blockLenght < 1) {
            return;
        } else {
            int max = this.messages.size() - blockLenght;
            int random = (int) (Math.random() * max + 1);
            for (int i = 0; i < blockLenght; i++) {
                System.out.println("    " + getMessagePrintString(random + i));
            }
        }
    }

    /**
     * Prints the informations about a message log.
     * 
     * @param messageLog - message log.
     */
    public void printDebugg(String messageLog) {
        System.out.println("\n-----------------------------------------------------");
        System.out.println("DEBUG");
        System.out.println("-----------------------------------------------------\n");

        System.out.println("Time: " + MessageUtil.getTime(messageLog));
        System.out.println("Date: " + MessageUtil.getDate(messageLog));
        System.out.println("Sender: " + MessageUtil.getSender(messageLog));
        System.out.println("Contents: " + MessageUtil.getContents(messageLog));
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Refreshes the registered user list using the message list.
     * 
     */
    private void refreshUserList() {
        this.registered = new ArrayList<Sender>();
        for (Message message : this.messages) { // Goes through all the messages
            if (message.getMessageType() == MessageType.REGULAR) { // Only do if the mesage is a user text message
                String senderTag = message.getSender();
                boolean foundUser = false;
                for (Sender sender : this.registered) { // Searches though the list of registered users
                    if (sender.getUsername().equals(senderTag)) {
                        foundUser = true;
                        sender.addMessageCount(1);
                    }
                }
                if (!foundUser) {
                    Sender newSender = new Sender(senderTag);
                    newSender.addMessageCount(1);
                    registered.add(newSender);
                }
            }
        }
    }

    /**
     * Gets the {@code String} of a message on the specific line, that can be
     * printed into the console.
     * 
     * @param line - target line.
     * @return - message {@code String}, or {@code null} if line out of bounds.
     */
    private String getMessagePrintString(int line) {
        if (line > this.messages.size() - 1) {
            return null;
        }

        Message message = this.messages.get(line);
        Sender sender = getMessageSender(message);

        String log = sender.getColor() + message.getDateSend() + " " + message.getTimeSend() + " - " +
                message.getSender() + " : "
                + message.getContents() + COLOR_RESET;

        return log;
    }

    /**
     * Gets the sender from the list of registered users for a {@code Message}
     * object.
     * 
     * @param message - {@code Message} object.
     * @return wanted {@code Sender} object or {@code null} if user not registered.
     */
    private Sender getMessageSender(Message message) {
        for (Sender sender : this.registered) {
            if (sender.getUsername().equals(message.getSender())) {
                return sender;
            }
        }
        return null;
    }

}
