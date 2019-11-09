/**
 * 
 */
package com.aiyun.common.filehandle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ZT
 *
 */
public class ProxyFactory {

    private Object targetObject;
    
    
    public Object getProxy() {
        
        ClassLoader loader = this.targetObject.getClass().getClassLoader();

        Class<?>[] interfaces = this.targetObject.getClass().getInterfaces();
        
        InvocationHandler handler = new InvocationHandler() {
            
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
            {
                System.out.println("在调用之前，我要干点啥呢？");
         
                System.out.println("Method:" + method);
         
                Object returnValue = method.invoke(targetObject, args);
         
                System.out.println("在调用之后，我要干点啥呢？");
         
                return returnValue;
            }
        };
        
        return Proxy.newProxyInstance(loader, interfaces, handler);
    }
    
    
    

    public void setTargetObject(Object targetObject) {

        this.targetObject = targetObject;

    }
    
}
