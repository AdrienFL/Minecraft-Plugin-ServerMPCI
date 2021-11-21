package fr.adrien13720.minecraftplugin;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import fr.adrien13720.minecraftplugin.commands.CommandFaction;

public class MinecraftPluginlisteners implements Listener {
	
	
	private int taskID;

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		player.sendTitle("§6Bienvenue", "§6sur le server §b" + player.getName(), 30, 50, 30); //Message d'entrée (au milieu de l'écran)
		player.setScoreboard(CommandFaction.board);
		for (Player playertest : CommandFaction.teamleaders) {
			CommandFaction.teamleadersnames.add(playertest.getName());
		}
		for(Team team : CommandFaction.teams) {
			if(team.hasEntry(player.getName())) {
				player.setDisplayName(team.getColor() + "[" + team.getName() + "] " + player.getName() + ChatColor.WHITE);
				 
			}
			if(team.hasEntry(player.getName()) && CommandFaction.teamleadersnames.contains(player.getName())) {
				player.setDisplayName(team.getColor() + "[" + team.getName() + "] " + ChatColor.BLACK + "[Chef] " + team.getColor() + player.getName()  + ChatColor.WHITE);
				player.setPlayerListName(team.getColor() + "[" + team.getName() + "] " + ChatColor.BLACK + "[Chef] " + team.getColor() + player.getName());
			}

		}
		createBoard(player);
		start(event.getPlayer());
		//Change le message de join dans le chat
		switch(player.getName()) {
		 
			case "Adrien13720":
				event.setJoinMessage("§6Un énorme bg : §b" + player.getName() + "§2 est arrivé sur le server.");
				break;
			
			case "MagicZemmour":
				event.setJoinMessage("§4Le Z est dans la place : " + player.getName());
				break;
			// Autre cas à rajouter à l'infini
			
			default:
				event.setJoinMessage("Bienvenue à toi " + player.getName());
			
		
		}
		

		
		
		
		
		
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		FirstBoard board = new FirstBoard(event.getPlayer().getUniqueId());
		if (board.hasID()) {
			board.stop();
		}
	}
	

	
	public void start(Player player) {
		long a = 0;
		long b = 10;


		
		
		Runnable Run = new Runnable() {
			public int count = 0;
			FirstBoard board = new FirstBoard(player.getUniqueId()); 
			
			@Override
			public void run() {
				if (!board.hasID()) {
					board.setID(taskID);
				}
				if (count == 7) {
					count = 0;
				}
				switch(count) {
				case 0:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§5S§de§1r§2v§ee§6u§cr §5M§dP§1C§2I");
					break;
				case 1:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§dS§1e§2r§ev§6e§cu§5r §dM§1P§2C§eI");
					break;
				case 2:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§1S§2e§er§6v§ce§5u§dr §1M§2P§eC§6I");
					break;
				case 3:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§2S§ee§6r§cv§5e§du§1r §2M§eP§6C§cI");
					break;	
				case 4:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§eS§6e§cr§5v§de§1u§2r §eM§6P§cC§5I");
					break;					
				case 5:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§6S§ce§5r§dv§1e§2u§er §6M§cP§5C§dI");
					break;
				case 6:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§cS§5e§dr§1v§2e§eu§6r §cM§5P§dC§1I");
					createBoard(player);
					break;
				}
				
				count++;
			}
			
		};

		
		taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), Run, a, b);
		
	}
	
	
	public void createBoard(Player player) {
		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard sideboard = manager.getNewScoreboard();
		Objective obj = sideboard.registerNewObjective("Factions2", "dummy", "§5S§de§1r§2v§ee§6u§cr §5M§dP§1C§2I");
		obj.setDisplaySlot(DisplaySlot.SIDEBAR);
		Score score = obj.getScore(ChatColor.BLUE + "=-=-=-=-=-=-=-=-");
		score.setScore(4);
		Score score2 = obj.getScore(ChatColor.AQUA + "Online Players : "+ ChatColor.DARK_AQUA + Bukkit.getOnlinePlayers().size());
		score2.setScore(3);
		Score score3 = obj.getScore(ChatColor.AQUA + "Kills : " + ChatColor.DARK_AQUA + player.getStatistic(Statistic.PLAYER_KILLS));
		score3.setScore(2);
		Score score4 = obj.getScore(ChatColor.BLUE + "-=-=-=-=-=-=-=-=");
		score4.setScore(1);
		Score score5 = obj.getScore(ChatColor.GOLD + "ip : mpci.apexmc.co");
		score5.setScore(0);
		player.setScoreboard(sideboard);
	}
	
	

	@EventHandler
	public void onInteract(PlayerInteractEvent event) { // Fonction à modifier
		Player player = event.getPlayer();
		Action action = event.getAction();
		ItemStack it = event.getItem();
		if(it == null) {
			return;
		}
		if(it.getType() == Material.COMPASS && it.getItemMeta().hasDisplayName()) {
			Inventory inv = Bukkit.createInventory(null, 18, "");
			player.openInventory(inv);
			
		}
		
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) { //gere les messages dans le chat
		Player player = event.getPlayer();
		String message = event.getMessage();
		boolean playerinteam = false;
		for (Team team : CommandFaction.teams) {
			if (team.hasEntry(player.getName())) {
				playerinteam = true;
			}
		}
		
		if (message.indexOf("!") == 0) {
			if (playerinteam) {
				event.getRecipients().clear();
				for (Team team : CommandFaction.teams) {
					if (team.hasEntry(player.getName())) {
						Team playerteam = team;
						for (Player playermessaged : Bukkit.getOnlinePlayers()) {
							if (playerteam.hasEntry(playermessaged.getName())) {
								event.getRecipients().add(playermessaged);
							}	
						}
						event.setMessage(playerteam.getColor()+ "(Team) " + event.getMessage().substring(1));
					}
				}	
					
			}
			else {
			player.sendMessage("§cVous ne pouvez pas envoyer de message dans le chat d'équipe si vous n'etes pas dans une equipe");
			return;
			}
		}
		else {
			return;
		}
		
		
		
	}
	
	
}
