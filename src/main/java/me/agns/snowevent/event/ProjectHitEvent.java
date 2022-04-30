package me.agns.snowevent.event;

import me.agns.snowevent.SnowEvent;
import me.agns.snowevent.Team;
import org.bukkit.ChatColor;
import org.bukkit.EntityEffect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.ArrayList;
import java.util.HashMap;

public class ProjectHitEvent implements Listener {
    private HashMap<Player, Player> shooters = new HashMap<>();


    @EventHandler
    public void onHitSnowBall(ProjectileHitEvent event){
        if (event.getEntity().getShooter() instanceof Player && event.getHitEntity() instanceof Player && event.getEntity() instanceof Snowball){
            Player shooter = (Player) event.getEntity().getShooter();
            Player hit = (Player) event.getEntity().getShooter();
            for (Team team : SnowEvent.getTeams()){
                ArrayList<Player> playerTeam = team.getPlayers();
                if (playerTeam.contains(shooter) && !(playerTeam.contains(hit))){
                    if (hit.getHealth() <= 3.5) {
                        hit.setHealth(0);
                        shooters.put(hit, shooter);
                        hit.playEffect(EntityEffect.HURT);
                        shooter.sendMessage(ChatColor.GOLD + "You killed " + ChatColor.ITALIC + hit.getName());
                    } else {
                        hit.playSound(hit.getLocation(), Sound.ENTITY_PLAYER_HURT, 100, 100);
                        hit.playEffect(EntityEffect.HURT);
                        hit.setHealth(hit.getHealth() - 3.5);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onDeathPlayer(PlayerDeathEvent event){
        if (shooters.containsKey(event.getEntity())){
            event.setDeathMessage(ChatColor.GOLD + "You was killed by "+ ChatColor.ITALIC + shooters.get(event.getEntity()).getName());
            shooters.remove(event.getEntity());
        }
    }
}
