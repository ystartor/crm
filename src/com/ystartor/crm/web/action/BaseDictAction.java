package com.ystartor.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ystartor.crm.domain.BaseDict;
import com.ystartor.crm.service.BaseDictService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;

import java.io.IOException;
import java.util.List;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    private BaseDict baseDict = new BaseDict();
    @Override
    public BaseDict getModel() {
        return baseDict;
    }
    private BaseDictService baseDictService;

    public void setBaseDictService(BaseDictService baseDictService) {
        this.baseDictService = baseDictService;
    }
    public String findByTypeCode() throws IOException {
        List<BaseDict> listBaseDict = baseDictService.findByTypeCode(baseDict.getDict_type_code());
        //转换成json
//        JSONArray：数组、集合到json
//        JSONConfig：转JSON的配置对象
//        JSONObject：将对象和Map转换成对象
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
        JSONArray jsonArray = JSONArray.fromObject(listBaseDict,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;

    }

}
