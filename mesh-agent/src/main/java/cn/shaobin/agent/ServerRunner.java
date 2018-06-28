package cn.shaobin.agent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ServerRunner implements CommandLineRunner {

    @Autowired
    Server server;

    @Override
    public void run(String... args) throws Exception {
        server.startUp();
    }

}
