package com.zy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author Administrator
 */
@Slf4j
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class HisSystemApplication {

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(HisSystemApplication.class, args);
        Environment env = application.getEnvironment();
        //获取本地的IP地址
        String ip = InetAddress.getLocalHost().getHostAddress();
        //获取配置的端口号
        String port = env.getProperty("server.port");
        //获取配置的项目路径
        String path = env.getProperty("server.servlet.context-path");
        String paths;
        if (path == null) {
            paths = "";
        } else {
            paths = path;
        }
        log.info("\n----------------------------------------------------------\n\t" +
                "Application Declare is running! Access URLs:\n\t" +
                "Local: \thttp://localhost:" + port + paths + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + paths + "/\n\t" +
                "Swagger-UI: \thttp://" + ip + ":" + port + paths + "/doc.html\n" +
                "----------------------------------------------------------");
    }

}