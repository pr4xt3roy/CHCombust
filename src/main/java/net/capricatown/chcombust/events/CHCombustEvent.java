package net.capricatown.chcombust.event;

import org.bukkit.block.Block;
import com.laytonsmith.core.events.BindableEvent;
import org.bukkit.event.Cancellable;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityCombustEvent;
import org.bukkit.event.entity.EntityCombustByEntityEvent;
import org.bukkit.event.entity.EntityCombustByBlockEvent;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;

/**
 * Wrapper around the bukkit Event.
 */
public class CHCombustEvent implements BindableEvent, Cancellable {

    /**
     * Bukkit Event object.
     */
    private EntityCombustEvent e;

    /**
     * Constructor for the Wrapper of the bukkit Event.
     *
     * @param event Bukkit event.
     */
    public CHCombustEvent(final EntityCombustEvent event) {
        this.e = event;
    }

    @Override
    public final Object _GetObject() {
        return this.e;
    }

    /**
     * Indicate if the event needs to be cancelled.
     */
    private boolean cancelled = false;

    @Override
    public final boolean isCancelled() {
        return cancelled;
    }

    @Override
    public final void setCancelled(final boolean arg0) {
        cancelled = arg0;
    }

    /**
     * Returns the Combusting Entity bukkit object.
     *
     * @return Entity The Bukkit Entity Object.
     */
    private Entity getEntity() {
        return e.getEntity();
    }

    /**
     * The UUID of the combusting entity.
     *
     * @return String 36 character formatted UUID.
     */
    public final String getEntityId() {
        return getEntity().getUniqueId().toString();
    }

    /**
     * Duration of the combustion.
     *
     * @return int number of seconds.
     */
    public final int getDuration() {
        return e.getDuration();
    }

    /**
     * Returns the Combuster, what caused thee combustion.
     *
     * @return String Either the entity id, the block id:data or null.
     */
    public final String getCombuster() {
        if (this.e instanceof EntityCombustByBlockEvent) {
            EntityCombustByBlockEvent e2 = (EntityCombustByBlockEvent) this.e;
            Block block = e2.getCombuster();
            if (block != null) {
                return block.getTypeId() + ":" + block.getData();
            }
        } else if (this.e instanceof EntityCombustByEntityEvent) {
            EntityCombustByEntityEvent e2 = (EntityCombustByEntityEvent) this.e;
            Entity entity = e2.getCombuster();
            if (entity != null) {
                return entity.getUniqueId().toString();
            }
        }
        return null;
    }

    /**
     * Get the type of combuster used (block, entity or other).
     * Other could mean the sun.
     *
     * @return String block, entity or other
     */
    public final String getCombusterType() {
        if (this.e instanceof EntityCombustByBlockEvent) {
            return "block";
        } else if (this.e instanceof EntityCombustByEntityEvent) {
            return "entity";
        } else {
            return "other";
        }

    }

    /**
     * Location of the combusting entity.
     *
     * @return MCLocation Coordinates.
     */
    public final MCLocation getLocation() {
        return new BukkitMCLocation(getEntity().getLocation());
    }

}
