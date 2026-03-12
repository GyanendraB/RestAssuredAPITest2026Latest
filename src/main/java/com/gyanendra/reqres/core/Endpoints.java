package com.gyanendra.reqres.core;

/**
 * Endpoints for ReqRes API.
 *
 * Author: Gyanendra
 */
public enum Endpoints {
    LIST_USERS("/api/users"),
    SINGLE_USER("/api/users/{id}"),
    CREATE_USER("/api/users"),
    UPDATE_USER("/api/users/{id}"),
    DELETE_USER("/api/users/{id}"),
    REGISTER("/api/register"),
    LOGIN("/api/login"),
    LIST_RESOURCES("/api/unknown"),
    SINGLE_RESOURCE("/api/unknown/{id}"),
    DELAYED_USERS("/api/users?delay={delay}");

    private final String path;

    Endpoints(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
