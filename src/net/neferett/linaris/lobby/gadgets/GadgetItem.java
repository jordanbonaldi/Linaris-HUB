package net.neferett.linaris.lobby.gadgets;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import net.neferett.linaris.metadatas.Flags;
import net.neferett.linaris.specialitems.SpecialItem;
import net.neferett.linaris.specialitems.SpecialItemSuperCooldownIndicatorRunnable;
import net.neferett.linaris.utils.PlayerUtils;

public abstract class GadgetItem extends SpecialItem {

	String flag;
	int cooldown;
	SpecialItemSuperCooldownIndicatorRunnable task;
	
	public GadgetItem(String name, ItemStack itemStack, String flag,int cooldown, String... lore) {
		super(name, itemStack, lore);
		this.flag = flag;
		this.cooldown = cooldown;
		setLeftClickable(true);
		setRightClickable(true);
		registerItem(this);
	}
	
	public boolean testCooldown(Player player) {
		if (Flags.hasFlag(player, flag)) {
			player.playSound(player.getEyeLocation(), Sound.VILLAGER_NO, 1, 1);
			PlayerUtils.sendActionMessage(player, "§c§lEn cours de recharge !");
			return true;
		}
		return false;
	}
	
	public void setCooldown(Player player) {
		Flags.setTemporaryFlag(player, flag, cooldown*20);
		task = new SpecialItemSuperCooldownIndicatorRunnable(player, this, cooldown);
		task.start();
	}

	@Override
	protected void rightClickEvent(Player player) {
		if (testCooldown(player)) {
			return;
		}
		setCooldown(player);
		onUse(player);
	}
	
	@Override
	protected void leftClickEvent(Player player) {
		onOption(player);
	}

	public abstract void onUse(Player player);
	public abstract void onOption(Player player);

	@Override
	public void inventoryClickEvent(Player player) {
		
	}
	
}
