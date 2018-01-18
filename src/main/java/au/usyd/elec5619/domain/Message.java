package au.usyd.elec5619.domain;

import java.io.Serializable;

import javax.validation.constraints.*;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="Message")
public class Message implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name="Id", unique=true)
	private long id;
	
	@NotBlank(message = "Error - recipient does not exist in the database.")
	@Size(min = 3, max = 15, message = "Your recepients username must between 3 and 15 characters")
	@JoinColumn(name = "Username", table = "User")
	@Column(name="Recipient")
	private String recipient;

	@NotBlank(message = "Error - subject not provided")
	@Column(name="Subject")
	private String subject;
	
	@NotBlank(message = "Error - no message body found")
	@Column(name="MessageBody")
	private String messageBody;

	@NotBlank(message = "Error - sender does not exist in the database")
	@JoinColumn(name = "Username", table = "User")
	@Column(name="Sender")
	private String sender;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}


}
