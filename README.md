# springboot_vue

## Project setup
```
npm install
```

### Compiles and hot-reloads for development
```
npm run serve
```

### Compiles and minifies for production
```
npm run build
```

### Customize configuration
See [Configuration Reference](https://cli.vuejs.org/config/).


#vue 工程创建及使用步骤
- 官网下载node.js,安装 ,cmd:npm -version 测试版本
- npm install -g @vue/cli  安装最新的vue ,vue --version 查看版本
- vue ui (3.0) 使用ui界面创建vue工程,选择(E:\VueProject)
- vue add axios 安装axios的ajax插件
-  cnpm install --save element-ui 安装ElementUI



###跨域异常
- 异常原因: 跨端口,跨域名请求
- 解决方法:前段后端都可以解决这个异常
-1.后端(SpringBoot)
   添加配置类
```java
    @Configuration
    public class CrosConfig implements WebMvcConfigurer {
    
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowCredentials(true)
                    .maxAge(3600)
                    .allowedHeaders("*");
    
        }
    }
```

-2.前端 https://www.cnblogs.com/sdcs/p/8484905.html