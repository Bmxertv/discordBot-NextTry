package me.bmxertv.nexttry.core;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 03.12.2019
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public abstract class Command extends ListenerAdapter {

    public abstract String call();
    public abstract String help();
    public abstract String description();
    public abstract HelpCategory helpCategory();
    public abstract boolean execute(String[] args, GuildMessageReceivedEvent event);

    public enum HelpCategory {
        MIC,MOD,FUN;
    }
}
