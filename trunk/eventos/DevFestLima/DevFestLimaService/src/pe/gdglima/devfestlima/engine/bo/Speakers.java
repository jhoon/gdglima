package pe.gdglima.devfestlima.engine.bo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Speakers {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Long id;

	@Persistent
	private String bio;

	@Persistent
	private String firstName;

	@Persistent
	private String lastName;

	@Persistent
	private String displayName;

	@Persistent
	private String plusOneUrl;

	@Persistent
	private String thumbnailUrl;

	@Persistent
	private String userId;

	@Persistent
	private String speakerId;

	public Speakers() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPlusOneUrl() {
		return plusOneUrl;
	}

	public void setPlusOneUrl(String plusOneUrl) {
		this.plusOneUrl = plusOneUrl;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSpeakerId() {
		return speakerId;
	}

	public void setSpeakerId(String speakerId) {
		this.speakerId = speakerId;
	}

}
