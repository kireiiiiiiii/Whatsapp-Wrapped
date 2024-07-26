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

package whatsappwrapped.Common;

import whatsappwrapped.Enums.MessageType;

/**
 * Object class for a message.
 * 
 */
public class Message {

    /////////////////
    // Variables
    ////////////////

    private String sender;
    private String contents;
    private String timeSend;
    private String dateSend;
    private MessageType messageType;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default constructor.
     * 
     * @param sender
     * @param contents
     * @param timeSend
     * @param dateSend
     * @param messageType
     */
    public Message(String sender, String contents, String timeSend, String dateSend, MessageType messageType) {
        this.sender = sender;
        this.contents = contents;
        this.timeSend = timeSend;
        this.dateSend = dateSend;
        this.messageType = messageType;
        // TODO : Add to chat list, and check for new users.
    }

    /////////////////
    // Accesors
    ////////////////

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

    public MessageType getMessageType() {
        return this.messageType;
    }

    /////////////////
    // Modifiers
    ////////////////

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

}
