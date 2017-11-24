package com.webSocket;

import com.alibaba.fastjson.JSON;
import com.domain.UsersRobots;

import javax.websocket.EncodeException;
import javax.websocket.EndpointConfig;

public class UsersRobotsEncoder implements javax.websocket.Encoder.Text<UsersRobots>{
    @Override
    public String encode(UsersRobots usersRobots) throws EncodeException {
        return JSON.toJSONString(usersRobots);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
