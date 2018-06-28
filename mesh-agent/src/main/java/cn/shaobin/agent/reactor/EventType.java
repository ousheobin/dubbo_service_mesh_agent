package cn.shaobin.agent.reactor;

public enum EventType {
    CONSUMER_WAITING_FOR_HANDLE, // IO收到请求，等待处理
    CONSUMER_WAITING_FOR_SEND_BACK; // 收到远端处理结果，等待回传
}
