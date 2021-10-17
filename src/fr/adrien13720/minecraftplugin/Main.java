package fr.adrien13720.minecraftplugin;


import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import fr.adrien13720.minecraftplugin.commands.CommandFaction;
import fr.adrien13720.minecraftplugin.commands.CommandTest;
import fr.adrien13720.minecraftplugin.commands.CommandTp;
import fr.adrien13720.minecraftplugin.commands.CreateSubcommand;
import fr.adrien13720.minecraftplugin.commands.ListSubCommand;

public class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		System.out.println("Le plugin démarre");
		getCommand("test").setExecutor(new CommandTest());
		getCommand("alert").setExecutor(new CommandTest());
		getCommand("spawn").setExecutor(new CommandTp());
		CommandFaction faction = new CommandFaction();
		getCommand("faction").setExecutor(faction);
		faction.registerCommand("create", new CreateSubcommand());
		faction.registerCommand("list", new ListSubCommand());


		getServer().getPluginManager().registerEvents(new MinecraftPluginlisteners(), this);
		CommandFaction.objective.setDisplaySlot(DisplaySlot.PLAYER_LIST); //Scoreboard au niveau du tab

	}
	
	@Override
	public void onDisable() {
		System.out.println("Le plugin s'arrete");


	}


}

