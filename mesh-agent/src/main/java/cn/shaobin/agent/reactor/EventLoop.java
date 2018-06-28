package cn.shaobin.agent.reactor;

import io.netty.util.concurrent.DefaultThreadFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class EventLoop {

    private Map<EventType, EventHandler> eventHandlerMap = new ConcurrentHashMap<>();
    private Selector selector;
    private Executor threadPool;

    public EventLoop(Selector selector) {
        this.selector = selector;
        this.threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors()*2,
                Runtime.getRuntime().availableProcessors()*2, 0, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(800),
                new DefaultThreadFactory("Event-Loop", true));
    }

    public void registEventHandler(EventType eventType, EventHandler eventHandler) {
        eventHandlerMap.put(eventType, eventHandler);
    }

    public void removeEventHandler(EventType eventType) {
        eventHandlerMap.remove(eventType);
    }

    public void handleEvents() throws Exception {
        dispatch();
    }

    //此例只是实现了简单的事件分发给相应的处理器处理，例子中的处理器都是同步，在reactor模式的典型实现NIO中都是在handle异步处理，来保证非阻塞
    private void dispatch() {
        while (true) {
            List<Event> events = selector.select();

            for (Event event : events) {
                EventHandler eventHandler = eventHandlerMap.get(event.getType());
                threadPool.execute(()->{
                    try {
                        eventHandler.handle(event);
                    }catch (Exception ex){

                    }
                });
            }
        }
    }

}
