package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class KickSubCommand extends SubCommand {
	
	public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
		Player player = (Player) sender;

		if(args.length != 2) {
			player.sendMessage("§cCette commande s'utilise : /faction kick <nomdujoueur>");
			return false;
		}
		else {
			if(CommandFaction.teamleadersnames.contains(player.getName())) {
				if(args[1].equals(player.getName())) {
					player.sendMessage("§cVous ne pouvez pas vous autokick");
					return false;
				}
				else {
					for(Team team : CommandFaction.teams) {
						if(team.hasEntry(player.getName()) && team.hasEntry(args[1])) {
							if(Bukkit.getPlayer(args[1]).equals(null)) {
								player.sendMessage("§cCe joueur n'est pas en ligne");
								return false;
							}
							else {
								Player playerkicked = Bukkit.getPlayer(args[1]);
								team.removeEntry(args[1]);
								player.sendMessage(args[1] + "§6 a été kick de la faction");
								playerkicked.sendMessage("§6Vous avez été viré de la faction " + team.getColor() + team.getName());
								CommandFaction.teamdiamondcount.put(team, CommandFaction.teamdiamondcount.get(team) - CommandFaction.diamondcount.get(args[1]));
								return true;								
							}
						}
					}
					player.sendMessage("§cVous n'êtes pas dans la même faction que le joueur spécifié");
					return false;
				}
			}
			else {
				player.sendMessage("§cVous n'êtes pas chef de faction");
				return false;
			}
		}
	}
}
