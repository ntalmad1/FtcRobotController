package org.firstinspires.ftc.teamcode.library.event.ping;

import org.firstinspires.ftc.teamcode.library.event.EventHandler;

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
