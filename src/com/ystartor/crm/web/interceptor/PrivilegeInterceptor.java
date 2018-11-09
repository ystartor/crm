package com.ystartor.crm.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.ystartor.crm.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {
    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        if(existUser== null){
            ActionSupport actionSupport = (ActionSupport) invocation.getAction();
            actionSupport.addActionError("没有denglu");
            return actionSupport.LOGIN;
        }else{
            return invocation.invoke();
        }
    }
}
