package com.jdj.movie.typesHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdj.movie.model.Audience;
import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.utils.CreateTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "basicFilter",urlPatterns = "/*")
public class HTTPBasicAuthorizeHandler implements Filter {

    private static Logger logger = LoggerFactory.getLogger(HTTPBasicAuthorizeHandler.class);
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/person/exsit")));
    @Autowired
    private Audience audience;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter is init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filter is start");
        try {
            logger.info("audience:"+audience.getBase64Secret());
            HttpServletRequest request = (HttpServletRequest) servletRequest;
//            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
            String flag = request.getHeader("web-flag") == null ? "":request.getHeader("web-flag");
            logger.info("url:"+path);
            Boolean allowedPath = ALLOWED_PATHS.contains(path);
            if(allowedPath || flag.equals("wexin")){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                ReturnModel returnModel = CreateTokenUtils.checkJWT((HttpServletRequest)servletRequest,audience.getBase64Secret());
                if(returnModel.getCode() == 0){
                    filterChain.doFilter(servletRequest,servletResponse);
                }else {
                    // response.setCharacterEncoding("UTF-8");
//                    response.setContentType("application/json; charset=utf-8");
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    ReturnModel rm = new ReturnModel();
//                    response.getWriter().print(rm);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        logger.info("filter is destroy");
    }

}
