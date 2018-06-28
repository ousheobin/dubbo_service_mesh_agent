package cn.shaobin.agent.reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Selector {
    private BlockingQueue<Event> eventQueue = new LinkedBlockingQueue<Event>();
    private Object lock = new Object();

    List<Event> select() {
        if (eventQueue.isEmpty()) {
            synchronized (lock) {
                if (eventQueue.isEmpty()) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
            }
        }
        List<Event> events = new ArrayList<Event>();
        eventQueue.drainTo(events);
        return events;
    }

    public void addEvent(Event e) {
        boolean success = eventQueue.offer(e);
        if (success) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }

}
