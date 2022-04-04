package ua.lv.mb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/HomeServlet")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "RoleA", "RoleB" }))
public class HomeServlet extends HttpServlet {

    private static String PAGE_HEADER = "<html><head><title>servlet-security</title></head><body>";

    private static String PAGE_FOOTER = "</body></html>";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String remoteUser = null;
        remoteUser = req.getRemoteUser();

        writer.println(PAGE_HEADER);
        writer.println("<h1>" + "HOME" + "</h1>");
        writer.println("<p>" + "User : " + remoteUser + "</p>");
        writer.println("<a href='/wildfly-elytron-example-1.0/ActionAServlet'>" + "<button>" + "ActionA" + "</button>" + "</a>");
        writer.println("<a href='/wildfly-elytron-example-1.0/ActionBServlet'>" + "<button>" + "ActionB" + "</button>" + "</a><br>");
        writer.println("<br><a href='/wildfly-elytron-example-1.0/LogoutServlet'>" + "<button>" + "Logout" + "</button>" + "</a>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}