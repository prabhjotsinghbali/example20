package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return repository.findByStatus(EazySchoolConstants.OPEN);
    }

    public boolean closeMessageById(int id) {

        boolean isUpdated=false;
       Optional<Contact> contact =repository.findById(id);
       if(contact.isPresent())
       {
           contact.get().setStatus(EazySchoolConstants.CLOSED);
           contact.get().setUpdatedBy("Prabh");
           contact.get().setUpdatedAt(LocalDateTime.now());
       }
       Contact updatedContact = repository.save(contact.get());
       if(null!=updatedContact && updatedContact.getCreatedBy()!=null)
       {

           isUpdated=true;
       }
       return isUpdated;
    }
}
