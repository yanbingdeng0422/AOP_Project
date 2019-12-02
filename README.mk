# 无痕埋点方案-Aspectj(二)



标签： AOP 无痕埋点Aspectj

---
##切点表达式
切点表达式
```
execution(* android.view.View.OnClickListener.onClick(android.view.View))
```
上面就是一个切入点表达式的示例。

一个完整的切入点表达式包括如下几部分：

**execution（<修饰符表达式>?<返回类型模式><方法名模式>(<参数模式>)<异常模式>?）**

其中：

 - 带?的表示这部分是可选的；
 - 修饰符模式指的是public、private、protected等
 - 异常模式指的是如ClassNotFoundException异常等

**Sample 1:**
```
    @After("execution(public * *(..))")
    public void onViewClickAop_After(JoinPoint joinPoint){
        View view = (View) joinPoint.getArgs()[0];
        Log.d(TAG,"After viwe="+view.toString());
    }
```
该切点将会匹配所有修饰符是public的方法，并在方法执行之后打印After viwe日志信息

**Sample 2:**
```
    @Around("execution(* on*(..))")
    public Object weaveAllMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.d(TAG,"MainActivity some Before");
        Object returnObject = joinPoint.proceed();
        Log.d(TAG,"MainActivity something after");
        
        return returnObject;
    }
```

该切入点将会匹配所有方法名以"on"开头的方法，并在方法执行之前打印"MainActivity some Before",在方法执行之后打印"MainActivity something afte";

**Sample 3:**
```
    @Before("execution(* android.view.View..*Click(..))")
    public void onViewClickAop(JoinPoint joinPoint){
        Log.d(TAG,"Before viwe");
    }
```
该切入点将会匹配android.view.View包及其子类中所有方法名以"Click"结尾的方法，并在方法执行之前打印"Before viwe"

**Sample 4:**
```
    @AfterReturning("execution(String android.view.View.*(..))")
    public void onViewClickAOP_AfterReturning(final JoinPoint joinPoint){
        Log.d(TAG,"MainActivity Something Done ");
    }
```
该切点将会匹配android.view.View包及其子类所有方法返回类型是String的方法，并在方法返回结果之后打印当前方法的返回值。

**Sample 5:**
```
    @After("execution(* android.view.View.OnClickListener.onClick(android.view.View))")
    public void onViewClickAop_After(JoinPoint joinPoint){
        View view = (View) joinPoint.getArgs()[0];
        Log.d(TAG,"After viwe="+view.toString());
    }
```
该切点将会匹配所有的android.view.View.OnClickListener.onClick(android.view.View)方法；


##Call与execution区别
在切入点的表达式中，我们有时还能见到call表达式，比如：
```
call(* android.view.View.OnClickListener.onClick(android.view.View))
```
call和exection区别：当call捕获joinPoint时，捕获的签名方法的**调用点**，而excution捕获joinPoint时，捕获的则是方法的**执行点**；

call是调用点：
```
call(Before)
Pointcut{
    Pointcut Method
}
call(After)
```

execution是执行点：
```
Pointcut{
    execution(Before)
    Pointcut Method
    execution(After)
}
```
用一个实例来说明俩着差别：
```
public class MainActivity extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.bottom);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(MainActivity.class.getSimpleName(),"button click");
            }
        });

        MYTest();
    }

    private void MYTest() {
        Log.d(MainActivity.class.getSimpleName(),"MYTest");
    }
}
```
execution表达式：
```
    @After("execution(* com.yanbing.aop_project.MainActivity.MYTest(..))")
    public void MYTestAop_After(JoinPoint joinPoint){
        Log.d(TAG,"MYTestAop_After");
    }
```

编译之后的结果
```
    private void MYTest() {
        JoinPoint var1 = Factory.makeJP(ajc$tjp_1, this, this);

        try {
            MYTest_aroundBody3$advice(this, var1, SensorsDataAspectJ.aspectOf(), (ProceedingJoinPoint)var1);
        } catch (Throwable var3) {
            SensorsDataAspectJ.aspectOf().MYTestAop_After(var1);
            throw var3;
        }

        SensorsDataAspectJ.aspectOf().MYTestAop_After(var1);
    }
```

call表达式
```
    @After("call(* com.yanbing.aop_project.MainActivity.MYTest(..))")
    public void MYTestAop_After_call(JoinPoint joinPoint){
        Log.d(TAG,"MYTestAop_After_call");
    }
```
编译之后的结果
```
    protected void onCreate(Bundle savedInstanceState) {
        JoinPoint var6 = Factory.makeJP(ajc$tjp_1, this, this, savedInstanceState);
        onCreate_aroundBody1$advice(this, savedInstanceState, var6, SensorsDataAspectJ.aspectOf(), (ProceedingJoinPoint)var6);
    }

    private void MYTest() {
        JoinPoint var1 = Factory.makeJP(ajc$tjp_2, this, this);
        MYTest_aroundBody3$advice(this, var1, SensorsDataAspectJ.aspectOf(), (ProceedingJoinPoint)var1);
    }
```
