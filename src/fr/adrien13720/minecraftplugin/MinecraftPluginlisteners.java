package fr.adrien13720.minecraftplugin;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.TimerTask;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Statistic;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.util.BoundingBox;

import fr.adrien13720.minecraftplugin.commands.CommandFaction;
import net.minecraft.core.IRegistryCustom.Dimension;
import net.minecraft.world.level.World;
import net.minecraft.world.level.dimension.WorldDimension;

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
	        event.setJoinMessage("§bLe programmeur fou, " + ChatColor.GOLD +player.getName() + "§b vient pour hacker vos diamants!");
	        break;
	            
	    case "MagicZemmour":
	        event.setJoinMessage("§bMigrants à couvert! " + player.getName() + "§b arrive pour vous sniper!");
	        break;
	                
	    case "nitneuq13":
	        event.setJoinMessage("§aMairsi dakeuir " + player.getName() + "§a !!");
	        break;
	            
	    case "kribou29":
	        event.setJoinMessage("§5Alerte! " + player.getName() + "§5aka le Deuhman arrive pour deuh le serveur!");
	        break;
	            
	    case "Spatabaz":
	        event.setJoinMessage("§4Les §efilles... §cPréparez §avooosss §banus! §aCar " + player.getName() + " §dvient d'intégrer le serveur!");
	        break;
	                
	    case "TraffyCrom":
	        event.setJoinMessage("§6Putain! " + player.getName() + "§6 a encore fait tomber un truc dans le serveur!");
	        break;
	                
	    case "Roctalite":
	        event.setJoinMessage("§eZioouup! " + player.getName() + "§e vient de se glisser dans le serveur!");
	        break;
	            
	    default:
	        event.setJoinMessage("Bienvenue à toi " + player.getName());
	            
	        
	}
		
		if(!player.getInventory().contains(Material.COMPASS)) {
			ItemStack menucompass = new ItemStack(Material.COMPASS, 1);
			ItemMeta menucompassmeta = menucompass.getItemMeta();
			menucompassmeta.setDisplayName("Compas Menu");
			Inventory inventory = player.getInventory();
			menucompass.setItemMeta(menucompassmeta);
			inventory.addItem(menucompass);
			player.updateInventory();
		}
		System.out.println(CommandFaction.diamondcount.get(player));
		if(CommandFaction.diamondcount.get(player) == null) {
			CommandFaction.diamondcount.put(player, 0);
			CommandFaction.playerdiamondlist.add(player);
		}
		System.out.println(CommandFaction.diamondcount);
		updateplayerdiamondscoreboard();
		updateteamdiamondscoreboard();
		
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		FirstBoard board = new FirstBoard(event.getPlayer().getUniqueId());
		
		if (board.hasID()) {
			board.stop();
		}
	}
	

	
	public void start(Player player) { //scoreboard dynamique 
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
				if(player.getScoreboard().getObjective(DisplaySlot.SIDEBAR) == null) {
					return;
				}
				switch(count) {
				case 0:
					player.getScoreboard().getObjective(DisplaySlot.SIDEBAR).setDisplayName("§5S§de§1r§2v§ee§6u§cr §5M§dP§1C§2I");
					createBoard(player);
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
			Inventory inv = Bukkit.createInventory(null, 27, "§6Menu");
			player.openInventory(inv);
			ItemStack diamondscore = new ItemStack(Material.DIAMOND, 1);
			ItemMeta diamondscoreM = diamondscore.getItemMeta();
			diamondscoreM.setDisplayName("§bDiamants --> Points");
			diamondscoreM.setLore(Arrays.asList("Transforme tous vos diamants en points", "§cLes diamants transformés ", "§cne pourront pas être récupérés"));
			diamondscore.setItemMeta(diamondscoreM);
			inv.setItem(10, diamondscore);
			player.updateInventory();
		}
		
	}

	@EventHandler
	public void onClick(InventoryClickEvent event) {
		Inventory inv = event.getInventory();
		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();

		if(current == null) return;
		
		if(event.getView().getTitle().equalsIgnoreCase("§6Menu")) {
			if(current.getType() == Material.DIAMOND) {
				Inventory validinv = Bukkit.createInventory(null, 27, "§6Validation");
				player.openInventory(validinv);
				ItemStack validclay = new ItemStack(Material.GREEN_TERRACOTTA, 1);
				ItemStack nonvalidclay = new ItemStack(Material.RED_TERRACOTTA, 1);
				ItemMeta validclayM = validclay.getItemMeta();
				ItemMeta nonvalidclayM = nonvalidclay.getItemMeta();
				validclayM.setDisplayName("§aValider le transfert");
				nonvalidclayM.setDisplayName("§cArreter le transfert");
				validclay.setItemMeta(validclayM);
				nonvalidclay.setItemMeta(nonvalidclayM);
				validinv.setItem(12, validclay);
				validinv.setItem(14, nonvalidclay);
				player.updateInventory();
				return;
				
			}
		}
		if(event.getView().getTitle().equalsIgnoreCase("§6Validation")) {
			if(current.getType() == Material.GREEN_TERRACOTTA) {
				int diamondnumber = 0;
				for(ItemStack it : player.getInventory().getContents()) {
					if(it != null) {
						if(it.getType() == Material.DIAMOND && !it.getItemMeta().hasDisplayName()) {
							
						diamondnumber += it.getAmount();
						player.getInventory().remove(it);
						}
					}
				}
				CommandFaction.diamondcount.put(player, CommandFaction.diamondcount.get(player) + diamondnumber);
				for(Team team : CommandFaction.teams) {
					if(team.hasEntry(player.getName())) {
						CommandFaction.teamdiamondcount.put(team, CommandFaction.teamdiamondcount.get(team) + diamondnumber);
					}
				}
				player.closeInventory();
				updateplayerdiamondscoreboard();
				System.out.println("bruh");
				updateteamdiamondscoreboard();
				return;
			}
			if(current.getType() == Material.RED_TERRACOTTA) {
				player.closeInventory();
				return;
			}
		}
		return;
	}
	
	public void sortplayerdiamondlist(){
		
		for(int i = 1; i <= CommandFaction.playerdiamondlist.size() - 1; i++) {
			Player x = CommandFaction.playerdiamondlist.get(i);
			int j = i;
			while(j > 0 && CommandFaction.diamondcount.get(CommandFaction.playerdiamondlist.get(j - 1)) < CommandFaction.diamondcount.get(x)) {
				CommandFaction.playerdiamondlist.remove(j);
				CommandFaction.playerdiamondlist.add(j, CommandFaction.playerdiamondlist.get(j-1));
				j = j - 1;
			}
			CommandFaction.playerdiamondlist.remove(j);
			CommandFaction.playerdiamondlist.add(j, x);
			
		}
		System.out.println(CommandFaction.playerdiamondlist);
		return;
		

	}
	
	public void sortteamdiamondlist() {

		for(int i = 1; i <= CommandFaction.teamdiamondlist.size() - 1; i++) {
			Team x = CommandFaction.teamdiamondlist.get(i);
			int j = i;
			while(j > 0 && CommandFaction.teamdiamondcount.get(CommandFaction.teamdiamondlist.get(j - 1)) <= CommandFaction.teamdiamondcount.get(x)) {
				CommandFaction.playerdiamondlist.remove(j);
				CommandFaction.teamdiamondlist.add(j, CommandFaction.teamdiamondlist.get(j-1));
				j = j - 1;
			}
			CommandFaction.playerdiamondlist.remove(j);
			CommandFaction.teamdiamondlist.add(j, x);
			
		}
		return;
	}
	
	
	public void updateplayerdiamondscoreboard() {
		sortplayerdiamondlist();
		BoundingBox box = new BoundingBox(-4, 60, -4, 4, 90, 4);
		Collection <Entity> entitiesList = Bukkit.getWorld("MainWorld").getNearbyEntities(box);
		for(Entity entity : entitiesList) {
			System.out.println("gg a tpous");
			entity.remove();
		}
		ArmorStand hologram = (ArmorStand) Bukkit.getWorld("MainWorld").spawnEntity(new Location(Bukkit.getWorld("MainWorld") , 0, 80, 0) , EntityType.ARMOR_STAND);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(ChatColor.RED + "Classement Joueurs/Diamants");
        //Second line
        for(int i = 0; i <= 9; i++) {
        	if(i < CommandFaction.diamondcount.size()) {
            	ChatColor color = ChatColor.WHITE;
        		Player player = CommandFaction.playerdiamondlist.get(i);
        		for(Team team : CommandFaction.teams) {
        			if(team.hasEntry(player.getName())) {
        				color = team.getColor();
        			}  			
        		}
        		ArmorStand hologram2 = (ArmorStand) Bukkit.getWorld("MainWorld").spawnEntity(new Location(Bukkit.getWorld("MainWorld") , 0, 79.6 - 0.4*i, 0) , EntityType.ARMOR_STAND);
        		hologram2.setVisible(false);
        		hologram2.setGravity(false);
        		hologram2.setCustomNameVisible(true);
        		hologram2.setCustomName(ChatColor.GOLD + Integer.toString(i + 1) +" : "+ color + player.getName() + "  " + ChatColor.GOLD + CommandFaction.diamondcount.get(player));

        	
        	}
        }
	}
	
	public void updateteamdiamondscoreboard() {
		sortteamdiamondlist();
		BoundingBox box = new BoundingBox(14, 60, 14, 8, 90, 8);
		Collection <Entity> entitiesList = Bukkit.getWorld("MainWorld").getNearbyEntities(box);
		for(Entity entity : entitiesList) {
			entity.remove();
		}
		ArmorStand hologram = (ArmorStand) Bukkit.getWorld("MainWorld").spawnEntity(new Location(Bukkit.getWorld("MainWorld") , 11, 80, 11) , EntityType.ARMOR_STAND);
        hologram.setVisible(false);
        hologram.setGravity(false);
        hologram.setCustomNameVisible(true);
        hologram.setCustomName(ChatColor.RED + "Classement Faction/Diamants");
        //Second line
        for(int i = 0; i <= 9; i++) {
        	if(i < CommandFaction.teamdiamondcount.size()) {
            	ChatColor color = ChatColor.WHITE;
        		Team team = CommandFaction.teamdiamondlist.get(i);
        		
        		ArmorStand hologram2 = (ArmorStand) Bukkit.getWorld("MainWorld").spawnEntity(new Location(Bukkit.getWorld("MainWorld") , 11, 79.6 - 0.4*i, 11) , EntityType.ARMOR_STAND);
        		hologram2.setVisible(false);
        		hologram2.setGravity(false);
        		hologram2.setCustomNameVisible(true);
        		hologram2.setCustomName(ChatColor.GOLD + Integer.toString(i + 1) +" : "+ color + team.getName() + "  "+ ChatColor.GOLD +CommandFaction.teamdiamondcount.get(team));

        	
        	}
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
