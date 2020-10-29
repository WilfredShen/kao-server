# kao-server

> 一、前后端交互数据格式

#### 1. 访问路径

- 登录：

  ```http
  localhost:8080/Visitor/login
  ```

- 注册

  ```http
  localhost:8080/Visitor/register
  ```

- 获取验证码

  ```http
  localhost:8080/getVfCode
  ```

- 忘记密码

  ```http
  localhost:8080/Visitor/forgetPassWord
  ```

#### 2. json数据格式

##### 2.1 前端--->后端

- 登录界面

  ```json
  {
  username:"账号",
  password:"密码"
  }
  ```

- 注册界面

  ```json
  //首先获取验证码
  {
      phoneNumber:"手机号"
  }
  
  {
  username:"账号",
  password:"密码"
  }
  ```

- 忘记密码

  ```json
  {
  //首先获取验证码
  {
      phoneNumber:"手机号"
  }
  
  {
  username:"账号",
  password:"新密码"
  }
  }
  ```

##### 2.2 后端--->前端

- 登录

  ```json
  {
  state:         //0为验证成功，1表示账号输入错误，2表示密码输入错误
  message:   //错误提示信息
  data:          //返回的数据,如添加权限管理，则为权限标识
  }
  ```

- 注册

  ```json
  {
  state:         //0为验证成功,3表示用户名已存在,4表示验证码获取失败
  message:	   //错误提示信息
  data:          //返回的数据,如添加权限管理，则为权限标识
  }
  ```

- 忘记密码

  ```json
  {
  state:         //0为修改成功,6表示修改密码失败
  message:	   //错误提示信息
  data:          //返回的数据,如添加权限管理，则为权限标识
  }
  ```

  

