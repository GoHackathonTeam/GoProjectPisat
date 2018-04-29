package com.dekinci.lksbstu.communication.server

import android.content.BroadcastReceiver
import com.dekinci.lksbstu.PolyApp
import com.dekinci.lksbstu.communication.structure.Message
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class DialogsServer {
    private val dialogFile = File(PolyApp.getInnerDir(), "dialogs")
    private val messages = mutableListOf<Message>()

    init {
        if (!dialogFile.exists()) {
            val writer = dialogFile.writer()
            writer.write("00000001\n03\nLol Kek!\n")
            writer.close()
        }
        val lines = dialogFile.readLines()
        val size = lines.size / 3
        for (i in 0 until size) {
            val offset = i * 3
            messages += Message(lines[offset], lines[offset + 1], lines[offset + 2])
        }
        messages.reverse()
    }

    fun getDialogs(userId: String) : MutableList<String> {
        val result = mutableSetOf<String>()
        val userId2 = "01"
        for (message in messages) {
            println(message)
            if (message.senderId == userId2)
                result += message.receiverId
            if (message.receiverId == userId2)
                result += message.senderId
        }
        return result.toMutableList()
    }

    fun getMessageList(firs: String, second: String, from: Int, to: Int) : MutableList<Message> {
        val result = mutableListOf<Message>()
        val size = messages.size
        val first = "01"

        fun condition(message: Message) =
            (first == message.receiverId && second == message.senderId ||
                first == message.senderId && second == message.receiverId)

        var i = 0
        var j = 0
        while (j < size && i < from) {
            if (condition(messages[j]))
                i++
            j++
        }
        while (j < size && i < to) {
            if (condition(messages[j])) {
                result += messages[j]
                i++
            }
            j++
        }
        return result
    }

    fun sendMessage(sender: String, receiver: String, message: String) {
        messages.add(0, Message(sender, receiver, message))
        val writer = FileWriter(dialogFile, true)
        writer.write(sender)
        writer.write(10)
        writer.write(receiver)
        writer.write(10)
        writer.write(message)
        writer.write(10)
        writer.close()
    }
}