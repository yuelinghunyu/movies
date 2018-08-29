package com.jdj.movie.typesHandlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jdj.movie.model.Audience;
import com.jdj.movie.model.ReturnModel;
import com.jdj.movie.model.Token;
import com.jdj.movie.utils.CreateTokenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HTTPBasicAuthorizeHandler implements Filter {

    private static Logger logger = LoggerFactory.getLogger(HTTPBasicAuthorizeHandler.class);
    @Autowired
    private Token token;
    @Autowired
    private Audience audience;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter is init");
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,filterConfig.getServletContext());

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("filter is start");
        try {
            ReturnModel returnModel = CreateTokenUtils.getUserInfoByRequest((HttpServletRequest)servletRequest,audience.getBase64Secret());
            if(returnModel.getCode() != 0){
                HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.setContentType("application/json; charset=utf-8");
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                ObjectMapper mapper = new ObjectMapper();
                httpServletResponse.getWriter().write(mapper.writeValueAsString(returnModel));
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
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
