// 
// 
// 

package mapper;

import po.Staff;

public interface LoginMapper
{
    Staff getpwdbyname(String p0);
    
    Staff getnamebyid(long p0);
}
