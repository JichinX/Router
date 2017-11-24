###注解笔记
###1.元注解
   元注解是用来实现自定义注解时 对注解进行修饰   
   有四个元注解：
   >1.@Target      
    2.@Retention   
    3.@Documented   
    4.@Inherited 
   
  1)@Target 修饰注解的适用范围
  >1.CONSTRUCTOR:用于描述构造器     
   2.FIELD:用于描述域     
   3.LOCAL_VARIABLE:用于描述局部变量        
   4.METHOD:用于描述方法   
   5.PACKAGE:用于描述包   
   6.PARAMETER:用于描述参数
   7.TYPE:用于描述类、接口(包括注解类型) 或enum声明
  
  2)@Retention 修饰注解的存活周期
  >1.SOURCE:在源文件中有效（即源文件保留） 
   2.CLASS:在class文件中有效（即class保留）    
   3.RUNTIME:在运行时有效（即运行时保留）
 
 3)@Documented 修饰的注解 可以被JavaDoc工具识别
 
 4）@Inherited 是一个标记注解，@Inherited阐述了某个被标注的类型是被继承的。如果一个使用了@Inherited修饰的annotation类型被用于一个class，则这个annotation将被用于该class的子类。