package cn.shaobin.agent.reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Acceptor implements Runnable {

    private Selector selector;
    private BlockingQueue<Event> sourceQueue = new LinkedBlockingQueue<>();

    public Acceptor(Selector selector) {
        this.selector = selector;
    }

    public void addEvent(Event event){
        sourceQueue.offer(event);
    };

    @Override
    public void run() {
        while (true) {
            Event event = null;
            try {
                event = sourceQueue.take();
            } catch (InterruptedException e) {

            }
            if (event != null) {
                selector.addEvent(event);
            }

        }
    }

}
