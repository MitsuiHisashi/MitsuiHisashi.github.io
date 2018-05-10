import java.io.IOException;
import java.util.ArrayList;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * ��������WebSocket��
 * 
 * @author Administrator
 *
 */
@ServerEndpoint("/MyWebSocket")
public class MyWebSocket {
	
	//�������ӵļ���
	private static ArrayList<Session> sessions = new ArrayList<>();

	/**
	 * ���ͻ��˷�����Ϣʱ���¼��ص�
	 * @param message
	 * @param session
	 * @throws IOException
	 * @throws InterruptedException
	 */
	@OnMessage
	public void onMessage(String message, Session session) throws IOException, InterruptedException {
		
		//�������Կͻ��˵���Ϣ
		System.out.println("���Կͻ��˵���Ϣ��" + message);
		
		System.out.println(sessions.size());
		
		//Ⱥ��
		for (Session s : sessions) {
			s.getBasicRemote().sendText(message);
		}
		
		//�ر����ӣ����Բ��أ��ͻ��˹ر�������ͻᴥ�����ӹر�
//		session.close();
	}
	
	/**
	 * ���пͻ������ӳɹ��ص�
	 */
	@OnOpen
	public void onOpen(Session session) {		
		sessions.add(session);
		System.out.println("�ͻ���������" + sessions.size() + "������");
	}
	
	/**
	 * ���пͻ��˶Ͽ����ӻص�
	 */
	@OnClose
	public void onClose(Session session) {
		sessions.remove(session);
		System.out.println("�ͻ���������");
	}

}