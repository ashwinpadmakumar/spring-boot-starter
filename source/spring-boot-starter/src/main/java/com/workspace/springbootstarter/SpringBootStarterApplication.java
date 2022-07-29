package com.workspace.springbootstarter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.InputStream;
import java.io.InputStreamReader;

@SpringBootApplication
public class SpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootStarterApplication.class, args);
		getInvitationTemplate("");
	}

	public static JsonObject getInvitationTemplate(String profileType) {
		InputStream inputStream = SpringBootStarterApplication.class
				.getClassLoader()
				.getResourceAsStream("templates/" + "samples" + ".json");
		JsonElement jsonElement = JsonParser.parseReader(new InputStreamReader(inputStream));
		System.out.println(jsonElement);
		return null;
	}

}
