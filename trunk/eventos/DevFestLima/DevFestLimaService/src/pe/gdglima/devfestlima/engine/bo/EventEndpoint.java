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


  @SuppressWarnings({"cast", "unchecked"})  
  public List<Event> listEvent() {
    PersistenceManager mgr = getPersistenceManager();
    List<Event> result = new ArrayList<Event>();
    try{
      Query query = mgr.newQuery(Event.class);
      for (Object obj : (List<Object>) query.execute()) {
        result.add(((Event) obj));
      }
    } finally {
      mgr.close();
    }
    return result;
  }
//  "room": "0",
//  "end_date": "2012-10-27",
//  "level": "",
//  "track": ["Keynote"],
//  "start_time": "10:00",
//  "title": "Keynote",
//  "abstract": "",
//  "start_date": "2012-10-27",
//  "attending": "N",
//  "has_streaming": false,
//  "end_time": "11:00",
//  "id": "gooio2012/1600/",
//  "tags": ""
  @ApiMethod(httpMethod = "GET", name = "event.get", path = "event/get/{id}")
  public Event getEvent(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
    Event event  = null;
    event = new Event();
    event.setRoom("0");
    event.setEnd_date(new Date("10/27/2012"));
    event.setStart_time("10:00");
    event.setTitle("Keynote");
    event.setSummary("Keynote de inicio");
    event.setStart_date(new Date("10/27/2012"));
    event.setAttending("N");
    event.setHas_streaming("false");
    event.setEnd_time("11:00");
    event.setId_event("gooio2012/1600/");
    event.setTags("devfest, gdglima");
    Track track = new Track();
//    track.setName("Keynote");
//    track.setSummary("Presentacion del evento");
    TrackEndpoint trackEndpoint = new TrackEndpoint();
    track  = trackEndpoint.getTrack((long)12); 
    System.out.println("Track:"+track.getName()+" - "+track.getSummary());
    event.setTrack(track);
    
    event = insertEvent(event);
    System.out.println("ID:"+event.getId());
    try {
      event = mgr.getObjectById(Event.class, event.getId());
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
    } catch(Exception exception){
    	exception.printStackTrace();
    	
    }finally {
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
     Event event  = null;
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
