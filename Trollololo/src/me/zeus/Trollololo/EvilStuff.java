
package me.zeus.Trollololo;


import net.minecraft.server.v1_6_R2.EntityPlayer;
import net.minecraft.server.v1_6_R2.Packet24MobSpawn;

import org.bukkit.craftbukkit.v1_6_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;



public class EvilStuff
{
    
    
    public static void crash(Player p)
    {
        EntityPlayer nmsPlayer = ((CraftPlayer) p).getHandle();
        nmsPlayer.playerConnection.sendPacket(new Packet24MobSpawn(nmsPlayer));
    }
    
    
    
    public static void kick(Player p, FakeMessage msg)
    {
        p.kickPlayer(msg.getMessage());
    }
    
    
    public enum FakeMessage
    {
        END_OF_STREAM("§7Connection Lost\n\n§fEnd of stream"),
        
        ;
        
        
        String message;
        
        
        
        FakeMessage(String s)
        {
            this.message = s;
        }
        
        
        
        public String getMessage()
        {
            return message;
        }
    }
    
}
