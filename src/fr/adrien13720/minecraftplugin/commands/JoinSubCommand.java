package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class JoinSubCommand extends SubCommand {
	
	public boolean onCommand(Player player, Command command, String[] args) {
		if (!(args.length == 2)) {
			player.sendMessage("§cLa commande s'utilise comme ça : /faction join <nomfaction>");
			return false;
		}
		else {
			for (Team team : CommandFaction.teams) {
				if (team.hasEntry(player.getName())) { // teste si le joueur est déjà dans une faction
					player.sendMessage("§cVous etes déjà dans une faction");
					return false;
				}
			}
			for (Team team : CommandFaction.teams) {
				if (team.getName() == args[1]) {
					team.addEntry(player.getName());
					player.sendMessage("§6Vous avez rejoint la faction :" + team.getName());
					return true;
				}
			}
			player.sendMessage("§cVous ne pouvez pas rejoindre une faction qui n'existe pas");
			return false;
		}		
	}
}
