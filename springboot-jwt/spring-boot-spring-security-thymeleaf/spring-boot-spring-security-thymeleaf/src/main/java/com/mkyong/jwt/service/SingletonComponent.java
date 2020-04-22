package com.mkyong.jwt.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("singletonComponent")
public class SingletonComponent {

    private SingletonComponent() {

    }

    public String getName(){
        return "Ganesh";
    }
    
}
