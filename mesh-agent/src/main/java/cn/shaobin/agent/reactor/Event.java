package cn.shaobin.agent.reactor;

public class Event<T> {

    private T source;
    private EventType type;

    public T getSource() {
        return source;
    }

    public void setSource(T source) {
        this.source = source;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

}
