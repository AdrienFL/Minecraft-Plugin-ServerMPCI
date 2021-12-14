package fr.adrien13720.minecraftplugin.commands;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;


public class CreateSubcommand extends SubCommand {
	

	public boolean onCommand(Player player, Command command, String[] args) {

		if(CommandFaction.deletedteams.contains(args[1])) {
			player.sendMessage("§cCe nom de faction a déjà été utilisé");
			return false;
		}
		if(!(args.length == 2)) { // teste s'il y a un nom de faction
			player.sendMessage("§cLa commande s'utilise comme : /faction create <nomdefaction>");
			return false;
		}
		if(args[1].length() >= 16) {
			player.sendMessage("§cCe nom de faction est trop long");
			return false;
		}

		for(Team team : CommandFaction.teams) {
			if(team.hasEntry(player.getName())) { 
				player.sendMessage("Vous etes déjà dans une faction");
				return false;
			}
		}
		
		if(!(CommandFaction.teamnames.contains(args[1]))) { // teste si la faction existe déjà
				CommandFaction.board.registerNewTeam(args[1]);
				CommandFaction.teams = CommandFaction.board.getTeams();
				CommandFaction.teamnames.add(args[1]);
				Team playerteam = CommandFaction.board.getTeam(args[1]);
				playerteam.addEntry(player.getName());
				CommandFaction.teamleaders.add(player);
				CommandFaction.teamleadersnames.add(player.getName());
				playerteam.setPrefix("[" + playerteam.getName()+ "] ");
				player.setPlayerListName(playerteam.getColor() + "[" + playerteam.getName() + "] "  + ChatColor.BLACK +"[Chef] "+ playerteam.getColor() + player.getName());
				player.setDisplayName(playerteam.getColor() + "[" + playerteam.getName() + "] " + ChatColor.BLACK + "[Chef] " + playerteam.getColor() + player.getName() + ChatColor.WHITE);
				CommandFaction.teamdiamondcount.put(playerteam.getName(), CommandFaction.diamondcount.get(player.getName()));
				CommandFaction.teamdiamondlist.add(playerteam.getName());
				return true;
		}
		else {
			player.sendMessage("§cCe nom de faction est déjà utilisé ou a déjà été utilisé");
			return false;
		}			
	}
}
