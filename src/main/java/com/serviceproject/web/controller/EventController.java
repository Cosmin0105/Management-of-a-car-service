package com.serviceproject.web.controller;

import com.serviceproject.web.Service.EventService;
import com.serviceproject.web.Service.UserService;
import com.serviceproject.web.dto.Autoturismedto;
import com.serviceproject.web.dto.Eventdto;
import com.serviceproject.web.models.Event;
import com.serviceproject.web.models.UserEntity;
import com.serviceproject.web.security.SecurityUtil;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class  EventController {
    private EventService eventService;

    private UserService userService;

    @Autowired
    public EventController(EventService eventService , UserService userService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        UserEntity user = new UserEntity();
        List<Eventdto> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if(username != null){

            user= userService.findByUsername(username);
            model.addAttribute("user" , user);
        }
        model.addAttribute("user" , user);
        model.addAttribute("events", events);
        return "events-list";
    }

@GetMapping("/events/{eventId}")
public String viewEvent(@PathVariable("eventId") Long eventId , Model model){
UserEntity user = new UserEntity();
Eventdto eventdto = eventService.findByEventId(eventId);
    String username = SecurityUtil.getSessionUser();
    if(username != null){

        user= userService.findByUsername(username);
        model.addAttribute("user" , user);
    }
    model.addAttribute("autoturisme" , eventdto);
    model.addAttribute("user" , user);
model.addAttribute("event" , eventdto);
return "events-detail";
}

    @GetMapping("/events/{autosId}/new")
    public String createEventForm(@PathVariable("autosId") Long autosId , Model model){
        Event event = new Event();
        model.addAttribute("autosId",autosId);
        model.addAttribute("event",event);
        return "events-create";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId , Model model){
        Eventdto event = eventService.findByEventId(eventId);
        model.addAttribute("event" , event);
        return "events-edit";
    }


@PostMapping("/events/{autosId}")
    public String createEvent(@PathVariable("autosId") Long autosId , @ModelAttribute("event") Eventdto eventdto ,
                              BindingResult result,
                              Model model){
    if(result.hasErrors()){
        model.addAttribute("event",eventdto);
        return "autos-create";
    }
        eventService.createEvent(autosId, eventdto);
        return "redirect:/autos/" + autosId;


}

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(@PathVariable("eventId") Long eventId, @Valid @ModelAttribute("event") Eventdto event , BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("event",event);
            return "events-edit";
        }
        Eventdto eventdto = eventService.findByEventId(eventId);
        event.setId(eventId); // Set the ID before updating
        event.setAuturisme(eventdto.getAuturisme());
        eventService.updateEvent(event);
        return "redirect:/events";
    }
    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") long eventId){
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }

    @GetMapping("/api/excel/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        // Create a new workbook and sheet
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Events");

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Denumire");
        headerRow.createCell(2).setCellValue("Tip");

        // Fetch data from the service
        List<Eventdto> events = eventService.findAllEvents();

        // Create data rows
        int rowNum = 1;
        for (Eventdto event : events) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(event.getId());
            row.createCell(1).setCellValue(event.getName());
            row.createCell(2).setCellValue(event.getType());

        }

        // Set the response headers
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=events.xlsx");

        // Write the workbook content to the response stream
        workbook.write(response.getOutputStream());
        workbook.close();
    }


}
