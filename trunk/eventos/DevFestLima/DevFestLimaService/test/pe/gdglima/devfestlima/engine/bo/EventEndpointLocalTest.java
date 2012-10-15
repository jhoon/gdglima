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
	String jsonEvent = "{ \"room\": \"\0\", \"end_date\": \"2012-10-27\", \"level\": \"\", \"track\": \"\",\"start_time\": \"10:00\", \"title\": \"Keynote\",\"summary\": \"\", \"start_date\": \"2012-10-27\", \"attending\": \"N\",\"has_streaming\": \"false\",\"end_time\": \"11:00\",\"id\": \"gooio2012/1600/\",\"tags\": \"\"}";

	@Test
	public void testInsert() {
		String content = postRequest(url_base + url_event_insert, jsonEvent);
		System.out.println("content: " + content);
		assertTrue(content.contains("\"id\" :"));
	}

	@Test
	public void testGet() {
		String url = "";
		String content = "";
		Long id = (long) 3;
		url = url_base + url_event_get + id;
		content = getRequest(url);
		assertTrue(content.contains("\"id\" :"));
	}

}
