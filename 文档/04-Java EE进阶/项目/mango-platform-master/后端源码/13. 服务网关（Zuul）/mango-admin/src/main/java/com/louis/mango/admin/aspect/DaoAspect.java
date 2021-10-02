package com.louis.mango.admin.aspect;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.louis.mango.admin.util.SecurityUtils;
import com.louis.mango.common.utils.StringUtils;

/**
 * DAO切面，插入创建人，创建时间，修改人，修改时间
 * @author Louis
 * @date Oct 29, 2018
 */
@Aspect
@Component
@Configuration
public class DaoAspect {
	private static final String createBy = "createBy";
	private static final String createTime = "createTime";
	private static final String lastUpdateBy = "lastUpdateBy";
	private static final String lastUpdateTime = "lastUpdateTime";

	@Pointcut("execution(* com.louis.mango.*.dao.*.update*(..))")
	public void daoUpdate() {
	}

	@Pointcut("execution(* com.louis.mango.*.dao.*.insert*(..))")
	public void daoCreate() {
	}

	@Around("daoUpdate()")
	public Object doAroundUpdate(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return pjp.proceed();
		}
		HttpServletRequest request = attributes.getRequest();
		String token = request.getHeader("token");
		String username = getUserName();
		if (token != null && username != null) {
			Object[] objects = pjp.getArgs();
			if (objects != null && objects.length > 0) {
				for (Object arg : objects) {
					BeanUtils.setProperty(arg, lastUpdateBy, username);
					BeanUtils.setProperty(arg, lastUpdateTime, new Date());
				}
			}
		}
		Object object = pjp.proceed();
		return object;

	}

	@Around("daoCreate()")
	public Object doAroundCreate(ProceedingJoinPoint pjp) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if (attributes == null) {
			return pjp.proceed();
		}
		Object[] objects = pjp.getArgs();
		if (objects != null && objects.length > 0) {
			for (Object arg : objects) {
				String username = getUserName();
				if (username != null) {
					if (StringUtils.isBlank(BeanUtils.getProperty(arg, createBy))) {
						BeanUtils.setProperty(arg, createBy, username);
					}
					if (StringUtils.isBlank(BeanUtils.getProperty(arg, createTime))) {
						BeanUtils.setProperty(arg, createTime, new Date());
					}
				}
			}
		}
		Object object = pjp.proceed();
		return object;
	}

	private String getUserName() {
		return SecurityUtils.getUsername();
	}
}
