package com.example.persis.models;

public class Rule {
    public String id;
    public String title;
    public Rule(){}

    public Rule(String id, String title){
        this.id = id;
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

}
