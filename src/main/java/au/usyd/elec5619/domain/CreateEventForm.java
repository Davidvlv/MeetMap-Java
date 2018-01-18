package au.usyd.elec5619.domain;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateEventForm {
	
	@NotBlank(message="You must give an event a name.")
	@Size(min=3, message="Please enter a descriptive event name.")
	private String eventName;
	
	private String description;
	
	private String startTime;
	private String endTime;
	
	@NotBlank(message="Please enter a latitude")
	private String latitude;
	
	@NotBlank(message="Please enter a longitude")
	private String longitude;
	
	
	private boolean isPrivate;
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime){
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
		//Date startDateTime = sdf.parse(startTime);
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime){
//		System.out.println("settingEndTime");
//		System.out.println(endTime);
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
//		Date endDateTime = sdf.parse(endTime);
		this.endTime = endTime;
	}
	
	public void setIsPrivate(boolean isPrivate){
		this.isPrivate = isPrivate;
	}
	
	public boolean getIsPrivate(){
		return isPrivate;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
}
