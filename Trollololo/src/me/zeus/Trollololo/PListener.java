
package me.zeus.Trollololo;


import java.util.List;

import me.zeus.Trollololo.EvilStuff.FakeMessage;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;



public class PListener implements Listener
{
    
    
    @EventHandler
    public void onLogin(PlayerLoginEvent e)
    {
        List<String> ss = Main.getInstance().getConfig().getStringList("players");
        for (String s : ss)
            if (e.getPlayer().getName().equalsIgnoreCase(s))
            {
                e.setResult(Result.KICK_OTHER);
                e.setKickMessage(FakeMessage.END_OF_STREAM.getMessage());
            }
    }
    
    
    
    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        List<String> ss = Main.getInstance().getConfig().getStringList("players");
        for (String s : ss)
            if (e.getPlayer().getName().equalsIgnoreCase(s))
                e.getPlayer().kickPlayer(FakeMessage.END_OF_STREAM.getMessage());
    }
}
