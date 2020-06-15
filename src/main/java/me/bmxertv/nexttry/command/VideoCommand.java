package me.bmxertv.nexttry.command;

import com.sun.org.apache.xerces.internal.xs.StringList;
import me.bmxertv.nexttry.core.Command;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.text.MessageFormat;

/****************************************************************************************
 *
 *   Copyright © Christopher Schulz | 10.12.2019
 *
 *   Diese Dokument darf nicht weitergegeben werden,
 *   ohne das Einverständnis von Christopher Schulz.
 *   Solte dies trozdem getan werden, dann ist es so.
 *
 ****************************************************************************************/

public class VideoCommand extends Command {

    @Override
    public String call() {
        return "video";
    }

    @Override
    public String help() {
        return "video";
    }

    @Override
    public String description() {
        return "Sendet dir einen Video Link!";
    }

    @Override
    public HelpCategory helpCategory() {
        return HelpCategory.MIC;
    }

    @Override
    public boolean execute(String[] args, GuildMessageReceivedEvent event) {

        if (!event.getMessage().getMember().getVoiceState().inVoiceChannel()) {
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " du musst dich in einem Voice Channel befinden!").queue();
            return false;
        }

        String link = MessageFormat.format("https://discordapp.com/channels/{0}/{1}", event.getGuild().getId(), event.getMember().getVoiceState().getChannel().getId());

        event.getChannel().sendMessage(link).queue();

        return false;
    }
}
