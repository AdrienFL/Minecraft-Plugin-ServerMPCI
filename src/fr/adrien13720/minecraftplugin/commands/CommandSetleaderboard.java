package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetleaderboard implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Player player = (Player) sender;
		int diam = 0;
		try {
			diam = Integer.parseInt(args[2]);
		}
		catch(NumberFormatException e) {
			diam = 0;
		}
		if(args.length != 3) {
			player.sendMessage("§cLa commande s'utilise : /Setleaderboard <add/remove> <nomdejoueur> <diamondcount>");
			return false;
		}
		else {
			if(args[0].equalsIgnoreCase("add")) {
				CommandFaction.diamondcount.put(args[1], diam);
				CommandFaction.playerdiamondlist.add(args[1]);
				return true;
			}
			if(args[0].equalsIgnoreCase("remove")) {
				CommandFaction.diamondcount.remove(args[1]);
				CommandFaction.playerdiamondlist.remove(args[1]);
				return true;
			}
			else {
				player.sendMessage("§cLa commande s'utilise : /Setleaderboard <add/remove> <team/player> <nomdefaction/nomdejoueur> <diamondcount>");
				return false;
			}
		}
	}
}
