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

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default contructor.
     * 
     * @param chat - {@code File} object of the chat file.
     */
    public Chat(File chat) {
        this.chatFile = chat;
        this.messages = new ArrayList<Message>();
        refresh();
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
    // Accesor methods
    ////////////////

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
    // Other public methods
    ////////////////

    /**
     * Refreshes all the class fields.
     * 
     */
    public void refresh() {
        if (!hasValidSource()) {
            System.out.println("Invalid source file");
            System.exit(0);
            return;
        }

        // Read the file
        System.out.println("Loading chat log...");
        ArrayList<String> fileContents = ChatLogUtil.getMessageList(this.chatFile);
        if (fileContents == null) {
            System.out.println("Couldn't read file");
            return;
        }

        if (fileContents.size() > 0) {
            if (fileContents.get(0).substring(17).equals(
                    "Messages and calls are end-to-end encrypted. No one outside of this chat, not even WhatsApp, can read or listen to them. Tap to learn more.")) {
                fileContents.remove(0);
            }
        }

        // Format and add the messages
        this.messages.clear();
        for (String messageLog : fileContents) {
            Message m = new Message(getSender(messageLog), getContents(messageLog), getTime(messageLog),
                    getDate(messageLog));
            this.messages.add(m);
        }
        SenderList.sortUsers();
    }

    public void printChatLog() {
        System.out.println("\nCHAT LOG: ");
        System.out.println("-----------------------------------------------------\n");
        for (Message m : this.messages) {

            String color = "";
            // find color
            for (int i = 0; i < SenderList.registered.size(); i++) {
                SenderList s = SenderList.registered.get(i);
                if (m.sender.equals(s.getUsername())) {
                    color = s.color;
                }
            }

            String log = color + m.getDateSend() + " " + m.getTimeSend() + " - " + m.getSender() + " : "
                    + m.getContents() + COLOR_RESET;
            System.out.println(log);
        }
    }

    public void printStats() {
        System.out.println("\nLOG STATS: ");
        System.out.println("-----------------------------------------------------\n");

        System.out.println("Total messages: " + this.messages.size());
        ArrayList<String> userMessageCount = new ArrayList<String>();
        for (SenderList sl : SenderList.registered) {
            userMessageCount.add(sl.color + "  > " + sl.getUsername() + ": " + sl.getMessagesSend() + COLOR_RESET);
        }
        System.out.println("\nIndividual:");
        for (String s : userMessageCount) {
            System.out.println(s);
        }
    }

    public void printRandom() {
        int random = (int) (Math.random() * this.messages.size());
        Message m = this.messages.get(random);
        String color = "";
        // find color
        for (int i = 0; i < SenderList.registered.size(); i++) {
            SenderList s = SenderList.registered.get(i);
            if (m.sender.equals(s.getUsername())) {
                color = s.color;
            }
        }
        String s = color + m.getDateSend() + " " + m.getTimeSend() + " - " + m.getSender() + " : " + m.getContents()
                + COLOR_RESET;
        System.out.println(s);
    }

    /**
     * Checks if the file is a valit chat preset.
     * 
     * @return {@code boolean} factor.
     */
    public boolean hasValidSource() {
        return this.chatFile.exists();
    }

    /**
     * Prints the informations about a message log.
     * 
     * @param messageLog - message log.
     */
    public void printDebugg(String messageLog) {
        System.out.println("Time: " + getTime(messageLog));
        System.out.println("Date: " + getDate(messageLog));
        System.out.println("Sender: " + getSender(messageLog));
        System.out.println("Contents: " + getContents(messageLog));
    }

    /////////////////
    // Private methods
    ////////////////

    private String getTime(String messageLog) {
        int firstSlashIndex = messageLog.indexOf('/');
        int secondSlashIndex = messageLog.indexOf('/', firstSlashIndex + 1);
        int startIndex = messageLog.indexOf(',', secondSlashIndex) + 2;
        return messageLog.substring(startIndex, startIndex + 5);
    }

    private String getDate(String messageLog) {
        int endIndex = messageLog.indexOf(',');
        return messageLog.substring(0, endIndex);
    }

    private String getSender(String messageLog) {
        int startIndex = messageLog.indexOf(" - ") + 3;
        int endIndex = messageLog.indexOf(":", startIndex);
        assert (endIndex != -1) : "SenderFormatError - " + messageLog;
        return messageLog.substring(startIndex, endIndex);
    }

    private String getContents(String messageLog) {
        int startIndex = messageLog.indexOf(" - ") + 3;
        startIndex = messageLog.indexOf(":", startIndex) + 2;
        assert (startIndex != -1) : "ContentFormatError - " + messageLog;
        return messageLog.substring(startIndex);
    }

    /////////////////
    // Message class
    ////////////////

    private class Message {

        /* CLASS VARIABLES */

        private String sender;
        private String contents;
        private String timeSend;
        private String dateSend;

        /* CONTRUCTORS */

        public Message(String sender, String contents, String timeSend, String dateSend) {
            this.sender = sender;
            this.contents = contents;
            this.timeSend = timeSend;
            this.dateSend = dateSend;
            new SenderList(sender);
        }

        /* ACCESORS */

        public String getSender() {
            return this.sender;
        }

        public String getContents() {
            return this.contents;
        }

        public String getTimeSend() {
            return this.timeSend;
        }

        public String getDateSend() {
            return this.dateSend;
        }
    }
}
