package com.alex_mono.service.impl;

import com.alex_mono.constant.Constant;
import com.alex_mono.dao.UserDao;
import com.alex_mono.domain.User;
import com.alex_mono.service.UserService;
import com.alex_mono.utils.BeanFactory;
import com.alex_mono.utils.MailUtils;

public class UserServiceImpl implements UserService {

	@Override
	/**
	 * 用户注册
	 */
	public void regist(User user) throws Exception {
		//1.调用dao完成注册
		//UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		ud.save(user);
		
		//2.发送激活邮件
		String emailMsg="恭喜"+user.getName()+":成为我们商城的一员,<a href='http://localhost/store/user?method=active&code="+user.getCode()+"'>点此激活</a>";
		MailUtils.sendMail(user.getEmail(), emailMsg);
	}

	@Override
	/**
	 * 用户激活
	 */
	public User active(String code) throws Exception {
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		//1.通过code获取用户
		User user=ud.getByCode(code);
		
		//1.1 通过激活码没有找到 用户
		if(user == null){
			return null;
		}
		
		//2.若获取到了 修改用户
		user.setState(Constant.USER_IS_ACTIVE);
		user.setCode(null);
		
		ud.update(user);
		return user;
	}

	@Override
	/**
	 * 用户登录
	 */
	public User login(String username, String password) throws Exception {
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		
		return ud.getByUsernameAndPwd(username,password);
	}

}
