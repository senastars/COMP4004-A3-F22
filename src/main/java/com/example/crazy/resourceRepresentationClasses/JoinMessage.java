package com.example.crazy.resourceRepresentationClasses;

public class JoinMessage {
    private final long id;

    public JoinMessage(long id) {
        this.id = id;
    }

    public long getId(){
        return this.id;
    }
}
