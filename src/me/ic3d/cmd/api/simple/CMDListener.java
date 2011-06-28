package me.ic3d.cmd.api.simple;

import org.bukkit.entity.Player;

public abstract class CMDListener{
	public CMDListener(){
	}
	public abstract void onCustomCommand(Player p,String usage, String cmd, String[] content);
}
