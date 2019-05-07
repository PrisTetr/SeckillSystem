package miaosha.demo.service;

import miaosha.demo.dao.UserDao;
import miaosha.demo.domain.User;
import miaosha.demo.exception.GlobalException;
import miaosha.demo.result.CodeMsg;
import miaosha.demo.util.MD5Util;
import miaosha.demo.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(long id){
        return userDao.getById(id);
    }

    public CodeMsg login(HttpServletResponse response, LoginVo loginVo){
        if (loginVo == null){
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        User user = getById(Long.parseLong(mobile));
        if (user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String slatDB = user.getSalt();
        String calcPass = MD5Util.formPassToDbPass(formPass,slatDB);
        if (!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return CodeMsg.SUCCESS;
    }
}
