package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 01.01.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class ReactionTestCommand extends Command {

    @Override
    public String call() {
        return "reactionTest";
    }

    @Override
    public String help() {
        return "reactionTest";
    }

    @Override
    public String description() {
        return "Sendet eine Verifzierung nachricht";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        event.getChannel().sendMessage("Bitte klick auf den Hacken um dich zu verifzieren!").queue(msg -> {
            msg.addReaction("✅").queue();
            msg.pin().queue();
        });

        return false;
    }
}
