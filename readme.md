### 总结

#### Thread的start方法总结

1. java应用程序的main函数是一个线程，是被JVM启动时侯调用，线程名字叫main
2. 实现一个线程，必须创建Thread实例，重写run方法，并且调用start方法
3. 在JVM启动后，实际上有多个线程，但是至少有一个非守护线程
4. 当调用一个线程start方法时，此时至少有两个线程，一个是调用你的线程，还有一个执行run方法的线程
5. 线程的生命周期分为new，runnable，running，blocked，waiting，terminated（此处可以参照操作系统中的线程与进程状态的转变）

#### 线程构造函数

1. 创建线程对象Thread，默认有一个线程名，以Thread-开头，从0开始计数 ==>构造函数 new Thread()

---

2. 如果在创建Thread时没有传递Runnable接口的实例或者没有重写Thread的run方法，该实例将不会调用任何东西；
   如果在创建Thread时传递了Runnable接口的实例或者重写了Thread的run方法，则会执行run方法的逻辑单元（逻辑代码） ==>构造函数 new Thread(Runnable
   target)

---

3. 如果构造线程对象时，未传入ThreadGroup，Thread会默认获取父线程的ThreadGroup作为该线程的ThreadGroup， 此时子线程和父线程将会在同一个ThreadGroup中
   ##### Thread(ThreadGroup group, Runnable target)
   ##### Thread(ThreadGroup group, Runnable target, String name)

---

4. 构造Thread的时候，传入stackSize代表着该线程占用的stack的大小； 如果没有指出stickSize的大小，默认是0，0代表着会忽略该参数，该参数会被JNI函数去使用。
   需要注意：该参数在一些平台有效，在有些平台则无效
   ##### Thread(ThreadGroup group, Runnable target, String name,long stackSize)
   since 9
   ##### Thread(ThreadGroup group, Runnable target, String name,long stackSize, boolean inheritThreadLocals)

---

5. 守护线程 (Daemon Thread)的创建以及使用场景
   ###### 将线程转换为守护线程可以通过调用Thread对象的setDaemon(true)方法来实现
   ###### thread.setDaemon(true)必须在thread.start()之前设置，否则会跑出一个IllegalThreadStateException异常。你不能把正在运行的常规线程设置为守护线程
        一个实际要使用daemon线程的场景：客户端与服务器端建立一个TCP的长链接，
       然后当连接建立之后就创建一个线程来给服务器发送心跳包以便服务器能监听客户端的网络状态，
       这时如果连接断开了那这个心跳线程也得跟着断开

---

#### synchronized相关

6. synchronized 常见的面试题

###### ①构造方法可以用synchronized关键字修饰吗？

答：不能，也不需要，因为构造方法本身就是线程安全的

###### ② synchronized 和 volatile 的区别

synchronized 关键字和 volatile 关键字是两个互补的存在，而不是对立的存在

1. volatile 关键字是线程同步的轻量级实现，所以 volatile性能肯定比synchronized关键字要好 。但是 volatile 关键字只能用于变量而 synchronized
   关键字可以修饰方法以及代码块 。
2. volatile 关键字能保证数据的可见性，但不能保证数据的原子性。synchronized 关键字两者都能保证。
3. volatile关键字主要用于解决变量在多个线程之间的可见性，而 synchronized 关键字解决的是多个线程之间访问资源的同步性。
4. volatile只能修饰实例变量和类变量，而synchronized可以修饰方法，以及代码块。

---

7. sleep()和wait()的区别
    1. sleep()是Thread类中的静态方法；wait()是Object类中的方法
    2. sleep()不会释放锁；wait()会释放这个锁并将它添加到就绪队列中
    3. sleep()方法不需要定义synchronized同步方法；wait()方法需要定义synchronized同步方法
    4. sleep()方法不需要被唤醒；wait()方法需要被唤醒。唤醒通过notify()或者notifyAll()方法


8. 如何解决高速缓存不一致的问题

   <p>解决方法：</p>
   <p>方法一： 给数据总线加锁。 注：总线（数据总线、地址总线、控制总线）</p>
   <div>
   方法二：使用CPU高速缓存一致性协议
   <p>
   核心思想：
   </p>
   <p>
   1. 当CPU写入数据的时候，如果发现该变量被共享（该变量在其他CPU中也存在副本），则会发出一个信号，通知其他CPU该变量的缓存无效
   </p>
   <p>
   2. 当其他CPU访问该变量的时候，重新到主存当中读取
   </p>
   </div>

--- 

### 原子性、可见性、有序性(AOV)

9. Java并发编程之原子性、可见性、有序性
    1. 原子性：一个操作或者多个操作要全部执行并且不能由于任何原因被中断，要么都不执行。类似于操作系统中的原语指令
    2. 可见性：当多个线程访问同一个共享变量时，一个线程修改了这个共享变量的值，其他线程能够立即看到修改后的值
    3. 有序性（顺序性）：程序的执行顺序安装代码顺序执行
    4. 指令重排序：CPU为了提高程序运行的效率，可能会对代码进行优化，它不保证语句执行执行先后顺序同代码中顺序一致，但是它会保证最终执行结果和代码顺序执行的结果是一致的。
       指令重排序不会影响单个线程的执行，但是会影响到线程并发执行的正确性
       <br/>
       总结：程序并发执行时，必须要保证同时满足原子性、可见性和有序性。只要有一个未被满足，就可能导致程序运行错误。

---   

10. Java内存模型中的原子性、可见性和有序性
    <br/>
    Java内存模型规定所有的变量都是存储在主存当中，每个线程都有自己的工作内存。线程对变量的所有操作都必须在工作内存中进行，而不能直接对主存中的变量进行操作。
    并且每个线程不能访问其他线程的工作内存。Java内存模型只保证了基本读取操作和赋值是原子性操作， 如果需要实现更大范围操作的原子性，可以通过synchronized和Lock来实现。
    由于synchronized和Lock能够保证任意时刻只有一个线程执行该代码块，可以保证原子性。
    <br/>
    1. 原子性：对基本数据类型的读取和赋值操作都是原子性操作，即这些操作是不可被中断的，要么执行，要么不执行。
    2. 可见性：使用volatile关键字保证可见性；但是volatile不能保证原子性。
    3. 有序性 happens-before原则
        1. 单线程内，代码的执行顺序，书写在前面的操作先行发生于书写在后面的操作
        2. unlock操作必须发生在lock操作之后
        3. volatile所修饰的变量，对一个变量的写操作，先于对该变量的读操作
        4. 传递规则：操作A先于B，B先于C，那么A肯定先于C
        5. 线程启动规则：start方法肯定先于线程的run方法
        6. 线程中断规则：interrupt操作，必须发生在捕获该操作之前
        7. 对象销毁规则：一个对象的初始化必须发生在finalize之前
        8. 线程终结规则：线程中所有的操作都先于发生于线程的终止检测，可以通过Thread.join()方法结束，Thread.isAlive()方法返回值的手段检测到线程以及终止不再执行

---

11. volatile关键字
    <br/>
    一旦一个共享变量被volatile关键字修饰，就具备以下两层含义:
    1.保证了不同线程之间共享变量的可见性，不保证原子性
    <br/>
    2.禁止对其进行重排序，也就是保证了有序性
    <br/>
    3. 保证重排序的时候不会把后面的指令放到屏障的前面，也不会把前面的放到后面
    4. 强制对缓存的修改操作立即写入内存
    5. 如果是写操作，他会导致其他CPU中的缓存失效

12. volatile的使用场景
    1. 状态量标记
       ` volatile boolean flag = true; while(flag){ // } void close(){ flag = false; }`
    2. 屏障前后的一致性

    
