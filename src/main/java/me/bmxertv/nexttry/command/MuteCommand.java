package me.bmxertv.nexttry.command;

import me.bmxertv.nexttry.core.Command;
import me.bmxertv.nexttry.utils.Config;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.awt.*;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 26.02.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class MuteCommand extends Command {

    @Override
    public String call() {
        return "mute";
    }

    @Override
    public String help() {
        return "mute <@user>";
    }

    @Override
    public String description() {
        return "Mutet eine Spieler";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MOD;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        if (event.getGuild().getRolesByName(Config.muteRole, true).isEmpty()) {

           event.getGuild().createRole()
                    .setName(Config.muteRole)
                    .setColor(new Color(122, 122, 122))
                    .setMentionable(false)
                    .setPermissions(Permission.EMPTY_PERMISSIONS).queue();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }

            event.getGuild().getCategories().forEach(category -> {
                category.getManager().putPermissionOverride(event.getGuild().getRolesByName(Config.muteRole, true).get(0),
                        0, 2145910271).queue();
            });

            event.getGuild().getTextChannels().forEach(channel -> {
                channel.getManager().putPermissionOverride(event.getGuild().getRolesByName(Config.muteRole, true).get(0),
                        0, 2145910271).queue();
            });

            event.getGuild().getVoiceChannels().forEach(channel -> {
                channel.getManager().putPermissionOverride(event.getGuild().getRolesByName(Config.muteRole, true).get(0),
                        0, 2145910271).queue();
            });

        }

        Role role = event.getGuild().getRolesByName(Config.muteRole, true).get(0);
        Member target = event.getMessage().getMentionedMembers().get(0);

        event.getGuild().addRoleToMember(target, role).queue();
        event.getChannel().sendMessage(target.getAsMention() + " du wurdest von dem Member " + event.getMember().getAsMention() + " gemutet!").queue();

        return false;
    }
}
