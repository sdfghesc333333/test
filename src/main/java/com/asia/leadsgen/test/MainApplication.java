package com.asia.leadsgen.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class MainApplication implements CommandLineRunner {

	@Value("${server.port}")
	private String serverPort;

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("PSP CAMPAIGN SERVER STARTED ON PORT : " + serverPort);

	}

	private Logger logger = Logger.getLogger(MainApplication.class.getName());

	public void setServerPort(String serverPort) {
		this.serverPort = serverPort;
	}
}
