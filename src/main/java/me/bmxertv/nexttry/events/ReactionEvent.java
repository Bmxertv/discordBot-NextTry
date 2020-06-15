package me.bmxertv.nexttry.events;

import me.bmxertv.nexttry.NextTry;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 01.01.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class ReactionEvent extends ListenerAdapter {

    public ReactionEvent() {
        NextTry.jda.addEventListener(this);
    }

    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

        if (event.getChannel().getId().equals("661949579456937985")) {
            Role role = event.getGuild().getRoleById("661949836861636618");
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
        }

    }

    public void onGuildMessageReactionRemove(GuildMessageReactionRemoveEvent event) {

        if (event.getChannel().getId().equals("661949579456937985")) {
            Role role = event.getGuild().getRoleById("661949836861636618");
            event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
        }

    }

}
