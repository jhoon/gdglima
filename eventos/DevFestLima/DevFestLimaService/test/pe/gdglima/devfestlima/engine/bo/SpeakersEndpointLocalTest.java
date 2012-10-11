package pe.gdglima.devfestlima.engine.bo;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SpeakersEndpointLocalTest extends TestUtils {

	@Test
	public void testInsert() {
		String url = "http://localhost:8888/_ah/api/speakers/v1/speakers/insert";

		String jsonSpeaker = "{\"bio\" : \"Ingeniero de Software, desarrollo de aplicaciones Android, App Engine, etc.\",\"firstName\" : \"David\",   \"lastName\" : \"Motta B\",\"displayName\" : \"@dmotta\",\"plusOneUrl\" : \"url\",\"thumbnailUrl\" : \"foto\",\"userId\" : \"dmotta1\",\"speakerId\" : \"dmota\"}";
		String content = postRequest(url, jsonSpeaker);
		System.out.println("content: " + content);
		assertTrue(content.contains("\"id\" :"));
	}

	@Test
	public void testSearch() {
		String content = getRequest("http://localhost:8888/_ah/api/speakers/v1/speakers/search/dmotta");
		assertTrue(content.contains("\"items\" :"));
	}

	@Test
	public void testGet() {

		String url = "";
		String content = "";
		Long id = (long) 3;

		System.out.println("ID: " + id);
		url = "http://localhost:8888/_ah/api/speakers/v1/speakers/get/" + id;
		content = getRequest(url);
		assertTrue(content.contains("\"id\" :"));
	}

	@Test
	public void testList() {
		String content = getRequest("http://localhost:8888/_ah/api/speakers/v1/speakers/list?limit=10");
		assertTrue(content.contains("\"items\" :"));
	}

}
