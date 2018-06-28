package cn.shaobin.agent.reactor;

public abstract class EventHandler<T> {

    public abstract void handle (Event event) throws Exception;

}
