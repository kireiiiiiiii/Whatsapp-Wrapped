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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import whatsappwrapped.Common.Message;
import whatsappwrapped.Enums.MessageType;

/**
 * Class to read from and format a {@code .txt} WhatsApp message log file.
 * 
 */
public class ChatLogUtil {

    /////////////////
    // Public methods
    ////////////////

    /**
     * Converts a file to an ArrayList of Strings, putting every line of the file in
     * a separate element. Returns {@code null} if an exception appears.
     * 
     * @param logFile - Target {@code File} object.
     * @return {@code ArrayList} containing the contents of the file.
     */
    public static ArrayList<Message> getMessageList(File logFile) {

        ArrayList<Message> messages = new ArrayList<Message>();

        try {
            // Create the file reader.
            Scanner scanner = new Scanner(logFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (!isMessageStart(line)) {

                    // Append on previous message
                    String temporaryContent = messages.get(messages.size() - 1).getContents();
                    messages.get(messages.size() - 1).setContents(temporaryContent + "\n" + line);

                } else if (isMessageLog(line)) {

                    messages.add(new Message(MessageUtil.getSender(line), MessageUtil.getContents(line),
                            MessageUtil.getTime(line), MessageUtil.getDate(line), MessageType.REGULAR));

                } else /* Is a system message */ {

                    MessageType messageType = getSystemMessageType(line);
                    if (messageType != MessageType.UNKNOWN) {
                        // TODO: Add system message
                    }

                }
            }
            scanner.close();
            // Return null if file not found
        } catch (FileNotFoundException e) {
            return null;
        }
        return messages;
    }

    /////////////////
    // Message type filters
    ////////////////

    /**
     * Determines, if a line is a start of a message log. Used when handeling
     * multilined message logs.
     * 
     * @param line - WhatsApp log line.
     * @return {@code boolean} factor.
     */
    private static boolean isMessageStart(String line) {
        // Regular expression to match the date and time format followed by " - "
        String dateTimePattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - ";
        return line.matches(dateTimePattern + ".*");
    }

    /**
     * Gets the {@code MessageType} of a system message from a message log.
     * 
     * @param systemMessageLog - message log {@code String}.
     * @return enum value of {@code MessageType}.
     */
    public static MessageType getSystemMessageType(String systemMessageLog) {
        MessageType systemMessageType;

        if (isEncryptionMessage(systemMessageLog)) {
            systemMessageType = MessageType.ENCRYPTION;
        } else if (isBlockedContactMessage(systemMessageLog)) {
            systemMessageType = MessageType.BLOCKED;
        } else if (isUnblockMessage(systemMessageLog)) {
            systemMessageType = MessageType.UNBLOCK;
        } else if (isGroupCreationMessage(systemMessageLog)) {
            systemMessageType = MessageType.GROUP_CREATION;
        } else if (isUserAddedMessage(systemMessageLog)) {
            systemMessageType = MessageType.USER_ADDED;
        } else if (isChangedGroupDescriptionMessage(systemMessageLog)) {
            systemMessageType = MessageType.DESCRIPTION_CHANGED;
        } else if (isGroupJoinMessage(systemMessageLog)) {
            systemMessageType = MessageType.USER_JOINED;
        } else if (isSettingsChangeMessage(systemMessageLog)) {
            systemMessageType = MessageType.SETTINS_CHANGED;
        } else if (isGroupNameChangeMessage(systemMessageLog)) {
            systemMessageType = MessageType.GROUP_NAME_CHANGE;
        } else if (isGroupIconChangeMessage(systemMessageLog)) {
            systemMessageType = MessageType.GROUP_ICON_CHANGE;
        } else if (isGroupLeftMessage(systemMessageLog)) {
            systemMessageType = MessageType.USER_LEFT;
        } else if (isUserRemovedMessage(systemMessageLog)) {
            systemMessageType = MessageType.USER_REMOVED;
        } else if (isUserAddedUserMessage(systemMessageLog)) {
            systemMessageType = MessageType.USER_ADDED_2;
        } else if (isCallStartedMessage(systemMessageLog)) {
            systemMessageType = MessageType.CALL_STARTED;
        } else if (isVideoCallStartedMessage(systemMessageLog)) {
            systemMessageType = MessageType.VIDEO_CALL_STARTED;
        } else {
            systemMessageType = MessageType.UNKNOWN;
        }

        return systemMessageType;
    }

    /**
     * Method determining if a WhatsApp log line is a message log, and not anything
     * else like block messages, group name/icon/description change messages etc.
     * 
     * @param line - WhatsApp log line.
     * @return
     */
    private static boolean isMessageLog(String line) {
        return !(isEncryptionMessage(line) || isBlockedContactMessage(line) || isUnblockMessage(line)
                || isGroupCreationMessage(line) || isUserAddedMessage(line)
                || isChangedGroupDescriptionMessage(line) || isGroupJoinMessage(line)
                || isSettingsChangeMessage(line) || isGroupNameChangeMessage(line)
                || isGroupIconChangeMessage(line) || isGroupLeftMessage(line) || isUserRemovedMessage(line)
                || isUserAddedUserMessage(line) || isCallStartedMessage(line) || isVideoCallStartedMessage(line));
    }

    private static boolean isEncryptionMessage(String line) {
        // Regular expression to match the encryption message format
        String encryptionPattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - Messages and calls are end-to-end encrypted\\. No one outside of this chat, not even WhatsApp, can read or listen to them\\. Tap to learn more\\.$";
        return line.matches(encryptionPattern);
    }

    private static boolean isBlockedContactMessage(String line) {
        // Regular expression to match the pattern with variable date and time
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - You blocked this contact\\. Tap to unblock\\.$";
        return line.matches(pattern);
    }

    private static boolean isUnblockMessage(String line) {
        // Regular expression to match the format of the unblock message
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - You unblocked this contact\\.$";
        return line.matches(pattern);
    }

    private static boolean isGroupCreationMessage(String line) {
        // Regular expression to match the group creation message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ created group \".+\"$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isUserAddedMessage(String line) {
        // Regular expression to match the user added message format with "was added" or
        // "were added"
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ (was|were) added$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isChangedGroupDescriptionMessage(String line) {
        // Regular expression to match the group description change message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ changed the group description$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isGroupJoinMessage(String line) {
        // Regular expression to match the group join message format with flexible
        // name/number
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ joined using this group's invite link$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isSettingsChangeMessage(String line) {
        // Regular expression to match the settings change message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - ~?\\s?.+ changed the settings so only admins can edit the group settings$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isGroupNameChangeMessage(String line) {
        // Regular expression to match the group name change message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - ~?\\s?.+ changed the group name from \".+\" to \".+\"$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isGroupIconChangeMessage(String line) {
        // Regular expression to match the group icon change message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - ~?\\s?.+ changed this group's icon$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isGroupLeftMessage(String line) {
        // Regular expression to match the group left message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - ~?\\s?.+ left$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isUserRemovedMessage(String line) {
        // Regular expression to match the user removed message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ removed .+$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isUserAddedUserMessage(String line) {
        // Regular expression to match the user added message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ added .+$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isCallStartedMessage(String line) {
        // Regular expression to match the call started message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ started a call$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }

    private static boolean isVideoCallStartedMessage(String line) {
        // Regular expression to match the video call started message format
        String pattern = "^\\d{1,2}/\\d{1,2}/\\d{2}, \\d{2}:\\d{2} - .+ started a video call$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(line);
        return matcher.matches();
    }
}
