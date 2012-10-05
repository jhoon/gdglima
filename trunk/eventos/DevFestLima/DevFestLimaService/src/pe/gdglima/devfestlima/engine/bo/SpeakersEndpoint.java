package pe.gdglima.devfestlima.engine.bo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.jdo.PersistenceManager;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;

@Api(name = "devsite_speakers")
public class SpeakersEndpoint {

  /**
   * This method lists all the entities inserted in datastore.
   * It uses HTTP GET method.
   *
   * @return List of all entities persisted.
   */
  @SuppressWarnings({"cast", "unchecked"})
  @ApiMethod ( name = "speakers.list" , path = "speakers/list" )
  public List<Speakers> listSpeakers() {
    PersistenceManager mgr = getPersistenceManager();
    List<Speakers> result = new ArrayList<Speakers>();
    
    Speakers speakers= new Speakers();
    speakers.setBio("Ingeniero de Software, desarrollo de aplicaciones Android, App Engine, etc.");
    speakers.setFirstName("David");
    speakers.setLastName("Motta B");
    speakers.setDisplayName("@dmotta");
    speakers.setPlusOneUrl("url");
    speakers.setThumbnailUrl("foto");
    speakers.setUserId("dmotta");
    speakers.setUserId("+dmotta");
    result.add(speakers);
//    try{
//      Query query = mgr.newQuery(Speakers.class);
//      for (Object obj : (List<Object>) query.execute()) {
//        result.add(((Speakers) obj));
//      }
//    } finally {
//      mgr.close();
//    }
    return result;
  }

  /**
   * This method gets the entity having primary key id. It uses HTTP GET method.
   *
   * @param id the primary key of the java bean.
   * @return The entity with primary key id.
   */
  public Speakers getSpeakers(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
    Speakers speakers  = null;
    try {
      speakers = mgr.getObjectById(Speakers.class, id);
    } finally {
      mgr.close();
    }
    return speakers;
  }

  /**
   * This inserts the entity into App Engine datastore.
   * It uses HTTP POST method.
   *
   * @param speakers the entity to be inserted.
   * @return The inserted entity.
   */
  public Speakers insertSpeakers(Speakers speakers) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      mgr.makePersistent(speakers);
    } finally {
      mgr.close();
    }
    return speakers;
  }

  /**
   * This method is used for updating a entity.
   * It uses HTTP PUT method.
   *
   * @param speakers the entity to be updated.
   * @return The updated entity.
   */
  public Speakers updateSpeakers(Speakers speakers) {
    PersistenceManager mgr = getPersistenceManager();
    try {
      mgr.makePersistent(speakers);
    } finally {
      mgr.close();
    }
    return speakers;
  }

  /**
   * This method removes the entity with primary key id.
   * It uses HTTP DELETE method.
   *
   * @param id the primary key of the entity to be deleted.
   * @return The deleted entity.
   */
  public Speakers removeSpeakers(@Named("id") Long id) {
    PersistenceManager mgr = getPersistenceManager();
     Speakers speakers  = null;
    try {
      speakers = mgr.getObjectById(Speakers.class, id);
      mgr.deletePersistent(speakers);
    } finally {
      mgr.close();
    }
    return speakers;
  }

  private static PersistenceManager getPersistenceManager() {
    return PMF.get().getPersistenceManager();
  }

}
