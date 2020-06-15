package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.IOException;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 21.03.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class restartCommand extends Command {

    @Override
    public String call() {
        return "restart";
    }

    @Override
    public String help() {
        return "restart";
    }

    @Override
    public String description() {
        return "Startet den Bot neu";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        event.getJDA().getPresence().setPresence(OnlineStatus.DO_NOT_DISTURB, Activity.playing("Neustarten..."));

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("D:\\Coding\\Discord\\NextTry\\target\\restart.bat");

        try {
            Process process = processBuilder.start();
            int exitVal = process.waitFor();
            if (exitVal == 0)
                System.exit(0);
        } catch (IOException | InterruptedException e) {
        }


        return false;
    }
}
