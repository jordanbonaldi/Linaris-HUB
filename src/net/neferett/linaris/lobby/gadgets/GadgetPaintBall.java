package net.neferett.linaris.lobby.gadgets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import net.neferett.linaris.specialitems.SpecialItemProjectileLauncheur;
import net.neferett.linaris.utils.BlockUtils;
import net.neferett.linaris.utils.Particles;
import net.neferett.linaris.utils.ProjectilesUtils;
import net.neferett.linaris.utils.VectorUtils;

public class GadgetPaintBall extends GadgetItem implements SpecialItemProjectileLauncheur {
	private static final String	CANON_FLAG		= "GadgetItemPaintBallFlag";
	private static final int	duration 		= 60;
	
	private static Random 		random			= new Random();
	
	private byte color;
	private HashMap<Byte,Material> colors;
	private Iterator<Byte> colorsi;
	
	public GadgetPaintBall() {
		super("§aP§ba§ci§dn§et §aB§ba§cl§dl §e!",  new ItemStack(Material.WOOL,1,(short) 14),CANON_FLAG,1,"§6Redécouvrez le hub");
		colors = new HashMap<>();
		colors.put((byte) 14, Material.NETHERRACK);
		colors.put((byte) 1, Material.PUMPKIN);
		colors.put((byte) 13, Material.CACTUS);
		colors.put((byte) 5, Material.SLIME_BLOCK);
		colorsi = colors.keySet().iterator();
		color = 14;
		registerItem(this);
	}

	@Override
	public void projectileHitBlock(Player player, ProjectileHitEvent event) {
		effect(event.getEntity().getLocation());
	}


	@Override
	public void projectileDamageEntity(Player player, Projectile projectile, EntityDamageByEntityEvent event) {
		effect(projectile.getLocation());
	}

	private void effect(Location location) {
		
		Block centerBlock = location.getBlock();
		Block block;
		location.getWorld().playSound(location, Sound.STEP_SNOW, 1, 0.7F);
		
		Material addon = colors.get(color);
		
		for(Vector v : VectorUtils.getVectorRange(5, 2)) {
			block = centerBlock.getRelative(v.getBlockX(), v.getBlockY(), v.getBlockZ());
			if(block.getType().equals(Material.WALL_SIGN)) {
				continue;
			} else if(block.getType().isSolid()) {
				if (random.nextBoolean())
					BlockUtils.temporaryChangeBlock(block,addon,(byte)0,duration);
				else
					BlockUtils.temporaryChangeBlock(block,Material.WOOL,color,duration);
			} else if(block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER)) {
				if (random.nextBoolean())
					BlockUtils.temporaryChangeBlock(block,addon,(byte)0,duration);
				else
					BlockUtils.temporaryChangeBlock(block,Material.WOOL,color,duration);
			} else if(random.nextInt(3)==0) {
				Particles.SPELL.display(0.3F, 0.3F, 0.3F, 0, 1,block.getLocation(),new ArrayList<>(Bukkit.getOnlinePlayers()));
			}
		}
	}

	@Override
	public void onUse(Player player) {
		Location spawn = player.getEyeLocation().add(player.getLocation().getDirection());
		ProjectilesUtils.launchProjectile(getId(), player, spawn, Snowball.class);
	}



	@Override
	public void onOption(Player player) {
		ItemStack item = getFirstInInventory(player.getInventory());
		
		byte nextColor = 0;
		
		if (colorsi.hasNext())
			nextColor = colorsi.next();
		else {
			colorsi = colors.keySet().iterator();
			nextColor = colorsi.next();
		}
		
		color = nextColor;
		item.setDurability(color);
		
	}

}
