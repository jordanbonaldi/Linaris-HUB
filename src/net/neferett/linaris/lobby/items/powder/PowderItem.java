package net.neferett.linaris.lobby.items.powder;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.BukkitAPI;
import net.neferett.linaris.api.Games;
import net.neferett.linaris.api.SettingsManager;
import net.neferett.linaris.metadatas.Flags;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.specialitems.SpecialItemSuperCooldownIndicatorRunnable;
import net.neferett.linaris.utils.PlayerUtils;

public class PowderItem extends SpecialItem  {

	public static int			id			= -1;
	public static String			flag			= "POWDER";

	public static SpecialItem get() {
		if (id == -1)id = registerItem(new PowderItem());
		return get(id);
	}
	
	@SuppressWarnings("deprecation")
	public PowderItem() {
		super("§6Poudre de Perlimpinpin", new ItemStack(289),"§7Un concentré d'objets !");
		setLeftClickable(true);
		setRightClickable(true);
	}

	@Override
	protected void rightClickEvent(Player player) {
		onUse(player);
	}
	
	@Override
	protected void leftClickEvent(Player player) {
		onUse(player);
	}
	
	public void onUse(Player player) {
		if (testCooldown(player)) return;
		BukkitAPI.get().getTasksManager().addTask(() -> {
			
			boolean enable = SettingsManager.isEnabled(player.getName(), Games.LOBBY,"powder" , false);
			
			SettingsManager.setSetting(player.getName(), Games.LOBBY, "powder" , String.valueOf(!enable));
			
			ItemStack item = getFirstInInventory(player.getInventory());
			
			SpecialItem.rename(item, getName() +  " §7(" + (enable ? "§aActivé" : "§cDésactivé") + "§7)");
			
			if (enable) {
				
				
				
			} else {
				
				
				
			}
			
			setCooldown(player, !enable);
			
		});
	}
	
	public boolean testCooldown(Player player) {
		if (Flags.hasFlag(player, flag)) {
			player.playSound(player.getEyeLocation(), Sound.CLICK, 1, 1);
			PlayerUtils.sendActionMessage(player, "§c§lEn cours de recharge !");
			return true;
		}
		return false;
	}
	
	public void setCooldown(Player player,boolean enable) {
		Flags.setTemporaryFlag(player, flag, 5*20);
		new SpecialItemSuperCooldownIndicatorRunnable(player, this, 5," §7(" + (enable ? "§aActivé" : "§cDésactivé") + "§7) (", ChatColor.AQUA, "§7)",null,
		() -> {
			ItemStack item = getFirstInInventory(player.getInventory());
			
			SpecialItem.rename(item, getName() +  " §7(" + (enable ? "§aActivé" : "§cDésactivé") + "§7)");
			
		}).start();
				
	}
	
	@Override
	protected void dropEvent(PlayerDropItemEvent e) {
		e.getItemDrop().remove();
	}

	@Override
	public void inventoryClickEvent(Player player) {}
	
}
