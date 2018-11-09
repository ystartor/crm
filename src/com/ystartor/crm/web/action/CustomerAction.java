package com.ystartor.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.ystartor.crm.domain.Customer;
import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.service.CustomerService;
import com.ystartor.crm.util.UploadUtils;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.apache.struts2.ServletActionContext;
import org.aspectj.util.FileUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
    private Customer customer = new Customer();

    @Override
    public Customer getModel() {
        return customer;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    private String uploadFileName;
    private File upload;
    private String uploadContentType;

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    private Integer currPage = 1;

    public void setCurrPage(Integer currPage) {
        if (currPage == null)
            currPage = 1;
        this.currPage = currPage;
    }

    private Integer pageSize = 3;

    public void setPageSize(Integer pageSize) {
        if (pageSize == null)
            pageSize = 3;
        this.pageSize = pageSize;
    }

    /**
     *
     */
    public String saveUI() {
        System.out.println("fd");
        return "saveUI";
    }

    /**
     * 保存客户
     */
    public String save() throws IOException {
        //
        if (upload != null) {
            //
            String path = "C:/upload";
            //
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //
            String realPath = UploadUtils.getPath(path);
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            //
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtil.copyFile(upload, dictFile);
            customer.setCust_image(url + "/" + uuidFileName);
        }
        //
        customerService.save(customer);
        return "saveSuccess";
    }

    /**
     * 分页查询客户的方法
     */
    public String findAll() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
        if (customer.getCust_name() != null) {
            detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
        }
        if (customer.getBaseDictSource() != null && !"".equals(customer.getBaseDictSource().getDict_id()) && customer.getBaseDictSource().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
        }
        if (customer.getBaseDictLevel() != null && !"".equals(customer.getBaseDictLevel().getDict_id()) && customer.getBaseDictLevel().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
        }
        if (customer.getBaseDictIndustry() != null && !"".equals(customer.getBaseDictIndustry().getDict_id()) && customer.getBaseDictIndustry().getDict_id() != null) {
            detachedCriteria.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
        }
        PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return "findAll";
    }

    public String delete() {
        //
        customer = customerService.findById(customer.getCust_id());
        //
        if (customer.getCust_image() != null) {
            File file = new File(customer.getCust_image());
            if (file.exists())
                file.delete();
        }
        //
        customerService.delete(customer);
        return "deleteSuccess";
    }

    public String edit() {
        //
        customer = customerService.findById(customer.getCust_id());
        //1.手动压栈<s:property value="cust_name"/>
        ActionContext.getContext().getValueStack().push(customer);
        //2.模型驱动<s:property value="model.cust_name"/>
        return "editSuccess";
    }

    public String update() throws IOException {
        //
        if (upload != null) {
            //
            if (customer.getCust_image() != null) {
                File file = new File(customer.getCust_image());
                if (file.exists())
                    file.delete();
            }
            //
            //
            String path = "C:/upload";
            //
            String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
            //
            String realPath = UploadUtils.getPath(path);
            String url = path + realPath;
            File file = new File(url);
            if (!file.exists()) {
                file.mkdirs();
            }
            //
            File dictFile = new File(url + "/" + uuidFileName);
            FileUtil.copyFile(upload, dictFile);
            customer.setCust_image(url + "/" + uuidFileName);
        }
        customerService.update(customer);
        return "updateSuccess";
    }

    public String findAllCustomer() throws IOException {
        List<Customer> list = customerService.findAll();

        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[]{"linkManSet","baseDictLevel","baseDictIndustry","baseDictSource"});
        JSONArray jsonArray = JSONArray.fromObject(list,jsonConfig);
        ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
        ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
        return NONE;
    }

}
