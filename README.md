# kao-server

> 一、前后端交互数据格式(实现权限管理后,data返回的是权限标识)

- 登录：

  - 请求路径

    ```http
    localhost:8080/Visitor/login
    ```

  - 请求数据格式

    ```json
    {
    "username":"",
    "password":""
    }
    ```

  - 返回数据格式(state为0表示登录成功，1为账号错误，2为密码错误，message为提示信息)

    ```json
    {
    "state": "",      
    "message":"",	   
    "data": ""        
    }
    ```

- 注册

  - 路径

    ```http
    localhost:8080/Visitor/register
    ```

  - 请求数据格式

    ```json
    {
    "username":"",
    "password":""
    }
    ```

  - 返回数据格式(state为0为注册成功，3表示用户名已存在，message为错误提示信息)

    ```json
    {
    "state":"",     
    "message":"",	  
    "data":""         
    }
    ```

- 获取验证码

  - 路径

    ```http
    localhost:8080/getVfCode
    ```

  - 请求数据格式

    ```json
    {
       "phoneNumber":""
    }
    ```

  - 返回数据格式(state为0为获取验证码成功，4表示验证码获取失败，message为错误提示信息，data为验证码)

    ```json
    {
    "state":"",
    "message":"",
    "data":""
    }
    ```

    

- 忘记密码

  - 路径

    ```http
    localhost:8080/Visitor/forgetPassWord
    ```

  - 请求格式(密码是新密码)

    ```json
    {
    "username":"",
    "password":""
    }
    ```

  - 返回格式(0为修改成功,6表示修改密码失败,mssage为错误提示信息)

    ```json
    {
    "state":"",       
    "message":"",	   
    "data":""      
    }
    ```

    