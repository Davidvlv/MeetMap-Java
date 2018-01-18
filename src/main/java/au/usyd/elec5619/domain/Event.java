package au.usyd.elec5619.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="Event")
public class Event {
	
	@Id
	@GeneratedValue
	@Column(name="Id")
	private long id;
	
	@Column(name="EventName")
	private String eventName;
	
	@Column(name="Description")
	private String description;
	
	@Column(name="StartTime")
	private Date startTime;
	
	@Column(name="EndTime")
	private Date endTime;
	
	
	@Column(name="Host")
	private String host;
	
	
	@Column(name="IsPrivate")
	private boolean isPrivate;
	
	@Column(name="Latitude")
	private Double latitude;
	
	@Column(name="Longitude")
	private Double longitude;
	
	//private Location location;
	
	
	//GETTERS AND SETTERS 
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setEventName(String eventName){
		this.eventName = eventName;
	}
	
	public String getEventName(){
		return eventName;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setHost(String host){
		this.host = host;
	}
	
	public String getHost(){
		return host;
	}
	
	public Date getStartTime(){
		return startTime;
	}
	
	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}
	
	public void setStartTime(String startTime, String format) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		this.startTime = sdf.parse(startTime);
	}
	
	public Date getEndTime(){
		return endTime;
	}
	
	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}
	
	
	public void setEndTime(String endTime, String format) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		this.endTime = sdf.parse(endTime);
	}
	
	
	public boolean getIsPrivate(){
		return isPrivate;
	}
	
	public void setIsPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
	}
	
	public void setLatitude(double latitude){
		this.latitude = latitude;
	}
	
	public Double getLatitude(){
		return latitude;
	}
	
	
	public void setLongitude(double longitude){
		this.longitude = longitude;
	}
	
	public Double getLongitude(){
		return longitude;
	}
	
	
//	
//	public void setLocation(Location location){
//		this.location = location;
//	}
//	
//	public Location getLocation(){
//		return location;
//	}
}
