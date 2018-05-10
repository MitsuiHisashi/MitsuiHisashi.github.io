import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * 服务器端WebSocket类
 * 
 * @author Administrator
 *
 */
@ServerEndpoint("/MyWebSocket")
public class MyWebSocket {
	
	//所有连接的集合
	private static ArrayList<Session> sessions = new ArrayList<>();

	/**
	 * 当客户端发送消息时的事件回调
	 * @param message
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		
		//接收来自客户端的消息
		System.out.println("来自客户端的消息：" + message);
		
		System.out.println(sessions.size());
		
		//群发
		for (Session s : sessions) {
			s.getBasicRemote().sendText(message);
		}
		
		//关闭连接，可以不关，客户端关闭浏览器就会触发连接关闭
//		session.close();
	}
	
	/**
	 * 当有客户端连接成功回调
	 */
	@OnOpen
	public void onOpen(Session session) {		
		sessions.add(session);
		System.out.println("客户端已连接" + sessions.size() + "人在线");
	}
	
	/**
	 * 当有客户端断开连接回调
	 */
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
		System.out.println("客户端已下线");
	}

}