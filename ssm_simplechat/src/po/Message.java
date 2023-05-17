// 
// 
// 

package po;

import java.util.Date;

public class Message
{
    public Long from;
    public String fromName;
    public Long to;
    public String text;
    public Date date;
    
    public Long getFrom() {
        return this.from;
    }
    
    public void setFrom(final Long from) {
        this.from = from;
    }
    
    public Long getTo() {
        return this.to;
    }
    
    public void setTo(final Long to) {
        this.to = to;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public String getFromName() {
        return this.fromName;
    }
    
    public void setFromName(final String fromName) {
        this.fromName = fromName;
    }
    
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(final Date date) {
        this.date = date;
    }
}
