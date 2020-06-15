package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.List;
import java.util.concurrent.TimeUnit;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 15.12.2019
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class ChatClearCommand extends Command {

    @Override
    public String call() {
        return "clear";
    }

    @Override
    public String help() {
        return "clear <amount>";
    }

    @Override
    public String description() {
        return "Löscht eine anzhal an nachrichten.";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        if (args.length == 2) {

            event.getMessage().delete().queue();

            int amount = Integer.parseInt(args[1]);
            if (amount >= 1 && amount <= 100) {

                MessageHistory history = new MessageHistory(event.getChannel());
                List<Message> messages;
                messages = history.retrievePast(amount).complete();
                event.getChannel().deleteMessages(messages).queue();
                event.getChannel().sendMessage("Die Nachrichten wurden gelöscht.").complete().delete().queueAfter(10, TimeUnit.SECONDS);

            } else {
                event.getChannel().sendMessage("Bitte gebe eine zahl zwischen 1 und 100 ein!").queue();
            }

        } else {
            event.getChannel().sendMessage(Config.INVOKE + help()).queue();
        }

        return false;
    }
}
