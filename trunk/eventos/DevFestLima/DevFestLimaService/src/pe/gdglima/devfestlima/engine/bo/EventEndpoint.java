package pe.gdglima.devfestlima.engine.bo;

import pe.gdglima.devfestlima.engine.bo.PMF;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@Api(name = "event", description = "Esta entidad representa a los expositores.", version = "v1")
public class EventEndpoint {

	@ApiMethod(httpMethod = "GET", name = "event.list", path = "event/list")
	@SuppressWarnings({ "cast", "unchecked" })
	public List<Event> listEvent() {
		PersistenceManager mgr = getPersistenceManager();
		List<Event> result = new ArrayList<Event>();
		try {
			Query query = mgr.newQuery(Event.class);
			for (Object obj : (List<Object>) query.execute()) {
				result.add(((Event) obj));
			}
		} finally {
			mgr.close();
		}
		return result;
	}

	@ApiMethod(httpMethod = "GET", name = "event.get", path = "event/get/{id}")
	public Event getEvent(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Event event = new Event();
		try {
			event = mgr.getObjectById(Event.class, id);
			System.out.println(":::::::::::::::" + event.getTrack().getName());
		} finally {
			mgr.close();
		}
		return event;
	}

	@ApiMethod(httpMethod = "POST", name = "event.insert", path = "event/insert")
	public Event insertEvent(Event event) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			mgr.makePersistent(event);
		} catch (Exception exception) {
			exception.printStackTrace();

		} finally {
			mgr.close();
		}
		return event;
	}

	public Event updateEvent(Event event) {
		PersistenceManager mgr = getPersistenceManager();
		try {
			mgr.makePersistent(event);
		} finally {
			mgr.close();
		}
		return event;
	}

	public Event removeEvent(@Named("id") Long id) {
		PersistenceManager mgr = getPersistenceManager();
		Event event = null;
		try {
			event = mgr.getObjectById(Event.class, id);
			mgr.deletePersistent(event);
		} finally {
			mgr.close();
		}
		return event;
	}

	private static PersistenceManager getPersistenceManager() {
		return PMF.get().getPersistenceManager();
	}

}
