package com.hpe.findlover.contoller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hpe.findlover.model.*;
import com.hpe.findlover.service.*;
import com.hpe.findlover.token.CustomToken;
import com.hpe.findlover.util.Constant;
import com.hpe.findlover.util.Identity;
import com.hpe.findlover.util.SessionUtils;
import com.hpe.findlover.util.ShiroHelper;
import net.sf.json.JSONArray;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sinnamm
 * @Date 2017-10-16
 * @Description:
 */
@Controller
public class UserController {
	private Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
    private ImageService imageService;
    @Autowired
    private TaocanService taocanService;
    @Autowired
    private UserOrderService userOrderService;
	@Autowired
	private UserService userService;
	@Autowired
    private UserPickService userPickService;
	@Autowired
    private UserLabelService userLabelService;
	@Autowired
    private LabelService labelService;
	@RequestMapping("about")
    public String about(){
	    return "front/about";
    }
	@PostMapping("queryOrder")
    @ResponseBody
    public String queryOrder(@RequestParam("startData") String startData,@RequestParam("endData") String endData,HttpSession httpSession){
        UserBasic userBasic=(UserBasic) httpSession.getAttribute("user");
        if(userBasic==null){
            return "{\"state\":\"false\"}";
        }
        System.out.println(startData+endData);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date data1=null;
        try {
            data1 = format.parse(startData);
        }catch (Exception e){
        }
        Date date2=null;
        try {
            date2=format.parse(endData);
        }catch (Exception e){
        }
//        if(data1==null||date2==null){
//            return "{\"state\":\"false\"}";
//        }
//        Calendar calendar1=Calendar.getInstance();
//        calendar1.add(Calendar.HOUR_OF_DAY,-10);
//        Calendar calendar2=Calendar.getInstance();
        List<UserOrder> userOrders=userOrderService.selectByTime(data1,date2,userBasic.getId());

        System.out.println("userOrders.size()"+userOrders.size());
        JSONObject itemJSONObj=new JSONObject();
        JSONArray jsonArray=new JSONArray();
        itemJSONObj.put("state","success");
        for(UserOrder userOrder:userOrders){
            JSONObject tempJSONObj = JSONObject.parseObject(JSON.toJSONString(userOrder));
            jsonArray.add(tempJSONObj);
        }
        itemJSONObj.put("data",jsonArray.toString());
        System.out.println(itemJSONObj.toString());
        return itemJSONObj.toString();
	}
	@GetMapping("myorder")
    public String myOrder(){
        return "back/user/myorder";
    }
	@PostMapping("order")
    @ResponseBody
    public String generateOrder(UserOrder order,HttpSession httpSession){
	    UserBasic userBasic=(UserBasic) httpSession.getAttribute("user");
        order.setUserId(userBasic.getId());
        order.setState(0);
        Calendar calendar=Calendar.getInstance();
        order.setOrderTime(calendar.getTime());
        Taocan taocan=taocanService.selectByPrimaryKey(order.getTaocanId());
        if(order.getVersionId()==1){
            order.setTotalPrice(taocan.getTaocanTypeAPrice());
        }
        if(order.getVersionId()==2){
            order.setTotalPrice(taocan.getTaocanTypeBPrice());
        }
        if(order.getVersionId()==3){
            order.setTotalPrice(taocan.getTaocanTypeCPrice());
        }
        order.setPrepayPrice(order.getTotalPrice()/2);
        order.setPaidPrice(0);
        order.setNotPayPrice(order.getTotalPrice());
        Boolean b =userOrderService.insertSelective(order);
        if(!b){
            return "{\"state\":\"false\"}";
        }
        Map<String,String> param=new HashMap<>();
        param.put("state","success");
        param.put("url","/images/wechatPay.jpg");
	    return JSONObject.parseObject(JSON.toJSONString(param)).toString();
    }
	@RequestMapping("goodsdetail")
	public String goodsDetail(@RequestParam("itemid") String itemid,ModelMap model,HttpSession httpSession){
        System.out.println(httpSession.getAttribute("user"));
        System.out.println("*******");
	    System.out.println(itemid);
        Taocan taocan=taocanService.selectByPrimaryKey(new Integer(itemid));
        //model.addAttribute("itemid",itemid);
        Image image=imageService.selectByPrimaryKey(taocan.getDisplayImageId());
        System.out.println(Image.getNotNullValue(image));
		model.addAttribute("itemid",itemid);
        model.addAttribute("displayImages",Image.getNotNullValue(image));
        image=imageService.selectByPrimaryKey(taocan.getIntroduceImageId());
        System.out.println(Image.getNotNullValue(image));
        model.addAttribute("introduceImages",Image.getNotNullValue(image));


        model.addAttribute("title",taocan.getTitle());
        model.addAttribute("introduce",taocan.getIntroduce());
        model.addAttribute("activityForm","适用场景："+taocan.getActivityForm());
        model.addAttribute("activityPlace","适用地点："+taocan.getActivityPlace());
        model.addAttribute("enableArea","开通地区："+taocan.getEnableArea());
        model.addAttribute("displayPrice",taocan.getLowPrice());
        if(!(taocan.getTaocanTypeA()==null)){
            model.addAttribute("typeA",taocan.getTaocanTypeA());
            model.addAttribute("typeAPrice",taocan.getTaocanTypeAPrice());
        }
        if(!(taocan.getTaocanTypeB()==null)){
            model.addAttribute("typeB",taocan.getTaocanTypeB());
            model.addAttribute("typeBPrice",taocan.getTaocanTypeBPrice());
        }
        if(!(taocan.getTaocanTypeC()==null)){
            model.addAttribute("typeC",taocan.getTaocanTypeC());
            model.addAttribute("typeCPrice",taocan.getTaocanTypeCPrice());
        }

		return "front/goodsdetail";
	}

	@RequestMapping("submitform")
    public String getPersonnalMakePage(@RequestParam("itemid") String itemid,HttpSession httpSession,ModelMap modelMap){
        System.out.println("submitform");
	    modelMap.put("itemid",itemid);
        Taocan taocan=taocanService.selectByPrimaryKey(new Integer(itemid));
        modelMap.put("title",taocan.getTitle());
        System.out.println(taocan.getTaocanTypeAPrice());
        System.out.println(taocan.getTaocanTypeBPrice());
        System.out.println(taocan.getTaocanTypeCPrice());
        if(!(taocan.getTaocanTypeA()==null)){
            modelMap.addAttribute("typeA",taocan.getTaocanTypeA());
            modelMap.addAttribute("typeAPrice",taocan.getTaocanTypeAPrice());
        }
        if(!(taocan.getTaocanTypeB()==null)){
            modelMap.addAttribute("typeB",taocan.getTaocanTypeB());
            modelMap.addAttribute("typeBPrice",taocan.getTaocanTypeBPrice());
        }
        if(!(taocan.getTaocanTypeC()==null)){
            modelMap.addAttribute("typeC",taocan.getTaocanTypeC());
            modelMap.addAttribute("typeCPrice",taocan.getTaocanTypeCPrice());
        }
        modelMap.put("displayImage",taocan.getSearchDisplayImage());
	    return "back/user/personalmake";
    }

	@PostMapping("submitItem")
    @ResponseBody
	public String submitItem(@RequestParam("itemid") String itemid,HttpSession httpSession){
        Map<String,String> param=new HashMap<>();
	    if(httpSession.getAttribute("user")==null){
            param.put("isLogin","false");
            param.put("redirectItemid",itemid);
            JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(param));
            return itemJSONObj.toString();
        }
        param.put("isLogin","true");
        UserBasic userBasic=(UserBasic)httpSession.getAttribute("user");
        System.out.println(userBasic.getId());
        param.put("redirectItemid",itemid);
		return JSONObject.parseObject(JSON.toJSONString(param)).toString();
	}

	@GetMapping("login")
	public String login(HttpServletRequest request, @RequestParam(value="redirectURL",required=false) String redirectURL, ModelMap modelMap) {
		System.out.println("login");
		try {
            String url = WebUtils.getSavedRequest(request).getRequestUrl();
            System.out.println(url);//登录前的地址
        }catch (Exception e){

        }
        System.out.println(SecurityUtils.getSubject().getSession().getId());
        System.out.println("@GetMapping(\"login\")"+redirectURL);
		if(!(redirectURL==null)){
            modelMap.put("redirectURL",redirectURL);
        }else {
		    modelMap.put("redirectURL","");
        }
		return "front/login";
	}
    @PostMapping("login")
    public String login(HttpServletRequest request, UserBasic user,@RequestParam("redirectURL") String redirectURL,boolean rememberMe, RedirectAttributes redirectAttributes) {
        if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) {
            redirectAttributes.addAttribute("message", "用户名或密码不能为空！");
            return "redirect:login";
        }
        CustomToken token = new CustomToken(user.getEmail(), user.getPassword(), Identity.USER);
        logger.info("rememberMe: " + rememberMe);
        token.setRememberMe(rememberMe);
        try {
            SecurityUtils.getSubject().login(token);
            if (SecurityUtils.getSubject().isAuthenticated()) {
                ShiroHelper.flushSession();
                HttpSession session = request.getSession();
                UserBasic userBasic = userService.selectByEmail(user.getEmail());
                userService.userAttrHandler(userBasic);
                session.setAttribute("user", userBasic);
                System.out.println(redirectURL);
                if(!(redirectURL==null) && !redirectURL.equals("")){
                    return "redirect:"+redirectURL;
                }
				return "redirect:index";
            } else {
                return "redirect:login";
            }
        } catch (UnknownAccountException uae) {
            logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,未知账户");
            redirectAttributes.addAttribute("message", "用户名不存在");
        } catch (IncorrectCredentialsException ice) {
            logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,错误的凭证");
            redirectAttributes.addAttribute("message", "密码不正确");
        } catch (LockedAccountException ule) {
            logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,用户被锁定");
            redirectAttributes.addAttribute("message", "用户被锁定");
        } catch (DisabledAccountException dae) {
            logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,用户未激活");
            redirectAttributes.addAttribute("message", "用户未激活");
        }
        return "redirect:login";
    }

	@GetMapping("logout")
	public String logout() {
		SecurityUtils.getSubject().logout();
		SecurityUtils.getSubject().getSession().removeAttribute("user");
		return "redirect:login";
	}

	@GetMapping("register")
	public String register() {
		return "front/register";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, true));
	}

	@PostMapping("register")
	public String register(UserBasic user, RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {
		System.out.println(user);
	    //目前先不加上后台验证
//		将居住地的数据进行拼接
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		user.setWorkplace(province + "-" + city);
//		设置注册页面没有的必填信息
		String uuid = UUID.randomUUID().toString();
		user.setCode(uuid);
		String tmpPassword=user.getPassword();
		user.setPassword(new Md5Hash(user.getPassword(), ByteSource.Util.bytes(user.getEmail())).toString());
		user.setAuthority(1);
		//暂时将状态码设置为1
		user.setStatus(1);
        user.setSexual("男".equals(user.getSex()) ? "女": "男");
		user.setPhoto("男".equals(user.getSex()) ? Constant.MALE_PHOTO : Constant.FEMALE_PHOTO);
		user.setRegTime(new Date());
//		发送邮件
//		String url= LoverUtil.getBasePath(request)+"/"+"active?email="+user.getEmail()+"&code="+uuid;
		//	EmailUtil.sendEmailByWeb(user.getEmail(),url);
		//将用户存放在数据库中
		if (userService.insertUseGeneratedKeys(user) > 0) {
			//添加高收入，高学历标签
			userService.updateUserBasicAndUserLabel(user);
			UserBasic userBasic = userService.selectByEmail(user.getEmail());
			//用户注册成功之后，生成默认的择偶条件和标签信息
			UserPick userPick = new UserPick();
			userPick.setId(userBasic.getId());
			userPick.setSex(userBasic.getSexual());
			userPick.setAgeLow(18);
			userPick.setAgeHigh(66);
			userPick.setWorkplace(userBasic.getWorkplace());
			userPick.setMarryStatus("未婚");
			userPick.setHeightLow(150);
			userPick.setHeightHigh(200);
			if (userPickService.insert(userPick)) {
                if (StringUtils.isEmpty(user.getEmail()) || StringUtils.isEmpty(user.getPassword())) {
                    redirectAttributes.addAttribute("message", "用户名或密码不能为空！");
                    return "redirect:login";
                }
                CustomToken token = new CustomToken(user.getEmail(), tmpPassword, Identity.USER);

                token.setRememberMe(false);
                try {
                    SecurityUtils.getSubject().login(token);
                    if (SecurityUtils.getSubject().isAuthenticated()) {
                        ShiroHelper.flushSession();
                        HttpSession session = request.getSession();

                        userService.userAttrHandler(userBasic);
                        session.setAttribute("user", userBasic);
                        return "redirect:index";
                    } else {
                        return "redirect:login";
                    }
                } catch (UnknownAccountException uae) {
                    logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,未知账户");
                    redirectAttributes.addAttribute("message", "用户名不存在");
                } catch (IncorrectCredentialsException ice) {
                    logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,错误的凭证");
                    redirectAttributes.addAttribute("message", "密码不正确");
                } catch (LockedAccountException ule) {
                    logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,用户被锁定");
                    redirectAttributes.addAttribute("message", "用户被锁定");
                } catch (DisabledAccountException dae) {
                    logger.debug("对用户[" + user.getEmail() + "]进行登录验证..验证未通过,用户未激活");
                    redirectAttributes.addAttribute("message", "用户未激活");
                }
				return "redirect:index";
			} else {
				return "redirect:register";
			}

		} else {
			return "redirect:register";
		}
	}

	@PutMapping("user")
	@ResponseBody
	public boolean updateUser(UserBasic userBasic, HttpServletRequest request) {
		userBasic.setId(SessionUtils.getSessionAttr("user", UserBasic.class).getId());
		if (userService.updateByPrimaryKeySelective(userBasic)) {
			request.getSession().setAttribute("user", userService.selectByPrimaryKey(userBasic.getId()));
			return true;
		}
		return false;
	}

	@RequestMapping("checkEmail")
	@ResponseBody
	public String checkEmail(@RequestParam("email") String email) {
		UserBasic userBasic = userService.selectByEmail(email);
		if (userBasic != null) {
			return "{\"error\":\"该邮箱已被注册！\"}";
		} else {
			return "{\"ok\":\"此邮箱可用！\"}";
		}
	}

	@RequestMapping("checkid")
	@ResponseBody
	public String checkid(@RequestParam("otherId") int otherId, HttpSession session) {
		UserBasic userBasic = userService.selectByPrimaryKey(otherId);
		UserBasic user = (UserBasic) session.getAttribute("user");
		if (user.getId().equals(otherId)) {
			return "{\"error\":\"您不能和自己搞对象！\"}";
		} else if (userBasic != null) {
			return "{\"ok\":\"我们将会请ta验证该消息~\"}";
		} else {
			return "{\"error\":\"该id不存在！\"}";
		}
	}

	@GetMapping("user/exists/{id}")
	@ResponseBody
	public boolean existsById(@PathVariable int id) {
		UserBasic basic = new UserBasic();
		basic.setId(id);
		return userService.selectOne(basic) != null;
	}


	@PostMapping("getUserById")
	@ResponseBody
	public UserBasic getById(int otherUserId) {
		logger.debug("otherUserId==" + otherUserId);
		return userService.selectByPrimaryKey(otherUserId);
	}

	@GetMapping("session/user")
	@ResponseBody
	public UserBasic getSessionUser(HttpServletRequest request) {
		return SessionUtils.getSessionAttr("user", UserBasic.class);
	}

}