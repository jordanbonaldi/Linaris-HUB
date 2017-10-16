package net.neferett.linaris.lobby.jump;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.neferett.linaris.lobby.managers.ScoreboardManager;


public class JumpCommand implements CommandExecutor {

	JumpsManager manager;

	public JumpCommand(JumpsManager manager) {
		this.manager = manager;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			if (args.length == 1)
				if (args[0].equals("end"))
					if (manager.inJump((Player) sender))
						manager.stopJump((Player) sender);
			ScoreboardManager.jump.put(((Player) sender).getPlayer(), false);

		}
		
		return false;
	}

}
