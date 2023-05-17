// 
// 
// 

package service.impl;

import po.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import mapper.LoginMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.LoginService;

@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 5)
@Service("loginservice")
public class LoginServiceImpl implements LoginService
{
    @Autowired
    LoginMapper loginmapper;
    
    @Override
    public String getpwdbyname(final String name) {
        final Staff s = this.loginmapper.getpwdbyname(name);
        if (s != null) {
            return s.getPassword();
        }
        return null;
    }
    
    @Override
    public Long getUidbyname(final String name) {
        final Staff s = this.loginmapper.getpwdbyname(name);
        if (s != null) {
            return (long)s.getStaff_id();
        }
        return null;
    }
    
    @Override
    public String getnamebyid(final long id) {
        final Staff s = this.loginmapper.getnamebyid(id);
        if (s != null) {
            return s.getUsername();
        }
        return null;
    }
}
