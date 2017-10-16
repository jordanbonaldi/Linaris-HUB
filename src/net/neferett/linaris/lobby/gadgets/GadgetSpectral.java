package net.neferett.linaris.lobby.gadgets;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.neferett.linaris.utils.tasksmanager.TaskManager;
import net.neferett.linaris.lobby.gadgets.spectral.ChestplateItem;
import net.neferett.linaris.lobby.gadgets.spectral.HeadItem;
import net.neferett.linaris.lobby.gadgets.spectral.ParticleRunnable;

public class GadgetSpectral extends GadgetItem {

	public static int			tickSpectreCouldown	= 400;
	public static int			tickSpectre			= 200;
	
	public GadgetSpectral() {
		super("Plasma Spectral",new ItemStack(Material.GHAST_TEAR),"GadgetItemSpectral",tickSpectreCouldown/20,"Vous transforme en spectre !");
	}

	private void startSpectre(final Player player) {
		TaskManager.scheduleSyncRepeatingTask("spectral-" + player.getName(), new ParticleRunnable(player), 0, 1);
		player.setAllowFlight(true);
		player.setFlying(true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, tickSpectre, 1));
		player.getInventory().setChestplate(new ChestplateItem().getStaticItem());
		player.getInventory().setHelmet(getHead(player));
		TaskManager.runTaskLater(new Runnable() {
			@Override
			public void run() {
				player.getInventory().setHelmet(null);
				player.getInventory().setChestplate(null);
				player.teleport(player.getWorld().getHighestBlockAt(player.getLocation()).getLocation().add(0, 1, 0));
				player.setFlying(false);
				player.setAllowFlight(false);
			}
		}, tickSpectre);
	}

	private ItemStack getHead(Player player) {
		ItemStack head = new HeadItem().getStaticItem();
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		head.setDurability((short) 3);
		meta.setOwner(player.getName());
		head.setItemMeta(meta);
		return head;
	}

	@Override
	public void onUse(Player player) {
		player.setVelocity(player.getVelocity().setY(2));
		TaskManager.runTaskLater(new Runnable() {
			@Override
			public void run() {
				startSpectre(player);
			}
		}, 10);
	}

	@Override
	public void onOption(Player player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inventoryClickEvent(Player player) {
		// TODO Auto-generated method stub
		
	}

}
