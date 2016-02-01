package net.capricatown.chcombust.event;

import java.util.Map;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.CInt;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;

/**
 * Group for all events for this extention.
 */
class CHCombustEvents {

    /**
     * The extention entity_combust class.
     */
    @api
    public static class entity_combust extends AbstractEvent {
        @Override
        public String docs() {
            return "{}"
                + " Fires when an entity is on fire/combustion."
                + " {id | combuster_type | combuster | location | duration}"
                + " {}"
                + " {}";
        }

        @Override
        public final BindableEvent convert(final CArray arg0, final Target t) {
            throw new UnsupportedOperationException(
              "This is not supported at this time."
            );
        }

        @Override
        public final Driver driver() {
            return Driver.EXTENSION;
        }

       /**
         * This is where the @event values are set.
         *
         * @param event This is our event wrapper object.
         * @throws EventException When the event object is wrong.
         * @return Map<String, Construct> Data that will populate @event.
         */
        public Map<String, Construct> evaluate(final BindableEvent event)
               throws EventException {
            if (event instanceof CHCombustEvent) {
                Target t = Target.UNKNOWN;
                CHCombustEvent e = (CHCombustEvent) event;
                Map<String, Construct> ret = evaluate_helper(e);
                ret.put("combuster_type", new CString(e.getCombusterType(), t));
                if (e.getCombuster() != null) {
                    ret.put("combuster", new CString(e.getCombuster(), t));
                }
                ret.put("id", new CString(e.getEntityId(), t));
                ret.put("duration", new CInt(e.getDuration(), t));
                MCLocation loc = e.getLocation();
                CArray chArray = ObjectGenerator.GetGenerator().location(loc);
                ret.put("location", chArray);
                return ret;
            } else {
                throw new EventException("Cannot convert e to CHCombustEvent");
            }
        }

        @Override
        public final String getName() {
            return "entity_combust";
        }

        @Override
        public final boolean matches(final Map<String, Construct> prefilter,
                                     final BindableEvent event)
               throws PrefilterNonMatchException {
            if (event instanceof CHCombustEvent) {
                return true;
            }
            return false;
        }

        @Override
        public final boolean modifyEvent(final String key,
                                   final Construct value,
                                   final BindableEvent event) {
            return false;
        }

        @Override
        public final Version since() {
            return CHVersion.V3_3_1;
        }


    }

}
