package fr.adrien13720.minecraftplugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class KickSubCommand extends SubCommand {
	
	public boolean onCommand(Player player, Command cmd, String[] args) {

		if(args.length != 2) {
			player.sendMessage("�cCette commande s'utilise : /faction kick <nomdujoueur>");
			return false;
		}
		else {
			if(CommandFaction.teamleadersnames.contains(player.getName())) {
				System.out.println(args[1]);
				System.out.println(player.getName());
				if(args[1].equals(player.getName())) {
					player.sendMessage("�cVous ne pouvez pas vous autokick");
					return false;
				}
				else {
					for(Team team : CommandFaction.teams) {
						if(team.hasEntry(player.getName()) && team.hasEntry(args[1])) {
							if(Bukkit.getPlayer(args[1]).equals(null)) {
								player.sendMessage("�cCe joueur n'est pas en ligne");
								return false;
							}
							else {
								Player playerkicked = Bukkit.getPlayer(args[1]);
								team.removeEntry(args[1]);
								player.sendMessage(args[1] + "�6 a �t� renvoy� dans son pays");
								playerkicked.sendMessage("�6Vous avez �t� vir� de la faction " + team.getColor() + team.getName());
								CommandFaction.teamdiamondcount.put(team.getName(), CommandFaction.teamdiamondcount.get(team.getName()) - CommandFaction.diamondcount.get(args[1]));
								player.setDisplayName(player.getName());
								player.setPlayerListName(player.getName());
								return true;								
							}
						}
					}
					player.sendMessage("�cVous n'�tes pas dans la m�me faction que le joueur sp�cifi�");
					return false;
				}
			}
			else {
				player.sendMessage("�cVous n'�tes pas chef de faction");
				return false;
			}
		}
	}
}
