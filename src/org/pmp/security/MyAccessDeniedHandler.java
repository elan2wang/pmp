/**
 * Author            : Elan
 * Created On        : 2012-7-12 下午02:11:02
 * 
 * Copyright 2012.  All rights reserved. 
 *
 * Revision History
 * 
 *    Date       Modifier       Comments
 * ----------    -------------  --------------------------------------------
 * 
 */
package org.pmp.security;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.pmp.json.MyJson;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * @author Elan
 * @version 1.0
 * @update TODO
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {

  //~ Static fields/initializers =====================================================================================

    public static final String SPRING_SECURITY_ACCESS_DENIED_EXCEPTION_KEY = "SPRING_SECURITY_403_EXCEPTION";
    protected static final Log logger = LogFactory.getLog(AccessDeniedHandlerImpl.class);

    //~ Instance fields ================================================================================================

    private String errorPage;

    //~ Methods ========================================================================================================

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {
        if (!response.isCommitted()) {
            if (errorPage != null) {
        	String accept = request.getHeader("accept");
    	        response.setContentType("text/html;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
                StringBuilder sb = new StringBuilder();
        	logger.debug("+++++++++++++++++++++++++++++++"+accept);
        	if(accept.contains("application/json")){
        	    Map<String,Object> params = new LinkedHashMap<String,Object>();
        	    params.put("msg", "访问受限！\n尊敬的用户，很抱歉您无权访问该资源或执行该操作！ 如有疑问请联系系统管理员！");
        	    MyJson json = new MyJson();
        	    response.getWriter().println(json.toJson(params));
        	} else {
                    sb.append("<html><head></head><body>");
                    sb.append("<h2>访问受限</h2>");
                    sb.append("<p>尊敬的用户，很抱歉您无权访问该资源或执行该操作！ 如有疑问请联系系统管理员！</p>");
                    sb.append("</body></html>");
                    response.getWriter().println(sb.toString());
        	}
            } else {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
            }
        }
    }

    /**
     * The error page to use. Must begin with a "/" and is interpreted relative to the current context root.
     *
     * @param errorPage the dispatcher path to display
     *
     * @throws IllegalArgumentException if the argument doesn't comply with the above limitations
     */
    public void setErrorPage(String errorPage) {
        if ((errorPage != null) && !errorPage.startsWith("/")) {
            throw new IllegalArgumentException("errorPage must begin with '/'");
        }

        this.errorPage = errorPage;
    }
}