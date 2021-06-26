package com.demo.model;

import java.util.HashMap;

public class Cache{

    public static class ResponseCache {
        private int expiration ;
        private Object response ;

        public int getExpiration() {
            return expiration;
        }

        public void setExpiration(int expiration) {
            this.expiration = expiration;
        }

        public Object getResponse() {
            return response;
        }

        public void setResponse(Object response) {
            this.response = response;
        }
    }

    private HashMap<String ,ResponseCache> requestMap = new HashMap<>();


    public HashMap<String ,ResponseCache>  getRequestMap() {
        return requestMap;
    }

    public void setRequestMap(HashMap<String ,ResponseCache>  requestMap) {
        this.requestMap = requestMap;
    }

    public Object getResponseFromCache(String key){
        Cache.ResponseCache responseCache = this.requestMap.get(key);
        if(responseCache != null) {
            if (responseCache.getExpiration() > 0) {
                responseCache.setExpiration(responseCache.getExpiration() - 1);
//            LOGGER.info("CheckTicketAvailability request was answered from Cache" );
                return responseCache.getResponse();
            } else if (responseCache.getExpiration() == 0) {
                this.requestMap.remove(key);
            }
        }
        return null;
    }

    public void addNewRequestToCache(Object resp,int expiration,String path){
        Cache.ResponseCache newResponseCache = new Cache.ResponseCache();
        newResponseCache.setResponse(resp);
        newResponseCache.setExpiration(expiration);
        this.requestMap.put(path, newResponseCache);
    }


}
