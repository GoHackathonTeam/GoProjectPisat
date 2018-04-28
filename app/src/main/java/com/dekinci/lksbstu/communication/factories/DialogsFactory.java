package com.dekinci.lksbstu.communication.factories;

import com.dekinci.lksbstu.communication.structure.Message;
import com.dekinci.lksbstu.utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.dekinci.lksbstu.utils.Utils.randomFromArr;

public class DialogsFactory {
    private static final String FILENAME = "dialogFile.txt";
    private File dialogs;

    private static final String[] messages = {"Привет", "привет", "приффки!!! <3",
            "как дела?", "что задали", "го хакатон", "а пойдем кодить!",
            "я сделал мердж и ничего не работает", "лол"};

    public DialogsFactory() {

    }

    public List<Message> makeMessages(int length) {
        ArrayList<Message> messages = new ArrayList<>();

        for (int i = 0; i < length; i++)
            messages.add(new Message(
                    randomFromArr(UserFactory.names), "0", randomFromArr(DialogsFactory.messages)));

        return messages;
    }
}
