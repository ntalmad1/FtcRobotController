package org.firstinspires.ftc.teamcode.library.event;

import org.firstinspires.ftc.teamcode.library.IsaacBot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class HandlerManager {

    /**
     *
     */
    private Map<EventType, List<EventHandler>> handlerMap;

    /**
     *
     */
    protected IsaacBot robot;

    public HandlerManager() {
        this.handlerMap = new HashMap<EventType, List<EventHandler>>();
        this.robot = IsaacBot.getInstance();
    }

    /**
     *
     * @param eventType
     * @param handler
     * @return
     * @param <T>
     */
    public <T extends EventHandler> HandlerRegistration addHandler (EventType<T> eventType, T handler)
    {
        if (!this.handlerMap.containsKey(eventType))
        {
            this.handlerMap.put((EventType)eventType, (List<EventHandler>) new ArrayList<T>());
        }

        this.handlerMap.get(eventType).add(handler);

        return new HandlerRegistration(handler);
    }

    /**
     *
     * @param event
     * @param <T>
     */
    public <T extends EventHandler> void fireEvent (Event<T> event) {

        if (!this.handlerMap.containsKey(event.getType())) {
            return;
        }

        List<T> handlers = (List<T>)this.handlerMap.get(event.getType());

        for (T handler : handlers) {
            event.handle(handler);
        }
   }

   public Map<EventType, List<EventHandler>> getHandlerMap() {
        return this.handlerMap;
   }

}
