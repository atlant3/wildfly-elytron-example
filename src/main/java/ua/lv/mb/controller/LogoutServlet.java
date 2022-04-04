package ua.lv.mb.controller;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LogoutServlet")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "RoleA", "RoleB" }))
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache, no-store");

        response.setHeader("Pragma", "no-cache");

        response.setHeader("Expires", new java.util.Date().toString());

        if (request.getSession(false) != null) {
            //    clearCache(request.getRemoteUser());
            request.getSession(false).invalidate();// remove session.

        }

        request.logout();
        response.sendRedirect(request.getContextPath());
    }
    public void clearCache(String username){
        try {
            ObjectName jaasMgr = new ObjectName("jboss.as:subsystem=security,security-domain=<YOUR SECURITY DOMAIN>" );
            Object[] params = {username};
            String[] signature = {"java.lang.String"};
            MBeanServer server = (MBeanServer) MBeanServerFactory.findMBeanServer(null).get(0);
            server.invoke(jaasMgr, "flushCache", params, signature);
        } catch (Exception ex) {

        }}
}