package com.company;

import java.util.ArrayList;

/**
 * Created by rdw1995 on 10/17/16.
 */
public class MessageWrapper {
    ArrayList<Message> messages;

    public MessageWrapper() {
    }

    public MessageWrapper(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

}
