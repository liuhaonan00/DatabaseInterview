# Database Interview Project
This is the database interview project, which is written by Haonan Liu

### 编译环境
Java 1.8 + maven3.6

```
cd DatabaseInterview
mvn clean package
``` 

### 运行指令
Usage:
```
java -jar target/DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar $CSV_PATH

SQL query
```

Example:
```
java -jar target/DatabaseInterview-1.0-SNAPSHOT-jar-with-dependencies.jar sample_data.csv

select a, avg(b) from foo group by a;

```

### 整体架构
1 主程序为 `DBService`  
2 代码主要架构
- Database 数据库主要组成部件 
    - DB数据库
    - Table：数据表，其中里边包含Page，用于储存表内部的Tuples
    - View：用户使用SQL语句查询后，需要保存数据库查询结果到View中
    - Tuple：用于储存每一行数据
- Query SQL解析类
- Aggregate：聚合方法库
    - 当前只有Average类
    
3 整体思路
- 用户输入SQL查询语句后，我们需要判断SQL的语法是否合理
- 根据用户所查询的数据表和相关的label，生成一个临时的View
- 如果有聚合方法，使用聚合类得到聚合后的View
- 返回View，并打印出来

### 性能分析
1 当前项目使用的是单线程的方式进行数据加载和计算，如果数据量比较大，那么query执行效率会降低

2 当前全部数据均加载到内存中，如果数据量很大，则有可能有OOM的风险




### 优化方式
1 多线程：
- 当前程序为单线程，如果表中数据量比较大，在生成View和聚合运输的过程中速度会比较慢。可以使用多线程，在聚合的过程中会省出大量时间

2 使用磁盘存储数据
- 我在设计的过程中已经考虑使用Heap Page块进行数据储存，如果能够直接从磁盘读取数据，将减少对于内存的使用


