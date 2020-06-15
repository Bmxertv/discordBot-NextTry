package me.bmxertv.nexttry;

import me.bmxertv.nexttry.command.*;
import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.core.CommandManager;
import me.bmxertv.nexttry.events.AutoChannelEvent;
import me.bmxertv.nexttry.events.ReactionEvent;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import javax.security.auth.login.LoginException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 03.12.2019
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class NextTry {

    public static JDA jda;

    public static void main(String[] args) throws LoginException {
        jda = new JDABuilder(Config.TOKEN).build();

        new CommandManager().load();

        CommandManager.addCommand(new PingCommand());
        CommandManager.addCommand(new VideoCommand());
        CommandManager.addCommand(new HelpCommand());
        CommandManager.addCommand(new ChatClearCommand());
        CommandManager.addCommand(new MuteCommand());
        CommandManager.addCommand(new UnmuteCommand());
        CommandManager.addCommand(new restartCommand());
        CommandManager.addCommand(new shutdownCommand());

        CommandManager.addCommand(new ReactionTestCommand());

        new ReactionEvent();
        new AutoChannelEvent();


    }
}
