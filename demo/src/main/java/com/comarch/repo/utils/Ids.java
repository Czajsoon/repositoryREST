package com.comarch.repo.utils;

import java.util.UUID;

public class Ids {

    public static String generateId(){
        UUID randomValue  = UUID.randomUUID();
        return randomValue.toString();
    }
}
