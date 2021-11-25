package fr.adrien13720.minecraftplugin;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Team;

import fr.adrien13720.minecraftplugin.commands.ColorSubCommand;
import fr.adrien13720.minecraftplugin.commands.CommandFaction;
import fr.adrien13720.minecraftplugin.commands.CommandTest;
import fr.adrien13720.minecraftplugin.commands.CommandTp;
import fr.adrien13720.minecraftplugin.commands.CreateSubcommand;
import fr.adrien13720.minecraftplugin.commands.JoinSubCommand;
import fr.adrien13720.minecraftplugin.commands.LeaveSubCommand;
import fr.adrien13720.minecraftplugin.commands.ListSubCommand;
import fr.adrien13720.minecraftplugin.commands.PromoteSubCommand;

public class Main extends JavaPlugin {
	private static Main instance;
	
	public static Main getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		System.out.println("Le plugin démarre");
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
		getCommand("spawn").setExecutor(new CommandTp());
		CommandFaction faction = new CommandFaction();
		getCommand("faction").setExecutor(faction);
		faction.registerCommand("create", new CreateSubcommand());
		faction.registerCommand("list", new ListSubCommand());
		faction.registerCommand("color", new ColorSubCommand());
		faction.registerCommand("join", new JoinSubCommand());
		faction.registerCommand("leave", new LeaveSubCommand());
		faction.registerCommand("promote", new PromoteSubCommand());
		getServer().getPluginManager().registerEvents(new MinecraftPluginlisteners(), this);
		CommandFaction.objective.setDisplaySlot(DisplaySlot.PLAYER_LIST); //Scoreboard au niveau du tab
		
		

	}
	
	@Override
	public void onDisable() {
		instance = null;
		System.out.println("Le plugin s'arrete");


	}


	
}

