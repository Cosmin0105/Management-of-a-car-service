package com.serviceproject.web.controller;

import com.serviceproject.web.Service.AutoturismeService;
import com.serviceproject.web.Service.UserService;
import com.serviceproject.web.dto.Autoturismedto;
import com.serviceproject.web.models.Autoturisme;
import com.serviceproject.web.models.UserEntity;
import com.serviceproject.web.security.SecurityUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.util.List;

@Controller
public class AutoturismeController {
    private AutoturismeService autoturismeService;
    private UserService userService;

    @Autowired
    public AutoturismeController(AutoturismeService autoturismeService , UserService userService) {
        this.userService = userService;
        this.autoturismeService = autoturismeService;
    }
    @GetMapping("/autos")
    public String listAutomobile(Model model){
        UserEntity user =new UserEntity();
        List<Autoturismedto> autos = autoturismeService.findAllAutoturisme();
        String username = SecurityUtil.getSessionUser();
        if(username != null){

            user= userService.findByUsername(username);
            model.addAttribute("user" , user);
        }
        model.addAttribute("user" , user);
        model.addAttribute("autos", autos);
        return "autos-list";

    }

@GetMapping("/autos/{autosId}")
public String autosDetail(@PathVariable("autosId") long autosId,Model model){
    UserEntity user =new UserEntity();
    Autoturismedto auto = autoturismeService.findAutoturismeById(autosId);
    String username = SecurityUtil.getSessionUser();
    if(username != null){

        user= userService.findByUsername(username);
        model.addAttribute("user" , user);
    }
    model.addAttribute("user" , user);
    model.addAttribute("autoturisme",auto);
    return "autos-detail";
}



    @GetMapping("/autos/new")
    public String createAutosForm(Model model){

        Autoturisme autoturisme = new Autoturisme();
        model.addAttribute("autoturisme",autoturisme);
        return "autos-create";
    }

    @GetMapping("/autos/{autosId}/delete")
    public String deleteAuto(@PathVariable("autosId") Long autosId){
        autoturismeService.delete(autosId);
        return "redirect:/autos";
    }

    @GetMapping("/autos/search")
    public String searchauto(@RequestParam(value="query") String query, Model model) {
        List<Autoturismedto> auto = autoturismeService.searchauto(query);
        model.addAttribute("autoturisme", auto);
        return "autos-list";
    }


    @PostMapping("/autos/new")
    public String saveAuto(@Valid @ModelAttribute("autoturisme") Autoturismedto autoturismedto, BindingResult result , Model model){
if(result.hasErrors()){
    model.addAttribute("autoturisme", autoturismedto);
    return "autos-create";
}
        autoturismeService.saveauto(autoturismedto); // Use the instance, not the interface
        return "redirect:/autos";
    }

    @GetMapping("/autos/{autosId}/edit")
    public String updateAutosForm(@PathVariable("autosId") long autosId, Model model) {
        Autoturismedto auto = autoturismeService.findAutoturismeById(autosId);
        model.addAttribute("autoturisme", auto);
        return "autos-edit";
    }

    @PostMapping("/autos/{autosId}/edit")
    public String updateAutoturisme(@PathVariable("autosId") long autosId, @Valid @ModelAttribute("auto") Autoturismedto auto , BindingResult result ,Model model) {
        if(result.hasErrors()){
            model.addAttribute("auto",auto);
            return "autos-edit";
        }

        auto.setId(autosId); // Set the ID before updating
        autoturismeService.updateAutoturisme(auto);
        return "redirect:/autos";
    }


    @GetMapping("/export")
    public String export() {
        return "export"; // This should correspond to your export.html template
    }

}
