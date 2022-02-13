package com.rajat.springparallelwebclients.service;

import com.rajat.springparallelwebclients.model.Item;
import com.rajat.springparallelwebclients.model.User;
import com.rajat.springparallelwebclients.model.UserWithItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.logging.Logger;

@Service
public class UserClient {
    private static final Logger LOG = Logger.getLogger(UserClient.class.getName());

    /*@Value("${unknown.param:some default}")
    private String someDefault;*/

    private WebClient webClient;

    public UserClient(@Value("${uri.path:localhost}") String uri) {
        this.webClient = WebClient.create(uri);
    }

    public Mono<User> getUser(int id) {
        return webClient.get()
                .uri("/user/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Mono<Item> getItem(int id) {
        return webClient.get()
                .uri("/item/{id}", id)
                .retrieve()
                .bodyToMono(Item.class);
    }

    public Mono<User> getOtherUser(int id) {
        return webClient.get()
                .uri("/otheruser/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

    public Flux<User> fetchUsers(List<Integer> userIds) {
        return Flux.fromIterable(userIds)
                .flatMap(this::getUser);
    }

    public Flux<User> fetchUserAndOtherUser(int id) {
        LOG.info(" In fetchUserAndOtherUser");
        return Flux.merge(getUser(id), getOtherUser(id));
    }

    public Mono<UserWithItem> fetchUserAndItem(int userId, int itemId) {
        Mono<User> user = getUser(userId);
        Mono<Item> item = getItem(itemId);

        return Mono.zip(user, item, UserWithItem::new);
    }

}
