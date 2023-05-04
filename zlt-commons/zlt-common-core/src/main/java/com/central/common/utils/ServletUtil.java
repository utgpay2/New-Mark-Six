package com.central.common.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ServletUtil {

	/**
	 * 获取当前请求request
	 *
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		if (null == requestAttributes) {
			return null;
		}
		return requestAttributes.getRequest();
	}

	/**
	 * 获取当前请求session
	 *
	 * @return
	 */
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 获取当前请求response * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		return ( ( ServletRequestAttributes ) RequestContextHolder
				.getRequestAttributes() )
				.getResponse();
	}

	/**
	 * 获取String参数
	 */
	public static String getParameter( String name ) {
		return getHttpServletRequest().getParameter( name );
	}

	/**
	 * 获取String参数
	 */
	public static String getParameter( String name, String defaultValue ) {
		return com.central.common.utils.Convert.toStr( getHttpServletRequest().getParameter( name ), defaultValue );
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt( String name ) {
		return com.central.common.utils.Convert.toInt( getHttpServletRequest().getParameter( name ) );
	}

	/**
	 * 获取Integer参数
	 */
	public static Integer getParameterToInt( String name, Integer defaultValue ) {
		return com.central.common.utils.Convert.toInt( getHttpServletRequest().getParameter( name ), defaultValue );
	}

	/**
	 * 将字符串渲染到客户端
	 *
	 * @param response 渲染对象
	 * @param string   待渲染的字符串
	 * @return null
	 */
	public static String renderString( HttpServletResponse response, String string ) {
		try {
			response.setStatus( 200 );
			response.setContentType( "application/json" );
			response.setCharacterEncoding( "utf-8" );
			response.getWriter().print( string );
		} catch ( IOException e ) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 是否是Ajax异步请求
	 */
	public static boolean isAjaxRequest( HttpServletRequest request ) {
		String accept = request.getHeader( "accept" );
		if ( accept != null && accept.indexOf( "application/json" ) != -1 ) {
			return true;
		}
		String xRequestedWith = request.getHeader( "X-Requested-With" );
		if ( xRequestedWith != null && xRequestedWith.indexOf( "XMLHttpRequest" ) != -1 ) {
			return true;
		}
		String uri = request.getRequestURI();
		if ( com.central.common.utils.StringUtils.inStringIgnoreCase( uri, ".json", ".xml" ) ) {
			return true;
		}
		String ajax = request.getParameter( "__ajax" );
		if ( com.central.common.utils.StringUtils.inStringIgnoreCase( ajax, "json", "xml" ) ) {
			return true;
		}
		return false;
	}
}
