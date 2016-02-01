package net.capricatown.chcombust;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityCombustEvent;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.EventUtils;
import net.capricatown.chcombust.event.CHCombustEvent;

/**
 * This Listener is responsible for getting the event from Bukkit and
 * passing it on to CH.
 */
public class CHCombustListener implements Listener {

    /**
     * Register itself to the CH plugin.
     *
     * @param chp command helper plugin object
     */
    public CHCombustListener(final CommandHelperPlugin chp) {
        chp.registerEvents(this);
    }

    /**
     * Inregister itself from CH.
     */
    public final void unregister() {
        EntityCombustEvent.getHandlerList().unregister(this);
    }

    /**
     * This is called by Bukkit when an Entity starts to combust.
     *
     * @param event Bukkit event
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public final void onEntityCombust(final EntityCombustEvent event) {
        CHCombustEvent chevent = new CHCombustEvent(event);
        EventUtils.TriggerListener(Driver.EXTENSION, "entity_combust", chevent);
    }

}
