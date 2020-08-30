##### 这里记录一些代码过程中总结的知识点
---

> 1,HTML页面放在static和templates文件下效果是不一样的。
  放在static下，既可以通过后台访问页面也可以直接通过前端访问页面；而在templates下，只可以通过后台
，不能直接通过浏览器访问到。

> 2.关于异常：Exception和RuntimeException.  
&nbsp;（1）Exception，代表了可检查异常，抛出此类异常时，要么在方法头部捕获抛出，要么try/catch，就是必须要捕获。
&nbsp;（2）RuntimeException，代表了不可检查异常，不用去捕获。

> 3.异常代码编写原则：  
（1）尽量不要抛出Exception这样的通用异常，而应该抛出他的子类。也就是更加具体的异常，这样方便我们排查异常。
（2）不要生吞异常（对异常不捕获也不抛出）：生吞异常导致程序将异常悄无声息的消化掉，这样无法判断异常产生的原因。

> 4.Mybaits Plus 不会读取XML文件，所以在配置中要加:mapper-locations: com/gosang/mapper/xml/*.xml,
>这些sql文件才能加载到程序中