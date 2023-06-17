package com.buixuantu.parking.controller;

import com.buixuantu.parking.Service.RoleService;
import com.buixuantu.parking.entity.RoleEntity;
import com.buixuantu.parking.entity.TicketEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/parking")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("role")
    public String role(ModelMap model){
        model.addAttribute("roles",roleService.getAllRole());
        return "role";
    }
    @PostMapping(value = "role",params = "btn_add_role")
    public String addRole(ModelMap model, @RequestParam("ip_id") String id,@RequestParam("ip_name") String name){

        if (roleService.findRoleById(id)!=null){
            model.addAttribute("mes","Id Invalid");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
        else{
            roleService.addRole(id,name);
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
    }

    @PostMapping(value = "role",params = "btn_update_role")
    public String updateRole(ModelMap model, @RequestParam("ip_id") String id,@RequestParam("ip_name") String name){
        if (roleService.findRoleById(id)==null){
            model.addAttribute("mes","Id Not Found");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
        else{
            roleService.updateRole(id,name);
            model.addAttribute("mes","Id Not Found");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
    }

    @PostMapping(value = "role",params = "btn_delete_role")
    public String deleteRole(ModelMap model, @RequestParam("ip_id") String id){
        if (roleService.findRoleById(id)==null){
            model.addAttribute("mes","Id Not Found");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
        else if(roleService.findRoleById(id).getEmployeeList()!=null){
            model.addAttribute("mes","Id Can't Delete");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
        else{
            roleService.deleteRoleById(id);
            model.addAttribute("mes","Id Can't Delete");
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
    }
    @PostMapping(value = "role", params = "btn_search_role")
    public String btnSearch(HttpServletRequest request, ModelMap model, @RequestParam("search_role") String id) {
        HttpSession session = request.getSession();
        if(id.equals("")==true){
            model.addAttribute("roles",roleService.getAllRole());
            return "role";
        }
        else if(roleService.findRoleById(id)==null){
            List<RoleEntity> list = new ArrayList<>();
            model.addAttribute("roles", list);
            return "role";
        }
        else {
            System.out.println(id);
            List<RoleEntity> list = new ArrayList<>();
            list.add(roleService.findRoleById(id));
            model.addAttribute("roles", list);
            return "role";
        }
    }
}
