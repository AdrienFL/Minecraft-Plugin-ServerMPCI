package fr.adrien13720.minecraftplugin.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class ColorSubCommand extends SubCommand {


	public boolean onCommand(Player player, Command command, String[] args) {
		
		List<String> colorlist = new ArrayList<String>();
		ChatColor[] colorarrlist = ChatColor.values();
		
		for(ChatColor color : colorarrlist) {
			colorlist.add(color.toString());
			}
		ChatColor color = ChatColor.WHITE;
		String colorcode = "";
		switch(args[1]) {
			case "black":
				colorcode = "§0";
				color = ChatColor.BLACK;
				break;
			case "darkblue":
				colorcode = "§1";
				color = ChatColor.DARK_BLUE;
				break;
			case "darkgreen":
				colorcode = "§2";
				color = ChatColor.DARK_GREEN;
				break;
			case "darkaqua":
				colorcode = "§3";
				color = ChatColor.DARK_AQUA;
				break;				
			case "darkred":
				colorcode = "§4";
				color = ChatColor.DARK_RED;
				break;
			case "darkpurple":
				colorcode = "§5";
				color = ChatColor.DARK_PURPLE;
				break;
			case "gold":
				colorcode = "§6";
				color = ChatColor.GOLD;
				break;
			case "gray":
				colorcode = "§7";
				color = ChatColor.GRAY;
				break;
			case "darkgray":
				colorcode = "§8";
				color = ChatColor.DARK_GRAY;
				break;
			case "blue":
				colorcode = "§9";
				color = ChatColor.BLUE;
				break;
			case "green":
				colorcode = "§a";
				color = ChatColor.GREEN;
				break;
			case "aqua":
				colorcode = "§b";
				color = ChatColor.AQUA;
				break;
			case "red":
				colorcode = "§c";
				color = ChatColor.RED;
				break;
			case "purple":
				colorcode = "§d";
				color = ChatColor.LIGHT_PURPLE;
				break;
			case "yellow":
				colorcode = "§e";
				color = ChatColor.YELLOW;
				break;
			case "white":
				colorcode = "§f";
				color = ChatColor.WHITE;
				break;
			default:
				colorcode = "";
		}
		
		if(args.length >= 1) {
			if(colorlist.contains(colorcode)) {
				for(Team team : CommandFaction.teams) {
					if(args[1].equalsIgnoreCase(team.getColor().toString())) {
						player.sendMessage("§cCette couleur est déjà utilisée par une autre faction");
						return false;
					}	
				}
			}
			else {
				player.sendMessage("§cLa couleur spécifiée n'est pas reconnue");
				return false;
			}
		}
		else {
			player.sendMessage("§cLa couleur vide n'existe pas, gros débile");
			return false;
		}
		for(Team team : CommandFaction.teams) {
			if(team.hasEntry(player.getName())) {
				if (CommandFaction.teamleadersnames.contains(player.getName())) {
					Team playerteam = team;
					playerteam.setColor(color);
					playerteam.setPrefix(playerteam.getColor() + "[" +playerteam.getName() + "] ");
					for (String playername : playerteam.getEntries()) {
						Bukkit.getPlayer(playername).setDisplayName(playerteam.getColor() + "[" + playerteam.getName() + "] " + playername + ChatColor.WHITE);
						Bukkit.getPlayer(playername).setPlayerListName(team.getColor() + "[" + team.getName() + "] " + team.getColor() + player.getName());

					}
					player.setDisplayName(playerteam.getColor() + "[" + playerteam.getName() + "] " + ChatColor.BLACK + "[Chef] "+ playerteam.getColor() +player.getName() + ChatColor.WHITE);
					player.setPlayerListName(playerteam.getColor() + "[" + playerteam.getName() + "] " + ChatColor.BLACK + "[Chef] "+ playerteam.getColor() +player.getName());
					player.sendMessage("La couleur de la faction a été modifiée");
					
					return true;
				}
				else {
					player.sendMessage("§cVous n'etes pas chef de votre faction");
					return false;
				}
			}
			else {
				player.sendMessage("Vous n'etes pas dans une faction");
				return false;
			}
		}
		
		return false;

	}
	
}
