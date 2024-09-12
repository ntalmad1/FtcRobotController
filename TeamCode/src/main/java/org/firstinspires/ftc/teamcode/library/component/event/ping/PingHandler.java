package org.firstinspires.ftc.teamcode.library.component.event.ping;

import org.firstinspires.ftc.library.component.event.EventHandler;

/**
 *
 */
public interface PingHandler extends EventHandler
{
    /**
     *
     * @param event
     */
    void onPing (PingEvent event);
}
