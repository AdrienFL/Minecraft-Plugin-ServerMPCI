package fr.adrien13720.minecraftplugin.commands;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;

public class PromoteSubCommand extends SubCommand {
	public boolean onCommand(Player player, Command command, String[] args) {
		if (!CommandFaction.teamleadersnames.contains(player.getName())) {
			player.sendMessage("§cVous ne pouvez pas utiliser cette commande si vous n'etes pas chef de faction");
			return false;
		}
		else {
			for (Team playerteam : CommandFaction.teams) {
				
				if (playerteam.hasEntry(player.getName())) {
					if (args.length == 2) {
						if (playerteam.hasEntry(args[1])) {
							for (Player playerprom : Bukkit.getServer().getOnlinePlayers()) {
								if (playerprom == Bukkit.getPlayer(args[1])) {
									CommandFaction.teamleaders.remove(player);
									CommandFaction.teamleadersnames.remove(player.getName());
									CommandFaction.teamleaders.add(playerprom);
									CommandFaction.teamleadersnames.add(playerprom.getName());
									player.setDisplayName(playerteam.getColor()+ "[" + playerteam.getName()+ "] " + player.getName() + ChatColor.WHITE);
									player.setPlayerListName(playerteam.getColor()+ "[" + playerteam.getName()+ "] " + player.getName());
									playerprom.setDisplayName(playerteam.getColor() + "[" + playerteam.getName() + "] " + ChatColor.BLACK + "[Chef] " + playerteam.getColor() + playerprom.getName()  + ChatColor.WHITE);
									playerprom.setPlayerListName(playerteam.getColor() + "[" + playerteam.getName() + "] "  + ChatColor.BLACK +"[Chef] "+ playerteam.getColor() + playerprom.getName());
									player.sendMessage(ChatColor.GOLD + playerprom.getName() + " est le nouveau chef de la faction" );
									playerprom.sendMessage(ChatColor.GOLD + "Vous etes le nouveau chef de la faction");
									return true;
								}
							}
							player.sendMessage("§cCe joueur n'est pas connecté");
							return false;
						}
						
						else {
							player.sendMessage("§cCe joueur n'est pas dans votre faction");
							return false;
						}
					}
					else {
						player.sendMessage("§cLa commande s'utilise comme ça : /faction promote <joueur>");
						return false;
					}
				}
			}
			player.sendMessage("§cVous ne pouvez pas utiliser cette commande si vous n'etes pas dans une faction");
			return false;
		}
	}
}
