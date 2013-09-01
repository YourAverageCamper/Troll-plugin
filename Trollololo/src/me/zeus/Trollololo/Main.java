
package me.zeus.Trollololo;


import java.io.File;
import java.util.List;

import me.zeus.Trollololo.EvilStuff.FakeMessage;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;



public class Main extends JavaPlugin implements CommandExecutor
{
    
    
    private static Main instance;
    File config;
    
    
    
    @Override
    public void onEnable()
    {
        instance = this;
        getServer().getPluginManager().registerEvents(new PListener(), this);
        
        config = new File(getDataFolder() + "/config.yml");
        if (!config.exists())
            saveDefaultConfig();
        
    }
    
    
    
    @Override
    public void onDisable()
    {
        instance = null;
        saveConfig();
    }
    
    
    
    @Override
    public boolean onCommand(CommandSender cs, Command cmd, String lbl, String[] args)
    {
        
        if (cmd.getName().equalsIgnoreCase("troll"))
        {
            if (!cs.hasPermission("Troll.Admin"))
            {
                cs.sendMessage("§8[§4Trollolo§8] §cInvalid permissions.");
                return false;
            }
            if (args.length == 1)
                if (args[0].equals("list"))
                {
                    StringBuilder sb = new StringBuilder();
                    List<String> s = getConfig().getStringList("players");
                    for (String ss : s)
                        sb.append(ss).append(" ");
                    cs.sendMessage("§8[§4Trollolo§8] §6" + sb.toString());
                }
            
            if (args.length == 2)
                if (args[0].equals("add"))
                {
                    List<String> s = getConfig().getStringList("players");
                    s.add(args[1]);
                    getConfig().set("players", s);
                    saveConfig();
                    cs.sendMessage("§8[§4Trollolo§8] §aAdded " + args[1] + " to the list!");
                }
                else if (args[0].equals("remove"))
                {
                    List<String> s = getConfig().getStringList("players");
                    s.remove(args[1]);
                    getConfig().set("players", s);
                    saveConfig();
                    cs.sendMessage("§8[§4Trollolo§8] §cRemoved " + args[1] + " to the list!");
                }
                else
                {
                    Player target = getServer().getPlayer(args[0]);
                    if (target == null)
                    {
                        cs.sendMessage("§8[§4Trollolo§8] §cPlayer offline or doesn't exist!");
                        return false;
                    }
                    switch (args[1])
                    {
                        case "crash":
                            EvilStuff.crash(target);
                            cs.sendMessage("§8[§4Trollolo§8] §eYou crashed " + target.getName() + "'s client! >:D");
                            break;
                        case "connection":
                            EvilStuff.kick(target, FakeMessage.END_OF_STREAM);
                            break;
                    }
                }
        }
        return false;
    }
    
    
    
    public static Main getInstance()
    {
        return instance;
    }
    
}
