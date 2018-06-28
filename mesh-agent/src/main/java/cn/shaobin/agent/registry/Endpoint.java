package cn.shaobin.agent.registry;

import java.net.InetSocketAddress;

public class Endpoint {
	
	private static final int DEFAULT_WEIGHT = 1;
	
    private final String host;
    private final int port;
    private final InetSocketAddress inetSocketAddress;
    private volatile Integer countPreTenSec;
    private volatile int weight = DEFAULT_WEIGHT ;

    public Endpoint(String host,int port){
        this.host = host;
        this.port = port;
        this.inetSocketAddress = new InetSocketAddress(host, port);
        countPreTenSec = 0;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

	public int getCountPreTenSec() {
		return countPreTenSec;
	}
	
	public  void addCount() {
		this.countPreTenSec ++;
	}
	
	public void resetCount() {
		this.countPreTenSec = 0;
	}

    public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String toString(){
        return host + ":" + port + " ( rank: "+ weight+")";
    }

	public InetSocketAddress getInetSocketAddress() {
		return inetSocketAddress;
	}

	public boolean equals(Object o){
        if (!(o instanceof Endpoint)){
            return false;
        }
        Endpoint other = (Endpoint) o;
        return other.host.equals(this.host) && other.port == this.port;
    }

	public int hashCode(){
        return host.hashCode() + port;
    }
}
