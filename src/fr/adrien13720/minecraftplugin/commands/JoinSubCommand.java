package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class JoinSubCommand extends SubCommand {
	
	public boolean onCommand(Player player, Command command, String[] args) {
		if (!(args.length == 2)) {
			player.sendMessage("§cLa commande s'utilise comme ça : /faction join <nomfaction>");
			return false;
		}
		if(CommandFaction.deletedteams.contains(args[1])) {
			player.sendMessage("§cCette faction n'existe plus");
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
				
				if (team.getName().equals(args[1])) {
					System.out.println(player.getName());
					team.addEntry(player.getName());
					player.setDisplayName(team.getColor()+ "[" + team.getName() + "] " + player.getName() + ChatColor.WHITE);
					player.setPlayerListName(team.getColor() + "[" + team.getName() + "] " + team.getColor() + player.getName());
					player.sendMessage("§6Vous avez rejoint la faction : " + team.getName());
					CommandFaction.teamdiamondcount.put(team.getName(), CommandFaction.teamdiamondcount.get(team.getName()) + CommandFaction.diamondcount.get(player.getName()));

					return true;
				}
			}
			player.sendMessage("§cVous ne pouvez pas rejoindre une faction qui n'existe pas");
			return false;
		}		
	}
}
