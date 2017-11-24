package com.webSocket;


import com.alibaba.fastjson.JSON;
import com.domain.UsersRobots;

import javax.websocket.DecodeException;
import javax.websocket.EndpointConfig;

public class UsersRobotsDecoder implements javax.websocket.Decoder.Text<UsersRobots>{
    @Override
    public UsersRobots decode(String usersRobots) throws DecodeException {
        return JSON.parseObject(usersRobots, UsersRobots.class);
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
