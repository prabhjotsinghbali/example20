package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String displayContactPage(Model model)
    {
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

    /*@PostMapping("/saveMsg")
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum, @RequestParam String email,
                                    @RequestParam String subject, @RequestParam String message)
    {
        logger.info("name : "+name);
        logger.info("mobileNumber : "+mobileNum);
        logger.info("email : "+email);
        logger.info("subject : "+subject);
        logger.info("message : "+message);
        return new ModelAndView("redirect:/contact");
    }*/

    @PostMapping("/saveMsg")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors)
    {
        if(errors.hasErrors())
        {
            log.error("Contact Form Validation Failed due to "+errors);
            return "contact.html";
        }
        contactService.saveMessageDetail(contact);
        return "redirect:/contact";
    }

    @GetMapping("/displayMessages")
    public ModelAndView displayMessages()
    {
     List<Contact> contactMsgs = contactService.findMessagesWithOpenStatus();
     ModelAndView model = new ModelAndView("messages.html");
     model.addObject("contactMsgs",contactMsgs);
     return model;
    }

    @GetMapping("/closeMsg")
    public String closeStatus(@RequestParam int id)
    {
        contactService.closeMessageById(id);
        return "redirect:/displayMessages";
    }
}
