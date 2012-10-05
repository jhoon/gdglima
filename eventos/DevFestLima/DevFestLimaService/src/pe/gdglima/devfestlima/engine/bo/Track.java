package pe.gdglima.devfestlima.engine.bo;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable ( identityType =  IdentityType. APPLICATION )
public class Track {
	
	@PrimaryKey
    @Persistent ( valueStrategy =  IdGeneratorStrategy. IDENTITY )
	private long id;
	
	private String name;
	private String color;
	private String abstrac;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getAbstrac() {
		return abstrac;
	}
	public void setAbstrac(String abstrac) {
		this.abstrac = abstrac;
	}
	
	

}
