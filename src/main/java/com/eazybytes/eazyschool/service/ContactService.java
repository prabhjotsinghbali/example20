package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public boolean saveMessageDetail(Contact contact)
    {
        boolean isSaved=false;
        contact.setStatus(EazySchoolConstants.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        Contact savedContact = repository.save(contact);
        if(null!=savedContact && savedContact.getContactId()>0)
        {
            isSaved=true;
        }
        return isSaved;
    }

    public List<Contact> findMessagesWithOpenStatus() {
        return repository.findMessagesWithOpenStatus(EazySchoolConstants.OPEN);
    }

    public boolean closeMessageById(int id) {

        boolean isUpdated=false;
       int result = repository.closeMessageById(id,EazySchoolConstants.CLOSED);
       if(result>0)
       {
           isUpdated=true;
       }
       return isUpdated;
    }
}
