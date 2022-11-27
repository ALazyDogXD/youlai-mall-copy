//package com.alazydogxd.youlai.auth.boot.aspect;
//
//import com.alazydogxd.youlai.copy.common.base.resp.ResponseData;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @author ALazyDogXD
// * @date 2022/6/7 21:32
// * @description token endpoint 切面
// */
//
//@Aspect
//@Component
//class TokenEndpointAspect {
//
//    @Pointcut("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.getAccessToken(..))")
//    public void pointcut() {
//    }
//
////    @Pointcut("execution(* com.alazydogxd.youlai.auth.boot.web.AuthController.postAccessToken(..))")
////    public void pointcut() {
////    }
//
//    @Around("pointcut()")
//    public Object getAccessToken(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        proceedingJoinPoint.proceed();
//        Map<String, String> result = new HashMap<>(16);
//        result.put("access_token", "access_token");
//        result.put("token_type", "token_type");
//        return ResponseData.success(result);
//    }
//
//}
