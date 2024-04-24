package com.eazybytes.eazyschool.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name="contact_msg")
public class Contact extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "native")
    @GenericGenerator(name ="native",strategy = "native")
    private int contactId;

    @NotBlank(message = "Name should not be blank")
    @Size(min=3, message = "Name must be atleast 3 characters long")
    private String name;
    @NotBlank(message ="Mobile Number should not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number must be 10 digit number")
    private String mobileNum;
    @NotBlank(message ="Email should not be blank")
    @Email(message ="Please provide a valid email address")
    private String email;
    @NotBlank(message ="Subject must not be blank")
    @Size(min=5, message ="Subject should be atleast 5 characters long")
    private String subject;
    @NotBlank(message ="Message must not be blank")
    @Size(min=10,message="Message should be atleast 10 characters long")
    private String message;
    private String status;
}
