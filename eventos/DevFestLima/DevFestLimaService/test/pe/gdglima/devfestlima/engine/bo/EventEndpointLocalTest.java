package pe.gdglima.devfestlima.engine.bo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author dmotta
 * 
 */
public class EventEndpointLocalTest extends TestUtils {

	String url_base = "http://localhost:8888/_ah/api/event/v1/";
	String url_event_insert = "event/insert";
	String url_event_get = "event/get/";
	String jsonEvent = "{ \"room\": \"0\", \"end_date\": \"2012-10-27\", \"level\": \"1\", \"start_time\": \"10:00\", \"title\": \"Keynote\",\"summary\": \"AAA\", \"start_date\": \"2012-10-27\", \"attending\": \"N\",\"has_streaming\": \"false\",\"end_time\": \"11:00\",\"id_event\": \"gooio2012/1600/\",\"tags\": \"ANDROID\"}";

	@Test
	public void testInsert() {
		
		System.out.println("Trama enviada: "+jsonEvent);
		String content = postRequest(url_base + url_event_insert, jsonEvent);
		System.out.println("content: " + content);
		assertTrue(content.contains("\"id\" :"));
	}

	@Test
	public void testGet() {
		String url = "";
		String content = "";
		Long id = (long) 13;
		url = url_base + url_event_get + id;
		content = getRequest(url);
		assertTrue(content.contains("\"id\" :"));
	}
	
	@Test
	public void testList() {
		String content = getRequest("http://localhost:8888/_ah/api/event/v1/event/list");
		assertTrue(content.contains("\"items\" :"));
	}

}
