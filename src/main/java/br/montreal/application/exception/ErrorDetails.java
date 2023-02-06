package br.montreal.application.exception;

import java.util.Date;

public class ErrorDetails {
	private long code;
	private Date timestamp;
    private String message;
    private String details;

    public ErrorDetails(long code, Date timestamp, String message, String details) {
         super();
         this.code = code;
         this.timestamp = timestamp;
         this.message = message;
         this.details = details;
    }
    
    public long getCode() {
        return code;
   }
    
    public Date getTimestamp() {
         return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public String getDetails() {
         return details;
    }
}
