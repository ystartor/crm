package com.ystartor.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.ystartor.crm.domain.Customer;
import com.ystartor.crm.domain.LinkMan;
import com.ystartor.crm.domain.PageBean;
import com.ystartor.crm.service.CustomerService;
import com.ystartor.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class LinkManAction implements ModelDriven<LinkMan> {
    private LinkManService linkManService;

    public void setLinkManService(LinkManService linkManService) {
        this.linkManService = linkManService;
    }
    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    private LinkMan linkMan = new LinkMan();
    @Override
    public LinkMan getModel() {
        return linkMan;
    }

    private Integer currPage = 1;
    private Integer pageSize = 3;

    public void setCurrPage(Integer currPage) {
        if(currPage == 0){
            currPage = 1;
        }
        this.currPage = currPage;
    }

    public void setPageSize(Integer pageSize) {
        if(pageSize == 0){
            pageSize =3;
        }
        this.pageSize = pageSize;
    }

    public String saveUI(){
        //查询所有客户
        List<Customer> list = customerService.findAll();
        ActionContext.getContext().getValueStack().set("list",list);
        return "saveUI";
    }


    public String findAll(){
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LinkMan.class);
        if(linkMan.getLkm_name()!=null && !"".equals(linkMan.getLkm_name())){
            detachedCriteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        if(!"".equals(linkMan.getLkm_gender())&&linkMan.getLkm_gender()!=null){
            detachedCriteria.add(Restrictions.eq("lkm_gender",linkMan.getLkm_gender()));
        }
        PageBean<LinkMan> linkManPageBean = linkManService.findAll(detachedCriteria,currPage,pageSize);
        System.out.println(linkManPageBean.getTotalCount());
        ActionContext.getContext().getValueStack().push(linkManPageBean);
        return "findAll";
    }

    public String save(){
        linkManService.save(linkMan);
        return "saveSuccess";
    }

    public String edit(){
        List<Customer> list = customerService.findAll();
        linkMan = linkManService.findById(linkMan.getLkm_id());
        ActionContext.getContext().getValueStack().set("list",list);
        ActionContext.getContext().getValueStack().push(linkMan);
        return "editSuccess";
    }

    public String update(){
        linkManService.update(linkMan);
        return "updateSuccess";
    }
    public String delete(){
        linkMan = linkManService.findById(linkMan.getLkm_id());
        linkManService.delete(linkMan);
         return "deleteSuccess";
    }

}
