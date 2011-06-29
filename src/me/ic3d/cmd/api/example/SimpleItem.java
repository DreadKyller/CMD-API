package me.ic3d.cmd.api.example;

import me.ic3d.cmd.api.simple.CMDListener;
import me.ic3d.cmd.api.simple.SimpleCommandAPI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleItem extends JavaPlugin{
	
	SimpleCommandAPI api = new SimpleCommandAPI();
	
	@Override
	public void onDisable(){
	}

	@Override
	public void onEnable(){
		
		api.register(new APITest(), "$");
		
		getServer().getPluginManager().registerEvent(Event.Type.PLAYER_CHAT, api, Event.Priority.Monitor, this);
	}
	
	public class APITest extends CMDListener{

		@Override
		public void onCustomCommand(Player p, String usage, String cmd, String[] content){
			
			if(!p.isOp()){
				return;
			}
			
			if(usage.equalsIgnoreCase("$")&&content.length<3){
				Material mat = Material.getMaterial(cmd.toUpperCase());
				if(mat==null){
					try{
						mat = Material.getMaterial(Integer.parseInt(cmd));
					}catch(NumberFormatException e){
						p.sendMessage("No Item Named "+cmd);
						return;
					}
					if(mat==null){
						p.sendMessage("No Item With ID="+cmd);
						return;
					}
				}
				int amount = 1;
				if(content.length>=1){
					try{
						amount = Integer.parseInt(content[0]);
					}catch(Exception e){
					}
				}
				Player pi;
				if(content.length==2){
					pi = p.getServer().matchPlayer(content[1]).get(0);
				}else{
					pi = p;
				}
				ItemStack item = new ItemStack(mat, amount);
				pi.getInventory().addItem(item);
			}
		}
	}
	
}
