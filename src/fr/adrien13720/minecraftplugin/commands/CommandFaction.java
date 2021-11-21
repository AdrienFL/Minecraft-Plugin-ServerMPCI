package fr.adrien13720.minecraftplugin.commands;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class CommandFaction implements CommandExecutor {
	
	public static ScoreboardManager manager = Bukkit.getScoreboardManager();
	public static Scoreboard board = manager.getNewScoreboard();
	public static Objective objective = board.registerNewObjective("Factions", "dummy", "Factions Title"); //Creation du scoreboard Factions   
	public static Set <Team> teams = board.getTeams();
	public static Set <String> teamnames = new HashSet<>();
	public static Set <Player> teamleaders = new HashSet<>();
	public static Set <String> teamleadersnames = new HashSet<>();

	private Map<String, SubCommand> commands = new HashMap<>();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String cmd2, String[] args) {

		
		if(sender instanceof Player) {
			Player player = (Player) sender;
			if(args.length == 0) { //teste si le joueur a mis des arguments
				player.sendMessage("§cCette commande s'utilise avec des arguments");
				return false;
			}
			
			if(!commands.containsKey(args[0])) { // test si la sous commande est valide
				player.sendMessage("§cCette sous commande n'est pas valide");
				return false;
			}
			commands.get(args[0]).onCommand(player, cmd, args);
			return true;
		}
		return false;
	}
	

	public void registerCommand(String cmd, SubCommand subcommand) { //ajoute une nouvelle sous commande dans la hashmap
		commands.put(cmd, subcommand);
	}
		
}


	