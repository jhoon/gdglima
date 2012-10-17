package pe.gdglima.devfestlima.engine.bo;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

//"room": "0",
//"end_date": "2012-10-27",
//"level": "",
//"track": ["Keynote"],
//"start_time": "10:00",
//"title": "Keynote",
//"abstract": "",
//"start_date": "2012-10-27",
//"attending": "N",
//"has_streaming": false,
//"end_time": "11:00",
//"id": "gooio2012/1600/",
//"tags": ""

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Event {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;
	
	@Persistent
	private String room;
	@Persistent
	private Date end_date;
	
	
	@Persistent(defaultFetchGroup = "true", dependent = "true")
	private Track track;
	
	@Persistent
	private String start_time;
	@Persistent
	private String title;
	@Persistent
	private String summary;
	@Persistent
	private Date start_date;
	@Persistent
	private String attending;
	@Persistent
	private String has_streaming;
	@Persistent
	private String end_time;
	@Persistent
	private String id_event;
	@Persistent
	private String tags;

	
	public Event() {
		// TODO Auto-generated constructor stub
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getRoom() {
		return room;
	}


	public void setRoom(String room) {
		this.room = room;
	}


	public Date getEnd_date() {
		return end_date;
	}


	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}


	public Track getTrack() {
		return track;
	}


	public void setTrack(Track track) {
		this.track = track;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public Date getStart_date() {
		return start_date;
	}


	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}


	public String getAttending() {
		return attending;
	}


	public void setAttending(String attending) {
		this.attending = attending;
	}


	public String getHas_streaming() {
		return has_streaming;
	}


	public void setHas_streaming(String has_streaming) {
		this.has_streaming = has_streaming;
	}


	public String getEnd_time() {
		return end_time;
	}


	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}


	public String getId_event() {
		return id_event;
	}


	public void setId_event(String id_event) {
		this.id_event = id_event;
	}


	public String getTags() {
		return tags;
	}


	public void setTags(String tags) {
		this.tags = tags;
	}
	
	
}
