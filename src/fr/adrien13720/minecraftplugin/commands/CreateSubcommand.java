package fr.adrien13720.minecraftplugin.commands;

import java.util.HashSet;
import java.util.Set;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class CreateSubcommand extends SubCommand {
	

	public boolean onCommand(Player player, Command command, String[] args) {

		
		if(args.length == 1) { // teste s'il y a un nom de faction
			player.sendMessage("�cUne faction doit avoir un nom, gros d�bile");
			return false;
		}
		player.sendMessage(args[1]);
		if(!(CommandFaction.teamnames.contains(args[1]))) { // test si la faction existe d�j�
				CommandFaction.board.registerNewTeam(args[1]);
				CommandFaction.teams = CommandFaction.board.getTeams();
				for (Team team : CommandFaction.teams) {
					CommandFaction.teamnames.add(team.getName());
					}
				return true;
		}
		else {
			player.sendMessage("�cCe nom de faction est d�j� utilis�");
			return false;
		}
			
	}
}
