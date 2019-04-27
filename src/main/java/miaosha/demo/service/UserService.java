package miaosha.demo.service;

import miaosha.demo.dao.UserDao;
import miaosha.demo.domain.User;
import miaosha.demo.result.CodeMsg;
import miaosha.demo.result.Result;
import miaosha.demo.util.MD5Util;
import miaosha.demo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id){
        return userDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo){
        if (loginVo == null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if (user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        String dbPass = user.getPassword();
        String slatDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(formPass,slatDB);
        if (!calcPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
