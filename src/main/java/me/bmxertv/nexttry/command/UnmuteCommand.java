package me.bmxertv.nexttry.command;

import com.sun.org.apache.xpath.internal.operations.Mod;
import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 26.02.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class UnmuteCommand extends Command {

    @Override
    public String call() {
        return "unmute";
    }

    @Override
    public String help() {
        return "unmute <@user>";
    }

    @Override
    public String description() {
        return "Entmutet einen Member";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        Role role = event.getGuild().getRolesByName(Config.muteRole, true).get(0);
        Member target = event.getMessage().getMentionedMembers().get(0);

        if (target.getRoles().contains(role)) {
            event.getGuild().removeRoleFromMember(target, role).queue();
            event.getChannel().sendMessage(target.getAsMention() + " du wurdest von dem Member " + event.getMember().getAsMention() + " enmutet!").queue();
            return false;
        } else {
            event.getChannel().sendMessage(event.getMember().getAsMention() + ", du kannst denn Member " + target.getAsMention() + " nicht entmuten, weil er nicht gemutet ist!").queue();
            return false;
        }
    }
}
