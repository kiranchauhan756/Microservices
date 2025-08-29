package com.user.service;


import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
         public static String asJsonString(final Object obj ){
             try{
                 return new ObjectMapper().writeValueAsString(obj);
             }catch(Exception e){
                 throw new RuntimeException(e);
             }
         }

         public static <T> T asObject(final String str, Class<T> tclass){
             try{
                 return new <T> ObjectMapper().readValue(str,tclass);
             }catch(Exception e){
                 throw new RuntimeException(e);
             }
         }
}
