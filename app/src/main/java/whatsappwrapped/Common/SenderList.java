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

package whatsappwrapped.Common;

import java.util.ArrayList;
import whatsappwrapped.Constatns.*;

/**
 * Class to store information about a whatsapp user, and a global list of all
 * registered users.
 * 
 */
public class SenderList {

    /////////////////
    // Global variables
    ////////////////

    public static ArrayList<SenderList> registered;

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
     * Dedault contructor.
     * 
     * @param username - username;
     */
    public SenderList(String username) {
        // Initialion of the global {@code ArrayList} of users
        if (registered == null) {
            registered = new ArrayList<SenderList>();
        }

        // Checks if it's a new sender
        // If it is, it adds it into the global user list
        // If it's not, than it increments the message count of the user mathing by one.
        for (int i = 0; i < registered.size(); i++) {
            SenderList s = registered.get(i);
            if (s.getUsername().equals(username)) {
                s.addMessageCount(1);
                this.username = null;
                return;
            }
        }

        // Sets the object fields.
        this.username = username;
        this.messagesSend = 1;
        this.color = ASCIIColors.getNextColor();
        registered.add(this);
    }

    /**
     * Creates a new {@code SenderList} object without adding it to the global list.
     * It also initializes it with given values.
     * 
     * @param userName    - the username of the user.
     * @param numMessages - initial number of messages the user send.
     * @param color       - username console ASCII color code.
     */
    public SenderList(String userName, int numMessages, String color) {
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

    public String getColor() {
        return this.color;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Add the number of messages send by a targen number.
     * 
     * @param count - target number.
     */
    public void addMessageCount(int count) {
        this.messagesSend += count;
    }

    /////////////////
    // Other public methods
    ////////////////

    public static void sortUsers() {
        if (registered.size() > 2) {
            numMsgSort(registered, 0, registered.size() - 1);
        }
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Sorts an {@code ArrayList} of {@code SenderList} objects, according to the
     * number of messages each user send from the highest message count to the
     * lowest.
     * </p>
     * Uses recursion.
     * 
     * @param arr   - target {@code ArrayList}.
     * @param start - start index of the sorting.
     * @param end   - end index of the sorting.
     */
    private static void numMsgSort(ArrayList<SenderList> arr, int start, int end) {
        if (start < end) {
            int pi = partition(arr, start, end);
            numMsgSort(arr, start, pi - 1);
            numMsgSort(arr, pi + 1, end);
        }
    }

    /**
     * Helper method for the message sort method.
     * 
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static int partition(ArrayList<SenderList> arr, int start, int end) {
        int pivot = arr.get(end).getMessagesSend();
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (arr.get(j).getMessagesSend() < pivot) {
                i++;
                SenderList temp = new SenderList(arr.get(i).getUsername(), arr.get(i).getMessagesSend(),
                        arr.get(i).getColor());
                arr.set(i, arr.get(j));
                arr.set(j, temp);
            }
        }
        SenderList temp = new SenderList(arr.get(i + 1).getUsername(), arr.get(i + 1).getMessagesSend(),
                arr.get(i).getColor());
        arr.set(i + 1, arr.get(end));
        arr.set(end, temp);

        return i + 1;
    }

}