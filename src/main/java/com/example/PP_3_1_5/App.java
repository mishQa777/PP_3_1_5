package com.example.PP_3_1_5;

import com.example.PP_3_1_5.Entity.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(App.class, args);

        Connect connect = context.getBean("connect",
                Connect.class);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        List<String> cookies = connect.getAllUsers(new HttpEntity<>(httpHeaders));
        System.out.println(cookies);

        httpHeaders.set("Cookie", String.join(";", cookies));
        User newUser = new User(3L, "James", "Brown", (byte)44);
        HttpEntity<User> httpEntity = new HttpEntity<>(newUser, httpHeaders);

        connect.addUser(httpEntity);
        newUser.setName("Thomas");
        newUser.setLastName("Shelby");

        connect.editUser(httpEntity);
        connect.deleteUserById(httpEntity, 3);
        System.out.println(connect.getResult());

    }
}
