# Docker使用

1. ## 安装镜像命令解读:

   - ```bash
     docker run -d\
     
     --name mysql \ --容器名
     
     -p 3306:3306 \ --端口映射，将容器3306端口映射给虚拟机的3306端口
     
     -e TZ=Asia/Shanghai \ --设置时区
     
     -e MYSQL_ROOT_PASSWORD=123 \ --密码
     
     mysql：[Version] --镜像版本
     ```

2. ## 创建docker镜像(dockerFile)

   - ### dockerFile编写

     ```dockerfile
     #基础镜像
     FROM openjdk:11.0-jre-buster
     #设置时区
     ENV TZ=Asia/Shanghai
     RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
     #拷贝jar包
     COPY docker-demo.jar /app.jar
     #入口
     ENTRYPOINT ["java","-jar","/app.jar"]
     ```

   - ### 构建镜像

     ```dockerfile
     docker build -t myImage:1.0 .
     ```

     - -t 是给镜像起名，格式为repository:tag的格式，tag默认为latest

     - . 是指定dockerFile所在目录，在当前目录就为 . 

       
