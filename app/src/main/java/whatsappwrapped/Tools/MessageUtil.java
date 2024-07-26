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

package whatsappwrapped.Tools;

/**
 * Class with methods that can extract a cetrain infromation from a message log.
 * 
 */
public class MessageUtil {

    /**
     * Gets the time the message was send from a message log.
     * 
     * @param messageLog
     * @return {@code String} with the time.
     */
    public static String getTime(String messageLog) {
        int firstSlashIndex = messageLog.indexOf('/');
        int secondSlashIndex = messageLog.indexOf('/', firstSlashIndex + 1);
        int startIndex = messageLog.indexOf(',', secondSlashIndex) + 2;
        return messageLog.substring(startIndex, startIndex + 5);
    }

    /**
     * Gets the date the message was send from a message log.
     * 
     * @param messageLog
     * @return {@code String} with the date.
     */
    public static String getDate(String messageLog) {
        int endIndex = messageLog.indexOf(',');
        return messageLog.substring(0, endIndex);
    }

    /**
     * Gets the name of the sender of the message from a message log.
     * 
     * @param messageLog
     * @return
     */
    public static String getSender(String messageLog) {
        int startIndex = messageLog.indexOf(" - ") + 3;
        int endIndex = messageLog.indexOf(":", startIndex);
        assert (endIndex != -1) : "SenderFormatError - " + messageLog;
        return messageLog.substring(startIndex, endIndex);
    }

    /**
     * Gets the content of the message (the text itself) from the message log.
     * 
     * @param messageLog
     * @return {@code String} of the content of the message.
     */
    public static String getContents(String messageLog) {
        int startIndex = messageLog.indexOf(" - ") + 3;
        startIndex = messageLog.indexOf(":", startIndex) + 2;
        assert (startIndex != -1) : "ContentFormatError - " + messageLog;
        return messageLog.substring(startIndex);
    }

}
