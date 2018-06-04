package com.hpe.findlover.contoller.front;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hpe.findlover.model.*;
import com.hpe.findlover.service.*;
import com.hpe.findlover.util.LoverUtil;
import com.hpe.findlover.util.SessionUtils;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mwq
 * @Date Create in
 */
@Controller
@RequestMapping("search")
public class SearchController {
    private Logger logger = LogManager.getLogger(SearchController.class);

    @Autowired
    private TaocanService taocanService;
    @Autowired
    private DictService dictService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserPickService userPickService;
    @Autowired
    private LabelService labelService;
    @Autowired
    private UserLabelService userLabelService;

    @GetMapping
    public String search(Model model, HttpServletRequest request) throws Exception {
        return "front/search";
    }
    @PostMapping("/getTaocan")
    @ResponseBody
    public String getTaocan(@Param("activityForm") String activityForm,@Param("activityPlace") String activityPlace,
                            @Param("highPrice") String highPrice,@Param("lowPrice") String lowPrice,
                            @Param("pageIndex") String pageIndex){
        System.out.println(activityForm+"--"+activityPlace+"--"+highPrice+"--"+lowPrice+"--"+pageIndex);
        Map<String,Object> params=new HashMap<>();
        if(!activityForm.equals("") && !activityForm.equals("全部")){
            params.put("activityForm",activityForm);
        }
        if(!activityPlace.equals("") && !activityPlace.equals("全部")){
            params.put("activityPlace",activityPlace);
        }
        if(!highPrice.equals("")){
            params.put("highPrice",highPrice);
        }
        if(!lowPrice.equals("")){
            params.put("lowPrice",lowPrice);
        }
        if(!pageIndex.equals("")){
            params.put("pageIndex",pageIndex);
        }
        List<Taocan> taocans=taocanService.selectBySearchCondition(params);
        JSONArray jsonArray=new JSONArray();
        for (Taocan taocan:taocans){
            Map<String,String> map=new HashMap<>();
            map.put("href","/goodsdetail.html?itemid="+taocan.getTaocanId());
            map.put("src",taocan.getSearchDisplayImage());
            map.put("title",taocan.getTitle());
            map.put("description",taocan.getIntroduce());
            map.put("price",taocan.getLowPrice().toString());
            JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
            jsonArray.add(itemJSONObj);
        }
        System.out.println(jsonArray.toString());
        return jsonArray.toString();
    }
    @GetMapping("/getLabel")
    @ResponseBody
    public List<Label> getLabel(){
        List<Label> labelList = labelService.selectAll();
        return labelList;
    }

    @GetMapping("/getLabelUser")
    @ResponseBody
    public UserInfo getLabelUser(@Param("labelId") Integer labelId, @Param("pageNum") Integer pageNum,HttpServletRequest request) {
        logger.info("labelId==" + labelId + "....pageNum==" + pageNum);
        //获取对应标签下的用户
        List<UserLabel> userLabelList = userLabelService.select(new UserLabel(null, labelId));
        logger.info("userLabelList======" + userLabelList.toString());
        Integer[] ids = new Integer[userLabelList.size()];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = userLabelList.get(i).getUserId();
        }
        if(ids.length==0){
            logger.info("no more users ids.length="+ids.length);
            return new UserInfo("error",null);
        }
        logger.info("more users ids.length="+ids.length);
        UserBasic user = SessionUtils.getSessionAttr("user",UserBasic.class);
        PageHelper.startPage(pageNum, 9);
        List<UserBasic> userBasicList = userService.selectUserByIdsAndSex(ids,user.getSexual());
        userBasicList.forEach(logger::info);

        if (userBasicList.size()>0) {
            //封装用户数据
            LoverUtil.formatUserInfo(userBasicList);
            PageInfo page = new PageInfo(userBasicList);
            return new UserInfo("success", page);
        }else {
            return new UserInfo("error", null);
        }

    }

    @GetMapping("/initUserPick")
    @ResponseBody
    public UserPick initUserPick(HttpServletRequest request) throws Exception {
        UserBasic user = SessionUtils.getSessionAttr("user",UserBasic.class);
        UserPick userPick = null;
        if (user!=null) {
            userPick = userPickService.selectByPrimaryKey(user.getId());
        }
        return userPick;
    }

    @RequestMapping("/initSearchUser")
    @ResponseBody
    public UserInfo initSearchUser(@Param("pageNum")Integer pageNum,HttpServletRequest request) {
        logger.info("pageNum......" + pageNum);
        UserBasic userBasic = SessionUtils.getSessionAttr("user",UserBasic.class);
        logger.info("userBasic===="+userBasic);
        UserPick userPick = userPickService.selectByPrimaryKey(userBasic.getId());
        if (!(userBasic.getVip())){
            formatPick(userPick);
        }
        if (userPick.getWorkplace()!=null) {
            userPick.setWorkplace("%" + userPick.getWorkplace() + "%");
        }
        if (userPick.getBirthplace()!=null) {
            userPick.setBirthplace("%" + userPick.getBirthplace() + "%");
        }
        logger.info("userPick:"+userPick);
        PageHelper.startPage(pageNum,9);
        List<UserBasic> userBasicList = userService.selectUserByUserPick(userPick);
        logger.info("userBasicPickList======" + userBasicList);
        if(userBasicList.size()>0) {
            //封装用户数据
            LoverUtil.formatUserInfo(userBasicList);
            PageInfo page = new PageInfo(userBasicList);
            return new UserInfo("success", page);
        }else {
            return new UserInfo("error", null);
        }
    }

    @RequestMapping("/getSearchUser")
    @ResponseBody
    public UserInfo getSearchUser(HttpServletRequest request,Search search, @Param("pageNum")Integer pageNum) {
        UserBasic userBasic = SessionUtils.getSessionAttr("user",UserBasic.class);
        search.setId(userBasic.getId());
        if (!(userBasic.getVip())){
            formatSearch(search);
        }
        logger.info("search......" + search.toString());
        logger.info("pageNum......" + pageNum);
        PageHelper.startPage(pageNum,9);
        List<UserBasic> userBasicList = userService.selectUserBySearch(search);

        logger.info("userBasicSearchList======" + userBasicList);
        if(userBasicList.size()>0) {
            for (UserBasic user:userBasicList){
                logger.info("用户资产"+user.getUserAsset());
                logger.info("用户详细"+user.getUserDetail());
            }
            //封装用户数据
            LoverUtil.formatUserInfo(userBasicList);
            PageInfo page = new PageInfo(userBasicList);
            return new UserInfo("success", page);
        }else {
            return new UserInfo("error", null);
        }
    }

    public void formatPick(UserPick userPick){

        if(userPick.getBirthplace()!=null) {
            userPick.setBirthplace(null);
        }
        if (userPick.getEducation()!=null) {
            userPick.setEducation(null);
        }
    }

    public void formatSearch(Search search){
        if (search.getEducation()!=null) {
            search.setEducation(null);
        }
       if (search.getLiveCondition()!=null) {
           search.setLiveCondition(null);
       }
       if (search.getBirthplace()!=null) {
           search.setBirthplace(null);
       }

    }

}
