package com.example.poswebback.util;

import java.util.UUID;

public class UtilProcess {
    public static String generateId(){
        return UUID.randomUUID().toString();
    }

}
