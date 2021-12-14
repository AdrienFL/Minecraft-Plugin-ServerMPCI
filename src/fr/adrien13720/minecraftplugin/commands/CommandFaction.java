package fr.adrien13720.minecraftplugin.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class CommandFaction implements TabExecutor {
	
	public static ScoreboardManager manager = Bukkit.getScoreboardManager();
	public static Scoreboard board = manager.getNewScoreboard();
	public static Objective objective = board.registerNewObjective("Factions", "dummy", "Factions Title"); //Creation du scoreboard Factions   
	public static Set <Team> teams = board.getTeams();
	public static Set <String> teamnames = new HashSet<>();
	public static Set <Player> teamleaders = new HashSet<>();
	public static Set <String> teamleadersnames = new HashSet<>();
	public static HashMap<String, Integer> diamondcount = new HashMap<String, Integer>();
	public static HashMap<String, Integer> teamdiamondcount = new HashMap<String, Integer>();
	public static ArrayList<String> playerdiamondlist = new ArrayList<>();
	public static ArrayList<String> teamdiamondlist = new ArrayList<>();
	public static ArrayList<String> deletedteams = new ArrayList<>();
	
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
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String cmd2, String[] args){
		
		List<String> autoCompletes = new ArrayList<>();
		if(cmd.getName().equalsIgnoreCase("faction")) {
			

			
			if(args.length == 1) {
				
				autoCompletes.add("color");
				autoCompletes.add("create");
				autoCompletes.add("join");
				autoCompletes.add("leave");
				autoCompletes.add("list");
				autoCompletes.add("promote");
				autoCompletes.add("kick");
				return autoCompletes;
			
			}
			
			if(args.length == 2) {
				switch(args[0]) {
				case("create"):
					autoCompletes.add("nomdefaction");
					break;
				case("color"):
					autoCompletes.add("green");
					autoCompletes.add("yellow");
					autoCompletes.add("red");
					autoCompletes.add("gold");
					autoCompletes.add("gray");
					autoCompletes.add("aqua");
					autoCompletes.add("white");
					autoCompletes.add("blue");
					autoCompletes.add("black");
					autoCompletes.add("purple");
					autoCompletes.add("darkpurple");
					autoCompletes.add("darkgreen");
					autoCompletes.add("darkred");
					autoCompletes.add("darkgray");
					autoCompletes.add("darkaqua");
					autoCompletes.add("darkblue");
					break;
				case("join"):
					for(Team team : teams) {
						autoCompletes.add(team.getName());
					}
					break;
				case("promote"):
					for(Team team : teams) {
						if(team.hasEntry(sender.getName())) {
							Team sender_team = team;
							for(Player player : Bukkit.getOnlinePlayers()) {
								if(sender_team.hasEntry(player.getName())) {
									autoCompletes.add(player.getName());
								}
							}
						}
					}
					break;
				case("kick"):
					for(Team team : teams) {
						if(team.hasEntry(sender.getName())) {
							for(Player player : Bukkit.getOnlinePlayers()) {
								if(team.hasEntry(player.getName())) {
									autoCompletes.add(player.getName());
								}
							}
						}
					}
					break;
				default:
					break;				
				}
				return autoCompletes;
			}

		}
		return null;
		
	}
		
}


	