package com.dekinci.lksbstu.communication.server

import android.content.BroadcastReceiver
import com.dekinci.lksbstu.PolyApp
import com.dekinci.lksbstu.communication.structure.Message
import java.io.File

class DialogsServer {
    private val dialogFile = File(PolyApp.getInnerDir(), "dialogs")
    private val messages = mutableListOf<Message>()

    init {
        val lines = dialogFile.readLines()
        val size = lines.size / 3
        for (i in 1..size) {
            val offset = i * 3
            messages += Message(lines[offset], lines[offset + 1], lines[offset + 2])
        }
        messages.reverse()
    }

    fun getDialogs(userId: String) : MutableList<String> {
        val result = mutableSetOf<String>()
        for (message in messages) {
            if (message.senderId == userId)
                result += message.receiverId
            if (message.receiverId == userId)
                result += message.senderId
        }
        return result.toList() as MutableList<String>
    }

    fun getMessageList(first: String, second: String, from: Int, to: Int) : MutableList<Message> {
        val result = mutableListOf<Message>()
        val size = messages.size

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
        val writer = dialogFile.writer()
        writer.write(sender)
        writer.write(10)
        writer.write(receiver)
        writer.write(10)
        writer.write(message)
        writer.write(10)
    }
}