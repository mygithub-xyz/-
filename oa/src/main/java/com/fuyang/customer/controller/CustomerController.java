package com.fuyang.customer.controller;

import com.fuyang.bean.Condition;
import com.fuyang.bean.Customer;
import com.fuyang.customer.service.CustomerService;
import com.fuyang.util.ExcelUtils;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/customer")
//表单展示
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @RequestMapping("/customer-show" )
    public String customerShow(@RequestParam(defaultValue = "1") int currentPage,Model model){

        PageInfo<Customer> pi= customerService.selectCustomers(currentPage);
        model.addAttribute("pi",pi);
        return "/customer.jsp";
    }
    //跳转添加界面
    @RequestMapping("/customer-add-show" )
    public String customerShow(Model model,int currentPage,int size,int pages){
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("pages",pages);
        model.addAttribute("size",size);
        return "/customer-add.jsp";
    }
    //添加
    @RequestMapping("/customer-saveInfo" )
    public  String customerSaveInfo(Customer customer,int pages,int size){
        customer.setAddtime(new Date());
        customerService.save(customer);
        if (size<3)
        {
            return "redirect:/customer/customer-search?currentPage="+pages;
        }else {
            return "redirect:/customer/customer-search?currentPage="+(pages+1);
        }
    }
    //操作（编辑/查看详情）
    @RequestMapping("/customer-edit-show" )
    public  String customerEditShow(Model model,int id,int currentPage,String flag) {
         Customer customer=customerService.selectCustomerById(id);
         model.addAttribute("customer",customer);
         model.addAttribute("currentPage",currentPage);
         if (flag.equals("edit")){
             //编辑界面
             return "/customer-edit.jsp";
         }else {
             //详情界面
             return "/customer-look.jsp";
         }
    }
    //修改customer信息
    @RequestMapping("/customer-edit")
    public String customerEdit(Customer customer,int currentPage){
         customer.setAddtime(new Date());
         customerService.editCustomerById(customer);
        return "redirect:/customer/customer-search?currentPage="+currentPage;
    }
    //修改删除customer信息
    @RequestMapping("/customer-delete")
    public String customerDelete(String ids,int lastPage){
       String[] split = ids.split(",");
        int[] idArr=new int[split.length];
        for (int i = 0; i < idArr.length; i++) {
            idArr[i]= Integer.parseInt(split[i]) ;
            
        }
        customerService.batchDelete(idArr);
        return "redirect:/customer/customer-search?currentPage="+lastPage;
    }
    //导出功能
    @RequestMapping("/customer-export")
    public String customerExport(String ids, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取数据
        String[] split = ids.split(",");
        int[] idArray = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            idArray[i] = Integer.parseInt(split[i]);
        }
        //按照一组ID查询一组数据
        List<Customer> customers = customerService.queryCustomerByIds(idArray);


        //excel标题
        String[] title = {"编号", "公司名称", "联系人", "地址", "电话", "介绍", "添加时间"};

        //excel文件名
        String fileName = "客户信息表" + System.currentTimeMillis() + ".xls";

        //sheet名
        String sheetName = "客户信息表";
        String[][] content = new String[customers.size()][];
        for (int i = 0; i < customers.size(); i++) {
            content[i] = new String[title.length];
            Customer obj = customers.get(i);
            content[i][0] = obj.getId() + "";
            content[i][1] = obj.getComname() + "";
            content[i][2] = obj.getCompanyperson() + "";
            content[i][3] = obj.getComaddress() + "";
            content[i][4] = obj.getComphone() + "";
            content[i][5] = obj.getPresent() + "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(obj.getAddtime());
            content[i][6] = format;
        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            //直接下载给用户，服务器没有保存
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/customer/customer-search";
    }
    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    //搜索功能
    @RequestMapping("/customer-search")
    public String customerSearch(@RequestParam(defaultValue = "1") int currentPage, Condition condition, Model model){
        String keyword = condition.getKeyword();
        condition.setKeyword("%"+keyword+"%");
        PageInfo<Customer> pi= customerService.queryCustomerList(currentPage,condition);
        model.addAttribute("pi",pi);
        ////////////////搜索条件的返回/////////////////
        condition.setKeyword(keyword);
        model.addAttribute("condition",condition);
        //查询条件
        Map<String,String> mapSelect = new HashMap<>();
        mapSelect.put("","选择类型...");
        mapSelect.put("comname","客户所在公司名称");
        mapSelect.put("companyperson","联系人姓名");
        model.addAttribute("mapSelect",mapSelect);
        //排序条件
        Map<String,String>mapOrder = new HashMap<>();
        mapOrder.put("","排序...");
        mapOrder.put("id","编号");
        mapOrder.put("addtime","添加时间");
        model.addAttribute("mapOrder",mapOrder);
        ////////////////搜索条件的返回/////////////////

        return "/customer.jsp";
    }
    @RequestMapping("/project-add-customer-show")
    @ResponseBody
    //ResponseEntity属于一个工具类，里面可以封装下载时的一个头、两个流；可以封装响应状态码；可以封装集合或者单一数据
    public ResponseEntity<List<Customer>> projectAddCustomerShow(){
        List<Customer> customers = customerService.queryCustomerListLimit(20,null);
        return ResponseEntity.ok(customers);//ok方法封装了200的状态码
    }

    //项目添加页面客户公司的搜索框
    @RequestMapping("/project-add-customer-search")
    @ResponseBody
    public ResponseEntity<List<Customer>>projectAddCustomerSearch(String name){
        List<Customer> customers = customerService.queryCustomerListLimit(20,"%"+name+"%");
        return ResponseEntity.ok(customers);//ok方法封装了200的状态码
    }

    //项目添加页面根据客户公司名称，显示客户联系人
    @RequestMapping("/project-add-customer-person-show")
    @ResponseBody
    public ResponseEntity<Customer>projectAddCustomerPersonShow(int id){
        Customer customer = customerService.selectCustomerById(id);
        return ResponseEntity.ok(customer);
    }
}
