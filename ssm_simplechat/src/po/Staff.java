// 
// 
// 

package po;

public class Staff
{
    private byte staff_id;
    private String first_name;
    private String last_name;
    private short address_id;
    private String email;
    private String username;
    private String password;
    private String last_update;
    
    public String getLast_update() {
        return this.last_update;
    }
    
    public byte getStaff_id() {
        return this.staff_id;
    }
    
    public void setStaff_id(final byte staff_id) {
        this.staff_id = staff_id;
    }
    
    public void setLast_update(final String last_update) {
        this.last_update = last_update;
    }
    
    public String getFirst_name() {
        return this.first_name;
    }
    
    public void setFirst_name(final String first_name) {
        this.first_name = first_name;
    }
    
    public String getLast_name() {
        return this.last_name;
    }
    
    public void setLast_name(final String last_name) {
        this.last_name = last_name;
    }
    
    public short getAddress_id() {
        return this.address_id;
    }
    
    public void setAddress_id(final short address_id) {
        this.address_id = address_id;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(final String email) {
        this.email = email;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(final String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(final String password) {
        this.password = password;
    }
}
