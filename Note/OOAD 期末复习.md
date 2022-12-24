# OOAD 期末复习

## 重构方法

### 重复代码（Duplicated Code）

- 同类相同代码 $\Rightarrow$ 提炼方法
- 互为兄弟类相同代码 $\Rightarrow$ 将相同方法推入超类
- 不相关类的重复代码 $\Rightarrow$ 对其中一个类重复代码提炼到一个独立类当中，在另一个类使用该类

### 过长函数（Long Method）

- 积极分解函数，使用更加直观的名字
- Replace Temp with Query(120)：将局部变量的获取方式写成方法，直接进行更加直观的调用
- Introduce Parameter Object(295)：将多个形参的传入封装成一个类，进行统一传入
- Decompose Conditional(238)：将条件表达式的判断以及执行语句替换成更加直观的方法进行调用

### 过大的类（Large Class）

- 提取子类
- 按照客户端的使用方式，提取接口

### 过长参数列表（Long Parameter List）

- Replace Parameter with Method: 将可以在函数内部通过方法求得的参数，不进行传入
-  Preserve Whole Object：将同一对象的所有属性收集起来，以对象的方式传入

### 发散式变化（Divergent Change）

- 定义：某个类经常因为不同的原因在不同的方向上发生变化
- 应该找出某特定原因而造成的所有变化，然后运用 Extract Clas(149) 将它们提炼到另一个类中

### 霰弹式修改(Shotgun Surgery)

定义：如果每遇到某种变化，你都必须在许多不同的类内做出许多小修改，会导致有些忘记修改

- Move Method, Move Field: 把所有需要修改的代码放进同一个类
-  Inline Class: 把一系列相关行为放进同一个类。 

Divergent Change 是指“一个类受多种变化的影响”，Shotgun Surgery 则是值“一种变化引发多个类相应修改”

### 依恋情结(Feature Envy)

定义： 函数对某个类的兴趣高过对自己所处类的兴趣，即该函数对某个类数据的引用次数高于自己所在的类的数据的引用次数。

- 先使用 Extract Method 将这部分提炼成为独立的函数，再将该部分使用 Move Method 移动到应该处于的函数当中。

### 数据泥团（Data Clumps）

定义：多个数据总是一起出现

- 首先使用 Extract Class 将这些对象作为一个类的成员变量

- 然后使用 Introduce Parameter Object 或者Preserve Whole Object 减少函数的传参

### 基本类型偏执（Primitive Obsession）

- 运用 Replace Data Value with Object(175)将原本单独存在的数据替换为对象
- 如果想要替换的数据值是类型码，而它并不影响行为，则可以运用 Replace Type Code with Class(218) 将它换掉
- 如果你有类型码相关的条件表达式，可以运用 Replace Type Code with Subclass(213)或 Replace Type Code with State/Strategy(227)加以处理

### switch 惊悚现身（Switch Statements）

- Extract Method, Move Method
- 使用多态就需要 Replace Type Code with Subclasses 或者 Replace Type Code with Strategy

### 冗余元素（Lazy Element）

定义：一个类的内容很少，冗余

- 使用 Collapse Hierarchy 或者 Inline Class，将继承层次结构压缩

### 夸夸其谈通用性(Speculative Generality)

定义：不必要的抽象类或者处理不需要处理的特殊情况

- 使用 Collapse Hierarchy 或者 Inline Class 压缩层次结构
- 对于不必要的参数，使用  Remove Parameter 或者 Rename Method 进行解决

### 临时字段(Temporary Field)

定义：一些类的内部某个字段仅为某种特定情况而设，不易理解

- 使用 Extract Class 将这样的字段提取成一个类
- 使用 Introduce Null Object 或者 Introduce Special Case 将一些特殊情况作为一个类，和 Extract Class 类似

### 过长的消息链(Message Chains)

- 通过 Hide Delegate， Extract Method， Move Method 将中间的调用封装成函数，再将该函数推回消息链

### 中间人（Middle Man）

定义：委托过多

- Remove Middle Man
- Inline Method

### Inappropriate Intimacy

定义：类之间联系太多

- Move Method, Move Field. 将一个类被其他类需要的一些方法移动到其他类当中，但是确保这些方法或成员变量不被这个类依赖
- Extract Class, Hide Delegate
-  Change Bidirectional Association to Unidirectional

### 异曲同工的类(Alternative Classes with Different Interfaces)

- Extract Superclass, Unify Interfaces with adapter

### 被拒绝的遗赠(Refused Bequest)

- Push Down Field, Push Down Method, Replace Inheritance With Delegation

