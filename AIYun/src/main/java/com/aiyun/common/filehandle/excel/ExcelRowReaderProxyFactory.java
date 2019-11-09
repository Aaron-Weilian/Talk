/**
 * 
 */
package com.aiyun.common.filehandle.excel;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * @author ZT
 *
 */
public class ExcelRowReaderProxyFactory<T> {

    private Class<T> clazz;
    
    public ExcelRowReaderProxyFactory(Class<T> obj) {
        this.clazz = obj ;
    }
    
    public T newInstance() {
        
        T iInstance = null;
        Enhancer enHancer = new Enhancer();//创建增强对象
        
        //设置要增强的目标类，指定其为父类，下面还是利用多态原理
        enHancer.setSuperclass(this.clazz);
        
        //设置回调方法，通过继承MethodInterceptor接口，实现方法的拦截
        enHancer.setCallback(new MethodInterceptor() {
            public Object intercept(Object obj, Method method, Object[] params,  MethodProxy proxy) throws Throwable {
                //System.out.println("...before enHance...");//前增强
                Object result = proxy.invokeSuper(obj, params);//目标方法
                //System.out.println("...after enHance...");//后增强
                return result;//目标方法返回结果
            }
        });
        
        iInstance = (T)enHancer.create();
        if (iInstance == null) {
            throw new RuntimeException("proxy对象创建失败，请重试...");
        }
        return iInstance;
 
    }
    
    
}
