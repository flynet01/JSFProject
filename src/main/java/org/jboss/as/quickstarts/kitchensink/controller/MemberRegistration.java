package org.jboss.as.quickstarts.kitchensink.controller;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.jboss.as.quickstarts.kitchensink.data.InfinispanState;
import org.jboss.as.quickstarts.kitchensink.model.Registration;
import org.jboss.logging.Logger;
import org.jboss.seam.solder.logging.Category;

import org.jboss.as.quickstarts.kitchensink.model.Member;

import java.util.UUID;

// The @Stateful annotation eliminates the need for manual transaction demarcation
@Stateful
// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://sfwk.org/Documentation/WhatIsThePurposeOfTheModelAnnotation
@Model
public class MemberRegistration {

   @Inject
   @Category("jboss-as-kitchensink")
   private Logger log;

   @Inject
   private EntityManager em;

   private String lastRegisterName = "";

   @Inject
   private Event<Member> memberEventSrc;

   @Inject
   private Event<Registration> registrationEventSrc;

   @Inject
   private InfinispanState infinispanState;

   private Member newMember;

   public String getLastRegisterName() {
      return lastRegisterName;
   }

   @Produces
   @Named
   public Member getNewMember() {
      return newMember;
   }


   public void register() throws Exception {
      System.err.println("processed!");
      FacesContext facesContext = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
      session.setAttribute("key2", "value");
      infinispanState.getCache().put("key", "value");
      log.info("Registering " + newMember.getName());
      em.persist(newMember);
      memberEventSrc.fire(newMember);
      init();
   }

   @PostConstruct
   public void init() {
      newMember = new Member();
   }
}
