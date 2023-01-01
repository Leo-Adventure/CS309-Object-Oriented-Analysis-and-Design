# OOAD 期末复习

> Author: Leo-Adventure
>
> Duration: Dec, 2022 - Jan, 2023

[TOC]

## UML 

![UML.png](https://s2.loli.net/2023/01/02/5YZR6GEnudgACeI.png)

## 设计模式(Design Pattern)

**GoF( *Erich Gamma, Richard Helm, Ralph Johnson, and John Vlissides*)**

### 简单工厂模式(Simple Factory Pattern)

![Screen Shot 2023-01-01 at 23.47.08.png](https://s2.loli.net/2023/01/01/2fsc4WAdU3jCoqD.png)

优点：

- 客户端免除了创建对象的责任，实现了对责任的分割
- 减少使用者的记忆量

缺点：

- 工厂类责任过大
- 增加类的个数，增加系统复杂度

### 工厂方法模式(Factory Method Pattern)

![Screen Shot 2023-01-01 at 23.52.51.png](https://s2.loli.net/2023/01/01/ImRZJEXwbOeGczB.png)

优点：

- 符合“开闭原则”，在增加新产品的时候无需修改原有代码
- 在工厂方法模式中，工厂方法用来创建客户所需要的产品，同时还向客户隐藏了哪种具体产品类将被实例化这一细节，用户只需要关心所需产品对应的工厂，无须关心创建细节，甚至无须知道具体产品类的类名。

缺点：

- 在添加新产品时，需要编写新的具体产品类，而且还要提供与之对应的具体工厂类，系统中类的个数将成对增加，在一定程度上增加了系统的复杂度

### 抽象工厂模式(Abstract Factory Pattern)

![Screen Shot 2023-01-01 at 23.56.02.png](https://s2.loli.net/2023/01/01/7uCyidGVZKLkmza.png)

优点：

- 增加新的具体工厂和产品族非常方便，不需要修改原有代码，符合“开闭原则”
- 高内聚低耦合

缺点：

- 添加新的产品对象时要修改工厂代码以及产品代码，不方便

### 单例模式(Singleton Pattern)

![Screen Shot 2023-01-01 at 23.58.54.png](https://s2.loli.net/2023/01/01/eOrBbsQqgUza9k8.png)

优点：

- 提供了对唯一实例的受控访问。
- 允许可变条数的实例

缺点：

- 单例类的职责过重
- 没有抽象层，扩展困难

注意饿汉式和懒汉式的区别：饿汉式是在成员变量刚开始的时候就 new，需要的时候直接进行 return，懒汉式则是成员变量一开始不进行 new，在有需要的时候再进行 new 和 return。

### 观察者模式(Observer Pattern)

![Screen Shot 2023-01-02 at 00.07.00.png](https://s2.loli.net/2023/01/02/N2SopdmbE3VX9gz.png)

优点：

- 观察者模式在观察目标和观察者之间建立一个抽象的耦合。
- 符合“开闭原则”

缺点：

- 如果一个观察目标对象有很多观察者的话，通知所有的观察者需要花费很多时间
- 如果观察者和观察目标之间有循环依赖的话，将会无限循环

### 桥接模式(Bridge Pattern)

![Screen Shot 2023-01-02 at 00.10.32.png](https://s2.loli.net/2023/01/02/ns5FIbuDrwfmig4.png)

优点：

- 桥接模式提高了系统的可扩充性，在两个变化维度中任意扩展一个维度，都不需要修改原有系统。

缺点：

- 增加系统负责度和理解难度

### 策略模式(Strategy Pattern)

![Screen Shot 2023-01-02 at 00.13.18.png](https://s2.loli.net/2023/01/02/Vj3vr7FCWoxgHBK.png)

优点：

- 符合“开闭原则”，可以在不修改原有系统的基础上增加算法

缺点：

- 客户端需要记住所有策略类，增加记忆量

### 命令模式(Command Pattern)

![Screen Shot 2023-01-02 at 00.18.47.png](https://s2.loli.net/2023/01/02/XWVySnLT7skG19z.png)

优点：

- 降低系统的耦合度
- 符合开闭原则，新的命令很容易加到系统当中

缺点：

- 可能会导致系统有过多具体命令类

### 享元模式(Flyweight Pattern)

![Screen Shot 2023-01-02 at 00.22.53.png](https://s2.loli.net/2023/01/02/YR9bOSUiokfZqWQ.png)

优点：

- 极大地减少内存中对象的数量

缺点：

- 使得系统更加复杂

### 外观模式(Facade Pattern)

![Screen Shot 2023-01-02 at 00.27.58.png](https://s2.loli.net/2023/01/02/kN2U7yFmpXWiJVa.png)

优点：

- 减少客户处理的对象数目，使得子系统使用起来更加容易
- 实现了子系统与客户的松耦合关系

缺点：

- 违背了“开闭原则”，增加子系统可能需要修改外观类或者客户端的源代码

### 适配器模式(Adapter Pattern)

![Screen Shot 2023-01-02 at 00.31.09.png](https://s2.loli.net/2023/01/02/Syi2UPHceaVDO5x.png)

优点：

- 将目标类与适配者类解耦，在不修改原有代码的情况下通过引入适配器类来重用适配者类
- 灵活性和扩展性都非常好

缺点：

- 一些语言不支持多重继承，一次最多只能匹配一个适配器类，使用具有一定局限性

## 重构(Refactoring)

### Code Smells

#### 重复代码(Duplicated Code)

- 同类相同代码 $\Rightarrow$ 提炼方法(==Extract Method==)
- 互为兄弟类相同代码 $\Rightarrow$ 将相同方法推入超类
- 不相关类的重复代码 $\Rightarrow$ 对其中一个类重复代码提炼到一个独立类当中，在另一个类使用该类

#### 过长函数(Long Method)

- 积极分解函数(==Extract Method==)，使用更加直观的名字
- ==Replace Temp with Query==：将局部变量的获取方式写成方法，直接进行更加直观的调用
- ==Introduce Parameter Object==：将多个形参的传入封装成一个类，进行统一传入
- ==Decompose Conditional==：将条件表达式的判断以及执行语句替换成更加直观的方法进行调用

#### 过大的类(Large Class)

- 提取子类(==Extract Subclass==)
- ==Extract Class==
- 按照客户端的使用方式，提取接口(==Extract Interface==)

#### 过长参数列表(Long Parameter List)

- ==Replace Parameter with Method==: 将可以在函数内部通过方法求得的参数，不进行传入
-  ==Preserve Whole Object==：将同一对象的所有属性收集起来，以对象的方式传入
-  ==Introduce Parameter Object==：将一部分参数整合成为一个 Object 进行传入

#### **发散式变化(Divergent Change)**

- 定义：某个类由于职责过多，经常因为不同的原因在不同的方向上发生变化
- 应该找出某特定原因而造成的所有变化，然后运用 ==Extract Class== 将一个大类提炼到另一个小类中

#### **霰弹式修改(Shotgun Surgery)**

定义：如果每遇到某种变化，你都必须在许多不同的类内做出许多小修改，会导致有些忘记修改

- ==Move Method, Move Field==: 把所有需要修改的代码放进同一个类
-  ==Inline Class==: 把一系列相关行为放进同一个类。 

**Divergent Change 是指“一个类受多种变化的影响”，Shotgun Surgery 则是值“一种变化引发多个类相应修改”**

#### 依恋情结(Feature Envy)

定义： 函数对某个类的兴趣高过对自己所处类的兴趣，即该函数对某个类数据的引用次数高于自己所在的类的数据的引用次数。

- 先使用 ==Extract Method== 将这部分提炼成为独立的函数，再将该部分使用 ==Move Method== 移动到应该处于的函数当中。

#### 数据泥团(Data Clumps)

定义：多个数据总是一起出现

- 使用 ==Extract Class== 将这些对象作为一个类的成员变量

- 使用 ==Introduce Parameter Object== 或者 ==Preserve Whole Object== 减少函数的传参

#### 基本类型偏执(Primitive Obsession)

- 运用 ==Replace Data Value with Object==将原本单独存在的数据替换为对象
- 如果想要替换的数据值是类型码，而它并不影响行为，则可以运用 ==Replace Type Code with Class== 将它换掉
- 如果需要替换的数据值是函数参数，可以使用 ==Introduce Parameter Object== 或者 ==Preserve Whole Object== 来进行替换
- 如果你有类型码相关的条件表达式，可以运用 Replace Type Code with Subclass(213)或 ==Replace Type Code with State/Strategy==(227)加以处理

#### switch 惊悚现身(Switch Statements)

- ==Extract Method, Move Method==
- 使用多态就需要 ==Replace Type Code with Subclasses==或者 Replace Type Code with Strategy

#### 冗余元素(Lazy Element)

定义：一个类的内容很少，冗余

- 使用 ==Collapse Hierarchy== 或者 ==Inline Class==，将继承层次结构压缩

#### 夸夸其谈通用性(Speculative Generality)

定义：不必要的抽象类或者处理不需要处理的特殊情况

- 使用 ==Collapse Hierarchy== 或者 ==Inline Class== 压缩层次结构
- 对于不必要的参数，使用 ==Remove Parameter== 或者 Rename Method 进行解决

#### 临时字段(Temporary Field)

定义：一些类的内部某个字段仅为某种特定情况而设，其余情况作用不大，且不易理解

- 使用 ==Extract Class== 将这样的字段提取成一个类
- 使用 Introduce Null Object 或者 ==Introduce Special Case== 将一些特殊情况作为一个类，和 Extract Class 类似

#### 过长的消息链(Message Chains)

定义：$a\rightarrow b()\rightarrow c()\rightarrow d()$

- 通过 ==Hide Delegate==， ==Extract Method==， Move Method 将中间的调用封装成函数，再将该函数推回消息链

#### 中间人(Middle Man)

定义：委托过多

- ==Remove Middle Man==

#### 不合适的亲昵(Inappropriate Intimacy)

定义：类之间联系太多

- Move Method, Move Field. 将一个类被其他类需要的一些方法移动到其他类当中，但是确保这些方法或成员变量不被这个类依赖
- Extract Class, Hide Delegate
-  ==Change Bidirectional Association to Unidirectional==

#### 异曲同工的类(Alternative Classes with Different Interfaces)

- ==Extract Superclass==, Unify Interfaces with adapter
- ==Rename Method==
- Move Method

#### 被拒绝的遗赠(Refused Bequest)

定义: 子类与父类没啥关系，但是却要继承所有的方法

- Push Down Field, Push Down Method, ==Replace Inheritance With Delegation==
- ==Extract Superclass==

### 重构方法

#### Extract Method

```java
void printOwing() {
  printBanner();

  // Print details.
  System.out.println("name: " + name);
  System.out.println("amount: " + getOutstanding());
}
```

$$
\Downarrow
$$

```java
void printOwing() {
  printBanner();
  printDetails(getOutstanding());
}

void printDetails(double outstanding) {
  System.out.println("name: " + name);
  System.out.println("amount: " + outstanding);
}
```



#### Introduce Parameter Object

```java
function amountInvoiced(startDate, endDate) {...}
function amountReceived(startDate, endDate) {...}
function amountOverdue(startDate, endDate) {...}
```

$$
\Downarrow
$$

```java
function amountInvoiced(aDateRange) {...}
function amountReceived(aDateRange) {...}
function amountOverdue(aDateRange) {...}
```



#### Extract Interface

![Screen Shot 2023-01-01 at 09.36.20.png](https://s2.loli.net/2023/01/01/bFpEAoi67wyOuqh.png)

#### Preserve Whole Object

```java
int low = daysTempRange.getLow();
int high = daysTempRange.getHigh();
boolean withinPlan = plan.withinRange(low, high);
```

$$
\Downarrow
$$

```java
boolean withinPlan = plan.withinRange(daysTempRange);
```

#### Replace Parameter with Method

```java
int basePrice = quantity * itemPrice;
double seasonDiscount = this.getSeasonalDiscount();
double fees = this.getFees();
double finalPrice = discountedPrice(basePrice, seasonDiscount, fees);
```

$$
\Downarrow
$$

```java
int basePrice = quantity * itemPrice;
double finalPrice = discountedPrice(basePrice);
```

#### Replace Data Value with Object

![Screen Shot 2023-01-01 at 10.10.43.png](https://s2.loli.net/2023/01/01/mpyIwOadKctSjvM.png)

#### Replace Type Code with Class

![Screen Shot 2023-01-01 at 10.16.14.png](https://s2.loli.net/2023/01/01/Qb3i9osmh275RUX.png)

#### Replace Type Code with State/Strategy

![Screen Shot 2023-01-01 at 10.17.53.png](https://s2.loli.net/2023/01/01/KLOoQ3Vkmab98AI.png)

#### Collapse Hierarchy

![Screen Shot 2023-01-01 at 10.32.26.png](https://s2.loli.net/2023/01/01/6KPGAlCvrVUSIiX.png)

#### Hide Delegate

```java
manager = aPerson.department.manager;
```

$$
\Downarrow
$$

```java
manager = aPerson.manager;

class Person {
  get manager() {return this.department.manager;
}
```

#### Change Bidirectional Association to Unidirectional

![Screen Shot 2023-01-01 at 16.45.25.png](https://s2.loli.net/2023/01/01/GugMWiLtaE95NTp.png)

#### Replace Inheritance with Delegation

```java
class List {...}
class Stack extends List {...}
```

$$
\Downarrow
$$

```java
class Stack {
  constructor() {
    this._storage = new List();
  }
}
class List {...}
```

### Summary

重复代码(Duplicated Code)： Extract Method, Extract Class

过长函数(Long Method)： Extract Method, Replace Temp with Query, Introduce Parameter Object

过大的类(Large Class)： Extract Class, Extract Interface

过长参数列表(Long Parameter List)： Introduce Parameter Object, Preserve Whole Object, Replace Parameter with Method

发散式变化(Divergent Change)（一个类过大，受多个地方改变影响）：Extract Class

霰弹式修改(Shotgun Surgery)： Move Method, Move Field, Inline Class

依恋情结(Feature Envy)： Move Method, Extract Method

数据泥团(Data Clumps)： Preserve Whole Object, Introduce Parameter Object, Extract Class

基本类型偏执(Primitive Obsession)： Preserve Whole Object, Introduce Parameter Object, Replace Type Code with Strategy/State, Replace Data Value with Object

Switch 惊悚现身(Switch Statements)： Extract Method, Move Method, Replace Type Code with Subclasses

冗余元素(Lazy Element)： Collapse Hierarchy, Inline Class

夸夸其谈通用性(Speculative Generality)： Collapse Hierarchy, Inline Class, Remove Parameter

临时字段(Temporary Field)： Extract Class, Introduce Special Case

过长的消息链(Message Chains)： Hide Delegate, Extract Method

中间人(Middle Man)：Remove Middle Man

不合适的亲昵(Inappropriate Intimacy)： Extract Class, Change Bidirectional Association to Unidirectional, Hide Delegate

异曲同工的类(Alternative Classes with Different Interface)： Extract Superclass, Rename Method, Move Method

被拒绝的遗赠(Refused Bequest)： Replace Inheritance with Delegation
