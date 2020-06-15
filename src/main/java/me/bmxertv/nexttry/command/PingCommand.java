package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/****************************************************************************************
 *
 *   Copyright © Christopher Schulz | 03.12.2019
 *
 *   Diese Dokument darf nicht weitergegeben werden,
 *   ohne das Einverständnis von Christopher Schulz.
 *   Solte dies trozdem getan werden, dann ist es so.
 *
 ****************************************************************************************/

public class PingCommand extends Command {

    @Override
    public String call() {
        return "ping";
    }

    @Override
    public String help() {
        return "ping";
    }

    @Override
    public String description() {
        return "Ping!";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.FUN;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        event.getChannel().sendMessage("Pong! " + event.getAuthor().getAsMention()).queue();

        return false;
    }
}
