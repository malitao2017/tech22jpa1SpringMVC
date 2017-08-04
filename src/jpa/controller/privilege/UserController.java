package jpa.controller.privilege;

import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jpa.entity.UserInfo;
import jpa.service.privilege.UserService;


//控制层，暂时还没有写一个公共的Controller用于被继承，就直接写UserController吧
@Controller
@RequestMapping(value="/user")
public class UserController {
	private static final Log log = LogFactory.getLog(UserController.class);
	
	//实例化
	@Autowired
	private UserService userService;//注入业务接口
	
	/**
     * 用户列表
     */
	@RequestMapping(value="/userList.do")
	public String userList(ModelMap modelMap){
		log.info("访问用户列表");//打印一句话测试，不查数据库了
		//根据SpringMVC配置文件配好的前缀和后缀，自动转为：/WEB-INF/jsp/user/userList.jsp
		return "user/userList"; 
	}
	/**
     * 根据主键查找用户
     */
	@RequestMapping(value="/getUserById.do")
	public String getUserById(@RequestParam long id,RedirectAttributes ats){
		log.info("id is "+id);
		UserInfo userInfo = userService.getById(UserInfo.class, id);
		if(userInfo != null)
			ats.addFlashAttribute("message","User's name is:"+userInfo.getName());
		else
			ats.addFlashAttribute("message","数据库中没有id=1的数据");
//		return "user/userList";//不能显示信息
		return "redirect:userList.do";
	}
	/**
	 * 保存用户
	 */
	@RequestMapping(value="/saveUser.do")
	public String saveUser(UserInfo userInfo,RedirectAttributes redirectAttributes){
		int name = (new Random()).nextInt();
		log.info("保存用户：Name"+name);
		
		userInfo=new UserInfo();
		userInfo.setAccount("Account"+name);
		userInfo.setName("Name"+name);
		userInfo.setPassword("Password"+name);
		userService.save(userInfo);
		//重定向后的提示信息，使用RedirectAttributes传递，在JSP页面可以用${message}获取；提示信息只出现一次，刷新也不会重复提示
		redirectAttributes.addFlashAttribute("message","保存姓名:"+userInfo.getName());
		//重定向，防止表单重复提交
		return "redirect:userList.do";//相对于当前路径  
//		return "redirect:/user/userList.do";//相对于当前项目根路径
	}
	
	
}
