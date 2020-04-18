# xml2excel
 
mvn clean install 
编译成功后，在target目录下得到可执行jar包 
xx-0.0.1-SNAPSHOT.jar

从sqlyog 或 mysqlWorkbench等 导出来的xml格式的excel文件，
可以使用此工具转成.xlsx


为了易使，可以alias一个快键命令，如下：

zhdeMacBook-Pro:target zh$ pwd
/Users/zh/xx/target

把jar文件复制到某个地方 ，这里我复制到~/Applications/xml2excel/  


zhdeMacBook-Pro:target zh$ cp xx-0.0.1-SNAPSHOT.jar ~/Applications/xml2excel/  

zhdeMacBook-Pro:target zh$ ls ~/Applications/xml2excel/  

xx-0.0.1-SNAPSHOT.jar





zhdeMacBook-Pro:target zh$ cd  

zhdeMacBook-Pro:~ zh$ vim .bash_profile  



编辑.bash_profile文件，增加一行内容
alias 'x'='java -jar /Users/zh/Applications/xml2excel/xx-0.0.1-SNAPSHOT.jar '  


保存退出，关闭终端，重新打开 （或者执行 source .bash_profile命令）




进入放了xml文件的目录，然后执行x命令，即把工作目录下的xml转成了xlsx文件，发给完全不懂技术的客户，不需要再用excel打开然后另存为一下了  


zhdeMacBook-Pro:Downloads zh$ l  

total 160
drwx------+  4 zh  staff    136 Apr 18 17:59 .  
drwxr-xr-x+ 80 zh  staff   2720 Apr 18 17:54 ..  
-rw-r--r--@  1 zh  staff  32709 Apr 18 15:10 4.18.brand.xml  
-rw-r--r--@  1 zh  staff  45993 Apr 18 15:10 4.18.category.xml  
zhdeMacBook-Pro:Downloads zh$ x  
converting:/Users/zh/Downloads/4.18.category.xml  
converting:/Users/zh/Downloads/4.18.brand.xml  
converting:/Users/zh/Downloads/4.18.category.xml success   
converting:/Users/zh/Downloads/4.18.brand.xml success   
time cost: 1(s)  
zhdeMacBook-Pro:Downloads zh$ l  
total 192  
drwx------+  6 zh  staff    204 Apr 18 17:59 .  
drwxr-xr-x+ 80 zh  staff   2720 Apr 18 17:54 ..  
-rw-r--r--@  1 zh  staff  32709 Apr 18 15:10 4.18.brand.xml  
-rw-r--r--   1 zh  staff   6040 Apr 18 17:59 4.18.brand.xml.xlsx  
-rw-r--r--@  1 zh  staff  45993 Apr 18 15:10 4.18.category.xml  
-rw-r--r--   1 zh  staff   7111 Apr 18 17:59 4.18.category.xml.xlsx  






