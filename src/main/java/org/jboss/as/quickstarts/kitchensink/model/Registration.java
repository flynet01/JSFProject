package org.jboss.as.quickstarts.kitchensink.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.UUID;

public class Registration implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 2L;

   private Long id;

   private final static String server = UUID.randomUUID().toString();
   private String name;

   public String getServer() {
      return server;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getName() {
      return name;
   }
}