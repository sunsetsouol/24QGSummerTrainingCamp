# Git基本命令使用

## 初始化

### 1.1直接创建

- 目的：直接把一个文件夹创建为github仓库
- 步骤：新建文件夹->鼠标右键->选择Open Git Bash here->输入代码指令
- 命令：git init

成功标志：该文件夹下为生成一个隐形的文件夹名为：git

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTBmOTI0N2ZmODY2OGFjOTc3MTk3NTM2NjQ5NGZiYjJfSllzbERRSk9KejFXZUhuSFNvUXRIOVNjdEJ3WTVVMldfVG9rZW46TTVJR2JlZFhXb1RSVk54YWMxM2NnZ3N5bld6XzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

查看当前文件夹：ll（两个小写L）

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDNhZTZkN2UyYTRlMzYxYmUzNjI1MDFiNTBjYWNhNTFfdnF6Y2VkNk1pNTVVTUJ2YllGcmZKZ1M4VnU2ak15ZlJfVG9rZW46SHExOWJkS0ZQb2dSbXN4RmxEOWN0a0ZQbkpoXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 1.2 复制仓库

- 目的：从个人git账号中把仓库链接复制到一个文件夹中
- 步骤：复制仓库的http链接->文件夹中右键->选择Open Git Bash here->输入代码指令
- 命令：git clone 复制的链接

成功标志：该文件夹下为生成一个隐形的文件夹名为：git

```Java
git clone https://github.com/USERNAME/REPOSITORIES.git
```

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTRjZWE4YjY3NTBjODlmYWY5YzQxN2E2MGFhMTZjMWNfRnYyRU5vUjBsdVVMTVZWM1lla0hjdTQ1dHpPeTVQNWJfVG9rZW46SWV6MWJrRlJab29CdWV4YThNSGNKQXFpbm1QXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTk2MTUxNzZiMTM1ZWU0M2E1MzFlNDc3ZGJhNmYyNDZfSnRaUzJJclpsRkR5SWNuaERHaW8wbXoyZzRmNGpIQ2tfVG9rZW46QzZwaGJwcFY2b2ExV1R4TVFXVmNZb1c1blZmXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NWY4NGI2ODVkYWZiMThkMTgyMjcyN2E0NTBjOTVkZjlfbmVhYzc5ZmhSV2tubEdiWWJybFlTakV0ZEZIWHRvNmNfVG9rZW46TXA2ZWIwTHFQb0hzcmx4dERXUGNXd25YbnAyXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

## 上传操作

### 2.1 把文件转移至暂存区(add)

- 目的：把工作区的新文件或修改的文件转至暂存区
- 命令：git add 文件名(或通识符)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzdjMzAwODdhMDQ5NTNiODZjMGU1N2IyN2QwYmFmMjBfQjB0ZENwVko2V2JIbzFUeTcwMkxJcW1JMVpVWVFpcm1fVG9rZW46TzgwWmJHbW14bzVsc3V4VlhVZWNsNkl6bnVmXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

若想直接把所有修改过的文件都上传，命令：git add .

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDY2MDliMmMzMWM4OGI0YzFjZDQ2MmY4NzNjMGQ4YWRfU0RYWnVVZXRYcHFiZndSRENhZ2VseGdXZ2hVOGFjWmpfVG9rZW46Vm1Mb2I5UzNZbzRMS3l4MDNIUmNLZ20xbjllXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 2.2 把暂存区的文件转移至本地仓库(commit)

- 目的：把暂存区中的文件转移至本地仓库
- 命令：git commit -m"备注"

**提交时候添加的message会被放入日志中**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzljNmJhODNiZTY3MjdjZDc2ZDJiZWZjYzAzMWNhMzJfdzhwOFljRm4wdDlyeE1UZVA4SW5oTThLaXA2UjljdmpfVG9rZW46Tm1RMWJsekIzb1gySDJ4S0l3d2NUbW10bjdiXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 2.3把本地仓库的文件转移至远程仓库(push)

- 目的：把本地仓库的文件转移至远程仓库，一般完成了这最后一步才算是成功提交文件至远程仓库
- 命令：git push

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MmJlZTJlMGI4NzAzMjQ2YzAyZDQ3MmI0Y2JjMWZmYjVfZGE2V1JhM3F5bDBFdUVxSTN5eUMyNk9INVl1ZFRweVFfVG9rZW46TjBEcmJKYXlYb2h0akp4RHh5TmNVeG00blQ4XzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 2.4 查看状态(status)

- 目的：查看当前各个文件所处状态，例如暂存区或本地仓库
- 命令：git status

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NjI4MjU3MDJhYmFhNTZjMmJmZDFiYTVjMTMyZDk2ZmRfZnQ3TlZSa2VPczRLbmhlTXVnSGFndEhMV0pxNkxNY0FfVG9rZW46SGNJRGJ6eU9lb0s1Y0h4bGlCV2NIY1ZvbjFiXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

表示有文件修改为未转移至暂存区

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDMyYjBiNzI0NTQ4NzMxNmU2MzEzNTA4M2MxNTNhNzZfUjd6Z0JDbEV3Uk8wZEFiNlZLRTRsSkVZQ081YkVrQVFfVG9rZW46SzJncGJHTndOb1ZTblp4VjZMcWN0MmxabjZjXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

表示等待文件转至本地仓库

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTA2YjJkYTE3YzJiYTFiOTU4NWNkNjMyMmJlZTEwMGZfTUppdnc4MGNaS2wzdHJvWDVWOUhZdUpRb1VjWWlyRGRfVG9rZW46THZkeGJvQTVyb2xvWFB4VThOdGMwdWw0blNiXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

表示该文件等待push到远程仓库

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDVmNmQzZTYxOWM5MDJhZDc3ZWEzOWVjYjIzZDA1NDhfN3dqOThvdEZuM2t5WURwZndSQTRrSzJOSkltcDJiNmZfVG9rZW46TnN5UGIwcjBpb1JHQ2p4VEJZeGNxNE11blJoXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

表示已完成推到远程仓库

## 分支操作

### 3.1 查看分支(branch)

#### 3.1.1 git branch

- 目的：查看所有分支（只有自己本地的）
- 命令：git branch

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTc4NDg5ZTYxOTI3OWM1OTE5MWViZTFkYzBiN2Q2NDBfRGZpYTF4SmZHckVxaHdRVGk2d3FReFZ6dGJna29NU3hfVG9rZW46Q3pySmJacXNzb2lmeEF4WE5VbmM1RWNFblRYXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

#### 3.1.2 git branch -a

- 目的：查看所有分支（关联与未关联都有）
- 命令：git branch -a

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWQ2Y2ZhYTZhZTA0NjRjYWMyY2RjOTEzNmE4ZjZkMDRfNG1BMmhoZ3NtU2d3UWVybnJnTnYzNzRrOERrWEZNTGRfVG9rZW46UzZTbWJIN2Fob2FxSER4enUxaGNkNmE2bk9mXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.2 创建分支

**新建的分支是在原来分支的基础上的，因此该新分支会有当前分支的所有内容**

- 目的：创建一个新分支
- 命令：git branch 分支名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=N2FkNWI2YmQzN2FhZWY4YThiMjA4Yzk5MjYwNWYwMjJfMjR6c0tCV04wY2pnYWU5WkFQSXV0bHl1ZG96V0k4cU1fVG9rZW46S01ZcmI1ajFqb0E4WHZ4Y0haMWN1UjU2bjhjXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.3 切换分支(checkout)

- 目的：切换分支（该分支存在）
- 命令：git checkout 分支名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzkzYmQ0NDYxMzJmOTJjMTIwZDc5OTNhMDliMmNlZDFfV1VGNFFvbWRrSEY3U0c0YzNrbkpnT3IwSDF6Q0lHVGVfVG9rZW46UHVjR2JyY3F5b0lITGp4TGxFaGNBdUJLbkhGXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.4 创建并切换到该分支

- 目的：可以切换到一个不存在的分支并把该分支创建好（注意：是不存在的分支）
- 命令：git checkout -b 分支名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OGRlMjFiZGYwMTIzNjE1YmU5NzgwMzk3ZTE4M2YxY2FfWXIwRFdSbXI5c1k2RHI0RTR0cEEzcDM2eWNiZWdCV3RfVG9rZW46Uzc5OGJvV3VNb2lHQm94bkZXTmN5MHRhblBiXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.5 删除分支

- 目的：删除分支（注：不能删除当前使用的分支）
- 命令：git branch -d 分支名（先检查该分支，再进行删除）

​         git branch -D 分支名（直接删除）

推荐：使用小写d即可

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZWUwNTM0NjI4MjJmNjQ2MTQ4ZmI5NWIzMzhiZGM4MGNfenJNUXJpZFk1YmRUUWhXMUVVRE14OTh2Rk5JTGdGSlFfVG9rZW46S2tUUWJ0QVhNb2hlaGJ4UnhlcGNmbzBqbkdoXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.6 分支操作

#### 3.6.1 分支意义

把开发工作从主线main上分离，去开发修改bug，开发新功能等

每一个分支是每一个人的工作区，每个人负责自己的工作区，不影响其他分支，更好协作开发

#### 3.6.2 合并分支(merge)

前提：分支上的全部文件已成功提交

- 目的：把不同分支的内容合并到同一分支，一般是把dev的分支合并至主线分支main
- 步骤：切换至主分支，输入合并分支命令，再上传至本地仓库接着远程仓库
- 命令：git merge 分支名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjU5ODJkODViNjE3YjdjMzgzN2NhZTg3OTM1OTU0NzVfMnVJOW9ZZ1BJTlVKMlhqblpRRE1LVkZLZjhNRzd0ZWZfVG9rZW46RHFMNGJwcEdvb2RXTEJ4azlXUmNwa3NjbmplXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.7 解决合并冲突(conflict)

冲突出现原因：有一个以上的文件对同一个文件的修改出现了不同的内容

方法：

1. 找出发生冲突的文件
2. 到发生冲突的文件中手动修改出生冲突的内容
3. 把修改后的文件add到暂存区中
4. 最后再推送到远程仓库即可

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Mzg4MmU0MjU4MDI0YWZlZmYwOWFkNjNmNjZiZDY4MWVfM1Jtck92VFh4SjNEdzJpZmFpUkx1QWx3TTlSbEtiMURfVG9rZW46TURwMmI2VVY3b3RuY2x4N0tXY2NQbFhnbmdjXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzBmZTRkYTZhNGNlOWFiNTZiZTA0YWJkYTZkYWIxNjVfQzZOekFRb3IwY3VtNUZCQklEblNXWDVCRjdLZlNjU25fVG9rZW46SGNTSmJrRWtqb3Z3eGF4YTFFR2Mwb0wxbnVlXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 3.8 分支的类别相关作用

#### main

- 作用：生产分支。（不可删除）

#### develop

- 作用：开发分支，是从main上创建的分支，在全部开发完成后，最后再整合到主分支。（不可删除）

#### feature

- 作用：可以开发新功能，是从develop上创建的分支，一般是开发后整合到develop上，而不是直接到main分支上。（用完后可删除）

#### hotfifix

- 作用：从main上派生的分支，一般作为线上bug修复使用，修复测试完后要合并到main、develop分支上。（用完后可删除）

## 查看日志

- 目的：查看过去的提交的记录
- 命令：

git log --[options]

- options
  - all 显示所有分支
  - pretty=oneline 将提交信息显示为一行
  - abbrev-commit 使得输出的commitId更简短
  - graph 以图的形式显示

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTA1OTJhNDhmM2FmNWU0ZTY5ZTgxYTEzYzc1MTViNTlfbFZ1cUtVZmo2b3hJY1IwTGs4RmVUd1htM2Eyd2djR3hfVG9rZW46TmN5TmJVazBlb3Q3MVp4dW1KWGNkQ1U0bmNnXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

git-log（更简洁，二者无区别）

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ODVjOTM3Y2I5ZGFhYjBkOTZmNDJjZTNkYzkyNDlmYjJfYnZzVmhIMUVVUUlRUTAzeGVSeVZtY2dqMUd0dFVXdlNfVG9rZW46Sk1qS2IybWx3b3BOdDZ4UGxLZGMzNWFKbkplXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

## 版本回退

### 5.1 版本切换

- 作用：切换到原来的版本
- 命令：git reset --hard CommitID

CommitID可从日志中查看，是七位数一段字符

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YTUxNzM4ZmFjNTYwYmI5YmVkM2FlMzJjZWFiMThlNGRfbjJTcmhRTmJnUlFZYjM4dEdqOERObmV0TjU0dnlBbG1fVG9rZW46R1VjNGIyMG5nb0NrVDB4bUttemNWNHV6bkJNXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 5.2 查看已被删除的记录

- 作用：查看已被删除的记录
- 命令：git reflog

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDZhZjY2OTdlMzhjY2QxMGJhOGQ2N2U5OTBmOTFjNThfRDRmUHRXSFFua3NaODlWSE82Z1FMOFZNcm9HVnNhZHFfVG9rZW46TW9xR2IwNTJzb0p2NG54UDRkYmNKTkowbjhnXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 5.3 还原版本

我们在查看reflog记录后，知道了原先前面的记录ID，可以再使用git reset --hard CommitID还原到最新的版本。

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzExOGE0MTRmMTg2MjcxZWE4YjJiZjkxMmRhMmU2OTFfanQzUEhtNUlZOXNMODZNdllKVkNMdjJ0SllxbzV6enFfVG9rZW46Q01aNmJBQzQ5bzY5TE94SEJ2ZmN3dnptbjFlXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

## 远程仓库

### 6.1 查看远程仓库

- 命令：git remote

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWRlYjEyMDI3NTY5YzVhOWMwMzliNzI2ZjYyM2NjMjdfa3NuQjlZdk9ET1dabWY0dkgzUUN3WE9oZ2lkVmVCMDFfVG9rZW46VExoVGJHTUJub2R5aTl4cXNORmNQZ2dZbmZnXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

### 6.2 推送到远程仓库

- 命令：git push --set-upstream 远程仓库 当前分支名

原因：可能是当前分支还没有与远端分支仓库建立起关联联系，需要先使用该命令，若已联系，可直接push

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTQ4Y2ZkYmNjNGQzOTMzZGZlZGQ5NjkzN2JkNTJiYjRfaHN0TXhidXFLWlFyWDZjN2ZueHhRRHlBcnpKVW5YcFVfVG9rZW46V2FOVmJYRXJub0FjdDV4SlVqZmMwM1lObmhoXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

## 拉取仓库最新版本

- 目的：确保每次开发前的版本是最新版本
- 命令：git pull

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDk5NDAxMjMxOWEwZmQyYTJhNWNhMzY2ZGVlYzg1NzNfYVJQRzBSTWR6TW5EU2Y3dWdNa3FGRk9mN1pDdHBiUWFfVG9rZW46RzJvSmJMOWNKbzQ4bUp4WnRiTGNXUHdhblRnXzE3MjA5NjQwMDk6MTcyMDk2NzYwOV9WNA)

**注意**：当合并分支前，必须要先git pull，确保自己当前是最新的版本再进行合并

合并步骤：分支上：add -> commit > checkout(切换分支) -> pull -> merge 若出现Conflict，手动进入文件进行修改，修改后再进行add，commit，push即可。