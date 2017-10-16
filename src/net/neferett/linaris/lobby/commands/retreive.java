package net.neferett.linaris.lobby.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class retreive implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender instanceof Player) {
        	if(cmd.equals("retreive")){
        		if(sender.hasPermission("game.vip")){
        			
        		}else if(sender.hasPermission("game.megavip")){
        			
        		}else if(sender.hasPermission("game.vipelite")){
        			
        		}
        	}
        }
        return false;
    }

}
