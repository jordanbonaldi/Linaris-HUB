package net.neferett.linaris.lobby.gadgets;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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

public class GadgetSnowCanon extends GadgetItem implements SpecialItemProjectileLauncheur {
	private static final String	CANON_FLAG		= "GadgetItemSnowCanonFlag";
	private static final int	duration 		= 60;
	
	private static Random 		random			= new Random();
	
	public GadgetSnowCanon() {
		super("§6Snow Canon",  new ItemStack(Material.SNOW_BALL),CANON_FLAG,5,"§7Une boule de neige", "§7pour transformer le hub");
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
		for(Vector v : VectorUtils.getVectorRange(5, 2)) {
			block = centerBlock.getRelative(v.getBlockX(), v.getBlockY(), v.getBlockZ());
			if(block.getType().equals(Material.WALL_SIGN)) {
				continue;//on va pas embÃªter les joueurs...
			} else if(block.getType().isSolid()) {
				BlockUtils.temporaryChangeBlock(block,random.nextBoolean()?Material.PACKED_ICE:Material.SNOW_BLOCK,(byte)0,duration);
			} else if(block.getType().equals(Material.WATER) || block.getType().equals(Material.STATIONARY_WATER)) {
				BlockUtils.temporaryChangeBlock(block,Material.ICE,(byte)0,duration);
			} else if(block.getType().equals(Material.AIR) && block.getRelative(BlockFace.DOWN).getType().isSolid()) {
				int i = random.nextInt(15);
				if(i<5) BlockUtils.temporaryChangeBlock(block,Material.SNOW,(byte)i,duration);
			} else if(random.nextInt(3)==0) {
				Particles.SNOW_SHOVEL.display(0.3F, 0.3F, 0.3F, 0, 1,block.getLocation(),new ArrayList<>(Bukkit.getOnlinePlayers()));
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

	}

}
