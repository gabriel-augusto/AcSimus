package languagesAndMessages;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class MessageTest {

	AID aid1;
	AID aid2;
	List <AID> targets1 = new ArrayList<AID>();
	HashMap <String, AID> targets2 = new HashMap<String, AID>();
	
	@Before
	public void setUp() throws Exception {
		aid1 = null;
		aid2 = null;
		targets1.add(aid1);
		targets1.add(aid2);
		targets2.put("aid1", aid1);
		targets2.put("aid2", aid2);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void prepareMessageTest() {
		ACLMessage message1 = Message.prepareMessage(0, "some language", "some message", aid1);
		ACLMessage message2 = Message.prepareMessage(0, "some language", "other message", aid1, aid2);
		ACLMessage message3 = Message.prepareMessage(0, "some language", "some other message", targets1);
		ACLMessage message4 = Message.prepareMessage(0, "some language", "new message", targets2);
		
		assertEquals("some message", message1.getContent());
		assertEquals("other message", message2.getContent());
		assertEquals("some other message", message3.getContent());
		assertEquals("new message", message4.getContent());
	}

	@Test
	public void getAnswerOfANotUnderstoodMessageTest(){
		ACLMessage message = Message.getAnswerOfANotUnderstoodMessage(aid1);
		assertEquals(Message.MSG_NOT_UNDERSTOOD, message.getContent());
	}
}
