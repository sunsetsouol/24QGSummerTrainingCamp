# GIT

## GIT两种使用方式

Git GUI: Git提供的图形界面工具

Git Bash:Git提供的命令行工具

## Git常用指令

### 基本配置

设置用户信息

git 指令

设置用户信息

```bash
git config --global user.name "输入用户名"
git config --global user.emal "输入用户邮箱"
```

查看配置信息

```bash
git config --global user.name
git config --global user.emal
```

### 为常用指令配置别名（可选）

1.使用指令

```bash
touch ~/.bashrc
```

在用户文件（C：\\\\用户\\\86178\\\）创建.bashrc文件

2.在.bashrc文件中输入如下内容:

```bash
#用于输出git提交日志
alias git-log='git log --pretty=online --all --graph --abbrev-commit'
#用于输出当前目录所有文件及基本信息
alias 11='1s -al'
```

3.打开gitBash，执行如下指令:

```bash
source ~/.bashrc
```

### 解决GitBash乱码问题（中文乱码，非必须）

1. 打开GitBash执行下面指令

   ```bash
   git config --global core.quotepath false
   ```

   

2. ${git_home}/etc/bash.bashrc 文件最后加入下面两行

   <!--(${git_home}表示git的文件安装位置)-->

```bash
	export LANG="zh_CN.UTF-8"
export LC_ALL="zh_CN.UTF-8"
```

### 获取本地仓库

在文件夹中调用git Bash窗口

执行命令 **git init** <!--初始化仓库-->

执行成功则可以看到一个隐藏的.git文件

可以使用指令 **ls -al** 来查看全部文件包括隐藏文件

### 基础操作指令

Git工作目录下对于文件的**修改**（增加、删除、更新）会存在几个状态，这些**修改**的状态会随着我们执行git的命令而发生变化

![image-20240708085015875](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708085015875.png)

1. git add (工作区 --> 暂存区)
2. git commit (暂存区 --> 本地仓库)

#### 查看修改的状态(status)

- 作用：查看修改的状态(暂存区、工作区)
- 命令格式:**git status**

#### 添加工作区到暂存区(add)

- 作用：添加工作区一个或多个文件的修改到暂存区
- 命令格式: **git add 单个文件明|通配符**
  - 将所有修改加入暂存区: **git add .**

#### 提交暂存区到本地仓库(commit)

- 作用：提交暂存区内容到本地仓库的当前分支
- 命令格式：**git commit -m"注释内容"**

#### 查看提交日志(log)

- 作用：查看提交记录
- 命令格式：git log[option]<!--可以叠加添加-->
  - options
    - --all 显示所有分支
    - --pretty=oneline 将提交信息显示为一行
    - --abbrev-commit 使得输出的commitld更简短
    - --graph 以图的形式显示

#### 版本回退

- 作用：版本切换
- 命令形式：git reset --hard coomitID
  - commitID 可以使用git log 指令查看
- 如何查看已经删除的记录
  - git reflog
  - 这个指令可以查看到已经删除的提交记录

#### 添加文件至忽略列表

添加.gitignore的文件(文件名称固定),列出要忽略的文件模式。示例:

```
#no .a files
*.a
#but do track lib.a , even though you're ignoring .a files above
!lib.a
#only ignore the TODO file in the current directory, not subdir/TODO
/TODO
#ignore all files in the bulid/directory
bulid
#ignore doc/notes.txt, but not doc/server/arch.txt
doc/*.txt
#ignore all .pdf files in the doc/ directory
doc/**/*.pdf
```

### 分支

#### 查看本地分支（不常用）

- 命令：git branch

#### 创建本地分支（不常用）

- 命令: git branch 分支名

#### *切换分支(checkout)

- 命令：git checkout 分支名

我们还可以直接切换到一个不存在的分支（创建并切换）

- 命令: git checkout -b 分支名

#### *合并分支(merge)

一个分支上的提交可以合并到另一个分支

- 命令：git merge 分支名

#### 删除分支

**不能删除当前分支，只能删除其他分支**

git branch -d b1 删除分支时，需要做各种检查

git branch -D b1 不做任何检查，强制删除 (当分支为merge到master时使用这个)

#### 解决冲突

当两个分支上对文件的修改可能会存在冲突，例如同时修改了同一个文件的同一行，这时就需要手动解决冲突，解决冲突步骤如下：

1. 处理文件中冲突的地方
2. 将解决完冲突的文件加入暂存区(add)
3. 提交到仓库(commit)

冲突部分的内容处理如下:

![image-20240708102404660](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708102404660.png)

#### 开发中分支使用原则与流程

几乎所有的版本控制系统都以某种形式支持分支。使用分支意味着你可以把你的工作从开发主线上分离开来进行重大的Bug修改、开发新的功能，以免影响开发主线。

在开发中，一般有如下分支使用原则与流程：

- master(生产)分支

  线上分支，主分支，中小规模项目作文线上运行的应用对应的分支；

- develop(开发)分支

  是从master创建的分支，一般作为开发部门的主要开发分支，如果没有其他并行开发不同期上线要求，都可以在此版本进行开发，阶段开发完成后，需要合并到master分支，准备上线

- feature/xxxx分支

  从develop创建的分支，一般是同期并行开发，蛋不同期上线要求，都可以在此版本进行开发，阶段开发完成后，需要是合并到master分支，准备上线。

- hotfix/xxxx分支

  从master派生的分支，一般作为线上bug修复使用，修复完成后需要合并到master、test、develop分支。

- 还有一些其他分支，例如test分支(用于代码测试)、pre分支(预上线分支)等等

![image-20240708103523669](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708103523669.png)

## ==简要总结Git指令==

工作区-》暂存区    git add .

暂存区-》仓库    git commit -m "commit message 01"

查看状态             git status

查看提交记录     git log          git log --pretty=oneline --abbrev-commit

版本回退(了解)   git reset --hard <commitID>

查看分支             git branch

创建并切换分支  git branch -b 分支名

分支合并              git merge 分支名 <!--首先切换到目标分支上-->

## GitHub设置SSH公钥

- 生产SSH公钥
  - ssh-kygen -t rsa
  - 不断回车
    - 如果公钥已经存在，则自动覆盖
- GitHub设置账户公共密钥
  - 获取公钥匙
    - cat ~/.ssh/id_rsa.pub
  - 验证是否配置成功
    - ssh -T git@github.com

## 操作远程仓库

### 添加远程仓库

此操作是先初始化本地库，然后与已创建的远程库进行对接。

- 命令：git remote add <远端名称> <仓库路径>
  - 远端名称，默认是origin，取决于远端服务器设置
  - 仓库路径，从远端服务器获取词URL
  - 例如：git remote add origin git@github.com:faken233/TestRepo.git

### 查看远程仓库

- 命令：git remote

### 推送到远程仓库

- 命令：git push [-f] [--set-upstream] [远端名称[本地分支名称]:[远端名称]]
  - 如果远程分支名和本地分支名相同，则可以只写本地分支
    - git push origin master
  - -f 表示强制覆盖
  - --set-upstream 推送到远端的同时并且建立起和远端分支的关联关系
    - git push --set-upstream origin master
  - 如果当前分支已经和远端分支管理，则可以胜利分支名和远端名
    - git push 将master分支推送到已关联的远端分支

#### 本地分支与远程分支的关联关系

- 查看关联关系我们可以使用`git branch -vv`命令

#### 从远程仓库克隆

如果已经有一个远端仓库，我们可以直接clogine到本地。

- 命令：git clone <仓库路径> [本地目录]
  - 本地目录可以省略，会自动生成一个目录

![image-20240708141102971](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708141102971.png)

#### 从远程仓库中抓取和拉取

远程分支和本地的分支一样，我们可以进行merge操作，只需要先把远端仓库里的更新都下载到本地，再进行操作。

- 抓取 命令：git fetch [remote name] [branch name]
  - **抓取指令就说将仓库里的更新抓取到本地，不会进行合并**
  - 如果不指定远端名称和分支名，则抓取所有分支。
- 拉去 命令： git pull [remote name] [branch name]
  - **拉取指令就说将远端仓库的修改拉到本地并自动进行合并，等同于fetch+merge**
  - 如果不指定远端名称和分支名，则抓取所有并更新当前分支

**解决合并冲突**

在一段世界，A、B用户修改了同一个号文件，且修改了同一行位置的代码，此时代码会发生合并冲突。

A用户再本地修改代码后优先推送到远程仓库，此时B用户在本地修订代码，提交到本地仓库后，也需要推到远程仓库，此时B用户晚于A用户，**故需要先拉取远程仓库的提交，经过合并才能推送到远端分支，**如下图所示.



![image-20240708193759707](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708193759707.png)

在B用户拉取代码时，因为A、B用户同一段世界修改了同一个文件的相同位置代码，故会发生合并冲突。

**远程分支也是分支，所以合并时冲突的解决方式也和解决本地分支冲突相同**。



# 使用git的铁令

1. **切换分支钱先提交本地的修改**
2. 代码及时提交，提交过后不会丢

# Git工作流程图

![image-20240708214305123](C:\Users\86178\AppData\Roaming\Typora\typora-user-images\image-20240708214305123.png)

# 重要指令

工作流程图上的7个指令(clone+fetch+pull+checkout+add+commit+push) 和两个常用指令(log+merge)

常用指令:

- 基本操作类：

​			git init	初始化仓库

​			git log    查看日志

​			git add <文件名>  添加到暂存区

​			git commiyt -m "注解“	提交到仓库

​			git merge <分支名>   合并指定分支到当前活跃分支

- 分支切换类：

​			git checkout <分支名>  切换到某个分支

​			git checkout -b <分支名> 创建并切换到某个分支（分支原来不存在）

- 远程操作

  ​	git clone <远程地址> [本地文件夹]   clone 远程仓库到本地

  ​	git pull   拉取远端仓库到本地

  ​	git push [--set-upstream] origin 分支名  推送本地修改到远端分支 

  ​	 --set-upstream 表示和远端分支绑定关联关系，只有第一次推送才需要此参数

