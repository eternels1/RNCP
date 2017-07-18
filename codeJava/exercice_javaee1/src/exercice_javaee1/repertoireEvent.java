package exercice_javaee1;

import java.time.LocalDateTime;

public class repertoireEvent {
	String source;
	LocalDateTime date;
	String message;
	
	public String getSource() {return source;}
	public void setSource(String source) {this.source = source;}
	public LocalDateTime getDate() {return date;}
	public void setDate(LocalDateTime date) {this.date = date;}
	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}
	
	public repertoireEvent(String source, LocalDateTime date, String message) {
		super();
		setDate(date);
		setMessage(message);
		setSource(source);
	}
	@Override
	public String toString() {
		return "repertoireEvent [source=" + source + ", date=" + date + ", message=" + message + "]";
	}
	
	 
	
	
	
	

}
