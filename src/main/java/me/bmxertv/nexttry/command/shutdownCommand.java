package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.util.Timer;
import java.util.TimerTask;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 21.03.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class shutdownCommand extends Command {

    @Override
    public String call() {
        return "shutdown";
    }

    @Override
    public String help() {
        return "shutdown";
    }

    @Override
    public String description() {
        return "Fährt den Bot herunter";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        event.getJDA().getPresence().setPresence(OnlineStatus.DO_NOT_DISTURB, Activity.playing("Herunterfahren..."));
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                event.getJDA().shutdown();
                System.exit(-1);
            }
        }, 2000);

        return false;
    }
}
