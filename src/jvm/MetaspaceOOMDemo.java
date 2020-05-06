package jvm;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


import java.lang.reflect.Method;

/**
 * @author ：xbb
 * @date ：Created in 2020/5/6 2:27 下午
 * @description：元空间（方法区MA）溢出
 * @modifiedBy：
 * @version:
 */
public class MetaspaceOOMDemo {
    // -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
    static class OOMTest {

    }

    public static void main(String[] args) {
        int i = 0;
        try {
            while(true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        } finally {
        }

    }
}
