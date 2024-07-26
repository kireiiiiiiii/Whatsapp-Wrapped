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

import whatsappwrapped.Constatns.*;

/**
 * Object class to store information about a whatsapp sender.
 * 
 */
public class Sender {

    /////////////////
    // Object variables
    ////////////////

    private String username;
    private int messagesSend;
    public String color;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default contructor, asigns the username, sets messages send to 0, and gets
     * the next ASCII color.
     * 
     * @param username - username;
     */
    public Sender(String username) {
        this.username = username;
        this.messagesSend = 0;
        this.color = ASCIIColors.getNextColor();
    }

    /**
     * Parameterized constructor, sets the param values.
     * 
     * @param userName    - the username of the user.
     * @param numMessages - initial number of messages the user send.
     * @param color       - username console ASCII color code.
     */
    public Sender(String userName, int numMessages, String color) {
        this.username = userName;
        this.messagesSend = numMessages;
        this.color = color;
    }

    /////////////////
    // Accesors
    ////////////////

    /**
     * Accesor method for the username.
     * 
     * @return username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Returns the number of messages a user send.
     * 
     * @return
     */
    public int getMessagesSend() {
        return this.messagesSend;
    }

    /**
     * Gets the ASCII color of the user.
     * 
     * @return - ASCII color.
     */
    public String getColor() {
        return this.color;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Add the number of messages send by a target number.
     * 
     * @param count - target number.
     */
    public void addMessageCount(int count) {
        this.messagesSend += count;
    }

}