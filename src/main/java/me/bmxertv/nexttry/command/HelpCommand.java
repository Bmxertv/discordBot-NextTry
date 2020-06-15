package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.core.CommandManager;
import me.bmxertv.nexttry.utils.Colors;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.rmi.activation.ActivationDesc;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 10.12.2019
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class HelpCommand extends Command {

    @Override
    public String call() {
        return "help";
    }

    @Override
    public String help() {
        return "help";
    }

    @Override
    public String description() {
        return "Zeigt dir die Hilfe";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MIC;
    }

    int temp = 0;

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setAuthor(event.getAuthor().getName(), null, event.getAuthor().getAvatarUrl())
                .setColor(Colors.blue)
                .setFooter("Diese Nachricht löscht sich automatich nach 30 Sekunden.", event.getAuthor().getAvatarUrl());

        HashMap<Integer, String> call = new HashMap<>();
        HashMap<Integer, String> description = new HashMap<>();
        HashMap<Integer, HelpCategory> category = new HashMap<>();

        CommandManager.getCommands().forEach((cmd) -> {
            call.put(temp, cmd.help());
            description.put(temp, cmd.description());
            category.put(temp, cmd.helpCategory());
            temp++;
        });

        StringBuilder micBuilder = new StringBuilder();
        StringBuilder funBuilder = new StringBuilder();
        StringBuilder modBuilder = new StringBuilder();
        for (int i = 0; i < CommandManager.getCommands().size(); i++) {
            switch (category.get(i)) {
                case MIC:
                    micBuilder.append(MessageFormat.format("{0}{1} » {2}\n", Config.INVOKE, call.get(i), description.get(i)));
                    break;
                case FUN:
                    funBuilder.append(MessageFormat.format("{0}{1} » {2}\n", Config.INVOKE, call.get(i), description.get(i)));
                    break;
                case MOD:
                    modBuilder.append(MessageFormat.format("{0}{1} » {2}\n", Config.INVOKE, call.get(i), description.get(i)));
                    break;
            }
        }

        embedBuilder.addField("Mic", micBuilder.toString(), false);
        embedBuilder.addField("Fun", funBuilder.toString(), false);
        embedBuilder.addField("Mod", modBuilder.toString(), false);

        event.getChannel().sendMessage(embedBuilder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);

        return false;
    }
}
