package me.bmxertv.nexttry.events;

import me.bmxertv.nexttry.NextTry;
import net.dv8tion.jda.api.entities.Category;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

/****************************************************************************************
*
*   Copyright © Christopher Schulz | 02.03.2020
*   
*   Diese Dokument darf nicht weitergegeben werden, 
*   ohne das Einverständnis von Christopher Schulz.
*   Solte dies trozdem getan werden, dann ist es so.
* 
****************************************************************************************/

public class AutoChannelEvent extends ListenerAdapter {

    public AutoChannelEvent() {
        NextTry.jda.addEventListener(this);
    }

    private final String tempSuffix = "[TEMP]";
    private final String acSuffix = "[AC]";

    private void createAutoChannel(Member member, VoiceChannel joinedChannel) {
        Category category = joinedChannel.getParent();
        int position = joinedChannel.getPosition() + 1;
        int id = (int) category.getChannels().stream().filter(c -> c.getName().endsWith(acSuffix)).count();
        String name = joinedChannel.getName().replace(tempSuffix, id + " ") + acSuffix;

        VoiceChannel channel = joinedChannel.getGuild().createVoiceChannel(name)
                .setBitrate(joinedChannel.getBitrate())
                .setUserlimit(joinedChannel.getUserLimit())
                .setParent(category)
                .complete();
        channel.getManager().sync(joinedChannel).queue();
        joinedChannel.getGuild().modifyVoiceChannelPositions().selectPosition(channel).moveTo(position).queue();
        joinedChannel.getGuild().moveVoiceMember(member, channel).queue();
    }

    @Override
    public void onGuildVoiceJoin(@Nonnull GuildVoiceJoinEvent event) {
        if (event.getChannelJoined().getName().endsWith(tempSuffix)) {
            createAutoChannel(event.getMember(), event.getChannelJoined());
        }
    }

    @Override
    public void onGuildVoiceMove(@Nonnull GuildVoiceMoveEvent event) {
        if (event.getChannelJoined().getName().endsWith(tempSuffix)) {
            createAutoChannel(event.getMember(), event.getChannelJoined());
        }
        if (event.getChannelLeft().getName().endsWith(acSuffix) && event.getChannelLeft().getMembers().isEmpty()) {
            event.getChannelLeft().delete().queue();
        }
    }

    @Override
    public void onGuildVoiceLeave(@Nonnull GuildVoiceLeaveEvent event) {
        if (event.getChannelLeft().getName().endsWith(acSuffix) && event.getChannelLeft().getMembers().isEmpty()) {
            event.getChannelLeft().delete().queue();
        }
    }
}
