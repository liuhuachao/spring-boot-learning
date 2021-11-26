package com.liuhuachao.websocket.server;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * WebSocket 服务端
 * @author huachao
 */
@Component
@Service
@ServerEndpoint("/api/websocket/{username}")
public class WebSocketServer {

	//region 字段

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	/**
	 * 日志标题
	 */
	private static final String LOG_TITLE = "WebSocket 服务端";

	/**
	 * 当前连接数
	 */
	private static AtomicInteger onlineCount = new AtomicInteger();

	/**
	 * 客户端对应的 WebSocketServer 对象集合
	 */
	private static ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();

	//endregion

	/**
	 * 连接建立
	 */
	@OnOpen
	public void onOpen(Session session, @PathParam("username") String userName) {
		logger.info(String.format("%s有连接(%s)建立，当前连接数：%s", LOG_TITLE, userName, getOnlineCount()));

		sessionPools.put(userName, session);
		addOnlineCount();

		try {
			sendInfo(userName, "conn_success");
		} catch (IOException e) {
			logger.error("WebSocket IO Exception");
		}
	}

	/**
	 * 连接关闭
	 */
	@OnClose
	public void onClose(@PathParam(value = "username") String userName) {
		sessionPools.remove(userName);
		subOnlineCount();

		//TODO 释放的时候，要处理的业务
		logger.info(String.format("%s连接(%s)关闭！当前在线人数为：%s", LOG_TITLE, userName, getOnlineCount()));
	}

	/**
	 * 接收消息
	 * @ Param message 客户端发送过来的消息
	 */
	@OnMessage
	public void onMessage(Session session, String message) {

		logger.info(String.format("%s收到消息：%s", LOG_TITLE, message));

		try {
			if (null == session) {
				broadcast(message);
			}
			else {
				sendMessage(session, message);
			}
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(String.format("%s收到消息后处理异常：%s", LOG_TITLE, e.getMessage()));
		}
	}

	/**
	 * 发生错误
	 * @ Param session
	 * @ Param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		error.printStackTrace();
		logger.error(String.format("%s发生错误：%s", LOG_TITLE, error.getMessage()));
	}

	/**
	 * 群发消息
	 * @param message
	 */
	public void broadcast(String message) {
		for (String userName : sessionPools.keySet()) {
			try {
				sendInfo(userName, message);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(String.format("%s给指定用户(%s)发送消息(%s)异常：%s", LOG_TITLE, userName, message, e.getMessage()));
				continue;
			}
		}

		logger.info(String.format("%s群发信息(%s)", LOG_TITLE, message));
	}

	/**
	 * 给指定用户发送消息
	 * @param userName
	 * @param message
	 * @throws IOException
	 */
	public static void sendInfo(String userName, String message) throws IOException {
		Session session = sessionPools.get(userName);
		try {
			sendMessage(session, message);
			logger.info(String.format("%s给用户(%s)发送信息:%s", LOG_TITLE, userName, message));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(String.format("%s给指定用户(%s)发送消息(%s)异常：%s", LOG_TITLE, userName, message,e.getMessage()));
		}
	}

	/**
	 * 发送消息
	 * @param session
	 * @param message
	 * @throws IOException
	 */
	public static void sendMessage(Session session, String message) throws IOException {
		if (session != null) {
			synchronized (session) {
				session.getBasicRemote().sendText(message);
				logger.info(String.format("%s发送信息：%s", LOG_TITLE, message));
			}
		}
	}

	public static synchronized AtomicInteger getOnlineCount() {
		return onlineCount;
	}

	public static synchronized void addOnlineCount() {
		WebSocketServer.onlineCount.incrementAndGet();
	}

	public static synchronized void subOnlineCount() {
		WebSocketServer.onlineCount.decrementAndGet();
	}

	public static ConcurrentHashMap<String, Session> getSessionPools() {
		return sessionPools;
	}

}
