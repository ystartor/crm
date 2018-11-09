package com.ystartor.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ystartor.crm.domain.User;
import com.ystartor.crm.service.UserService;
import net.sf.json.JSONArray;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class UserAction extends ActionSupport implements ModelDriven<User> {
    private User user = new User();
    @Override
    public User getModel() {
        return user;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户注册的方法
     */
    public String regist(){
        userService.regist(user);
        return LOGIN;
    }

    /**
     *
     * @return
     */
    public String login(){
        User existUser = userService.login(user);
        if (existUser==null){
            this.addActionError("反正是错l");
            return LOGIN;
        }else{
//            ServletActionContext.getRequest().getSession().setAttribute("user",user);
            ActionContext.getContext().getSession().put("existUser",existUser);
            return SUCCESS;
        }
    }

    public String findAllUser() throws IOException {
        List<User> list = userService.findAllUser();
        JSONArray jsonArray = JSONArray.fromObject(list);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }


}
