package com.yanbing.aop_project;


import android.util.Log;
import android.view.View;

import androidx.annotation.Keep;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Locale;

@Aspect
@Keep
@SuppressWarnings("all")
public class SensorsDataAspectJ {

    private static final String TAG = "SensorsDataAspectJ";

//    @Around("execution(* *(..))")
//    public Object weaveAllMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        long startNanoTime = System.nanoTime();
//        Log.d(TAG,"MainActivity some Before");
//
//        Object returnObject = joinPoint.proceed();
//        Log.d(TAG,"MainActivity something after");
//        //纳秒，1毫秒=1纳秒*1000*1000
//        long stopNanoTime = System.nanoTime();
//
//        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Method method = signature.getMethod();
//
//        Log.i(TAG, String.format(Locale.CHINA, "Method:<%s> cost=%s ns", method.toGenericString(), String.valueOf(stopNanoTime - startNanoTime)));
//
//        return returnObject;
//    }

//    @Before("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
//    public void onViewClickAop(JoinPoint joinPoint){
//        View view = (View) joinPoint.getArgs()[0];
//        Log.d(TAG,"Before viwe="+view.toString());
//    }
//
//    @After("execution(* com.yanbing.aop_project.MainActivity.MYTest(..))")
//    public void MYTestAop_After(JoinPoint joinPoint){
//        Log.d(TAG,"MYTestAop_After");
//    }
//
//    @After("call(* com.yanbing.aop_project.MainActivity.MYTest(..))")
//    public void MYTestAop_After_call(JoinPoint joinPoint){
//        Log.d(TAG,"MYTestAop_After_call");
//    }
//
//    @After("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
//    public void onViewClickAop_After(JoinPoint joinPoint){
//        View view = (View) joinPoint.getArgs()[0];
//        Log.d(TAG,"After viwe="+view.toString());
//    }
//
////toString//    @Around("execution(* android.support.v4.app.Fragment.OnCreateViewe(..))")
////    publ  ic Object fragmentOnCreateViewMethod_Around(ProceedingJoinPoint joinPoint) throws Throwable{
////        Log.d(TAG,"MainActivity some Before");
////        Object result = joinPoint.proceed();
////        Log.d(TAG,"something after");
////        return result;
////    }
//
//    @AfterReturning("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
//    public void onViewClickAOP_AfterReturning(final JoinPoint joinPoint){
//        Log.d(TAG,"MainActivity Something Done ");
//    }
//
//
//    @AfterThrowing("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
//    public void onClickAOP_AfterThrowing(final JoinPoint joinPoint){
//        Log.d(TAG,"onClickAOP_AfterThrowing Something error");
//    }

//    @Around("execution(* android.app.Activity.*(..))")

//    @Around("execution(* android.app.Activity.*(..))")
//    public void getSetContentViewTime(ProceedingJoinPoint joinPoint) {
//        Signature signature = joinPoint.getSignature();
//        String name = signature.toShortString();
//        long time = System.currentTimeMillis();
//        try {
//            joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        Log.i(TAG,name+" cost " + (System.currentTimeMillis() - time));
//    }

//    @Around("execution(* android.app.Activity.setContentView(..))")
//    public void getContentViewTime(ProceedingJoinPoint point) throws Throwable {
//        String name = point.getSignature().toShortString();
//        long time = System.currentTimeMillis();
//        point.proceed();
//        Log.e(TAG, name + " cost: " + (System.currentTimeMillis() - time));
//    }

    @Around("execution(* com.yanbing.aop_project.MainActivity.MYTest(..))")
    public void getContentViewTime(ProceedingJoinPoint point) throws Throwable {
        String name = point.getSignature().toShortString();
        long time = System.currentTimeMillis();
        point.proceed();
        Log.e(TAG, name + " cost: " + (System.currentTimeMillis() - time));
    }
}
