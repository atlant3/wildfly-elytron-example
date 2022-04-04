package ua.lv.mb.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ActionBServlet")
@ServletSecurity(@HttpConstraint(rolesAllowed = { "RoleA" }))
public class ActionBServlet extends HttpServlet {

    private static String PAGE_HEADER = "<html><head><title>servlet-security</title></head><body>";

    private static String PAGE_FOOTER = "</body></html>";

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        String remoteUser = null;
        remoteUser = req.getRemoteUser();

        writer.println(PAGE_HEADER);
        writer.println("<p>" + "User : " + remoteUser + "</p>");
        writer.println("<h1>" + "ActionB completed" + "</h1>");
        writer.println("<a href='/wildfly-elytron-example-1.0/HomeServlet'>" + "<button>" + "Back" + "</button>" + "</a>");
        writer.println("<a href='/wildfly-elytron-example-1.0/LogoutServlet'>" + "<button>" + "Logout" + "</button>" + "</a>");
        writer.println(PAGE_FOOTER);
        writer.close();
    }

}