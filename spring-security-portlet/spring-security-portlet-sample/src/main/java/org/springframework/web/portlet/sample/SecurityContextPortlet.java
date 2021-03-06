package org.springframework.web.portlet.sample;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.portlet.GenericPortlet;
import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;


/**
 * A simple portlet which prints out the contents of the current {@link org.springframework.security.core.context.SecurityContext}
 *
 * @author Luke Taylor
 */
public class SecurityContextPortlet extends GenericPortlet {

    public void doView(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Security Context Display Portlet</h2>");
        out.println("<p>");
        out.println(new Date());
        out.println("<p>Current Session " + request.getPortletSession().getId() + "</p>");
        out.println("</p>");
        out.println("<p>The security context contains: " +
                SecurityContextHolder.getContext().getAuthentication() +
                "</p>");
        Object lastException = request.getPortletSession().getAttribute(AbstractAuthenticationProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY, PortletSession.APPLICATION_SCOPE);

        if (lastException != null) {
            out.println("Last Exception: " + lastException);
        }
    }
}
