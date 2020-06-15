package me.bmxertv.nexttry.core;

import me.bmxertv.nexttry.NextTry;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.ArrayList;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 03.12.2019
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class CommandManager extends ListenerAdapter {

    private static ArrayList<Command> commands = new ArrayList<Command>();

    public void load() {
        NextTry.jda.addEventListener(this);
        commands.forEach(obj ->  {NextTry.jda.addEventListener(obj);});
    }

    public static void addCommand(Object obj) {
        commands.add((Command) obj);
    }

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        String[] message = event.getMessage().getContentRaw().trim().split(" ");
        String invoke = message[0];
        String call = invoke.replace(Config.INVOKE, "");
        String[] args = message;

        commands.forEach(cmd -> {
            if (invoke.startsWith(Config.INVOKE) && cmd.call().equalsIgnoreCase(call) && !event.getAuthor().isBot()) {
                cmd.execute(args, event);
            }
        });

    }

    public static ArrayList<Command> getCommands() {
        return commands;
    }
}
