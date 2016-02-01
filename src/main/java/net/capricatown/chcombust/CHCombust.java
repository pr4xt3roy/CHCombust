package net.capricatown.chcombust;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;

/**
 * Main extention class used to register listener.
 */
@MSExtension("CHCombust")
public class CHCombust extends AbstractExtension {

    /**
     * Listener for events.
     */
    private static CHCombustListener dl;

    /**
     * Version of this extension.
     *
     * @return Version The version of this extension.
     */
    public final Version getVersion() {
        return new SimpleVersion(0, 0, 1);
    }

    @Override
    public final void onStartup() {
        CommandHelperPlugin chp = CommandHelperPlugin.self;
        dl = new CHCombustListener(chp);
        System.out.println("CHCombust " + getVersion() + " loaded.");
    }

    @Override
    public final void onShutdown() {
        dl.unregister();
        System.out.println("CHCombust " + getVersion() + " unloaded.");
    }
}
