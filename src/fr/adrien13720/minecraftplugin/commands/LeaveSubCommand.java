package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class LeaveSubCommand extends SubCommand {
	public boolean onCommand(Player player, Command command, String[] args) {
		if (!(args.length == 1)) {
			player.sendMessage("§cLa commande s'utilise comme ça : /faction leave");
			return false;
		}
		else {
			for (Team team : CommandFaction.teams) {
				if (!CommandFaction.teamleadersnames.contains(player.getName())) {
					if (team.hasEntry(player.getName()) && team.getSize() == 1) { // teste si le joueur est déjà dans une faction
						player.sendMessage("§6La faction " + team.getName() + " a été dissoute");
						team.removeEntry(player.getName());
						player.setDisplayName(player.getName());

						return true;
					}
					if (team.hasEntry(player.getName())) {
						team.removeEntry(player.getName());
						player.sendMessage("Vous avez quitté la faction : " + team.getName());
						player.setDisplayName(player.getName());
						return true;
					}
				}
				else {
					if (team.hasEntry(player.getName()) && team.getSize() == 1) {
						player.sendMessage("§6La faction " + team.getName() + " a été dissoute");
						team.removeEntry(player.getName());
						CommandFaction.teamleaders.remove(player);
						CommandFaction.teamleadersnames.remove(player.getName());
						player.setDisplayName(player.getName());
						player.setPlayerListName(player.getName());
						return true;
					}
					if (team.hasEntry(player.getName()));
						player.sendMessage("§cVous ne pouvez pas quitter une faction si vous en etes le chef et qu'il reste d'autres joueurs dans votre faction");
						return false;
				}
			}
			player.sendMessage("§cVous ne pouvez pas quitter une faction si vous n'etes pas dans une faction");
			return false;
		}		
	}
}
