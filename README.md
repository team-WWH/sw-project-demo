# sw-project-demo
# WWH - 智慧演讲系统

## 项目简介

WWH（智慧演讲系统）是一个基于Spring Boot的在线演讲管理平台，支持多角色用户管理、演讲发布、问题互动、实时分析等功能。系统采用前后端分离架构，提供RESTful API接口。

## 技术栈

### 后端技术
- **Spring Boot 3.5.3** - 主框架
- **Spring Security 6.x** - 安全认证
- **JWT** - 无状态身份验证
- **MyBatis 3.0.3** - ORM框架
- **MySQL 8.3.0** - 数据库
- **Redis** - 缓存和会话存储
- **WebSocket** - 实时通信
- **MinIO** - 对象存储服务
- **Apache POI** - Excel文件处理
- **PDFBox** - PDF文件处理

### 开发工具
- **Java 17** - 编程语言
- **Maven** - 依赖管理
- **Lombok** - 代码简化

## 功能特性

### 用户管理
- 多角色支持：听众(Listener)、演讲者(Speaker)、组织者(Organizer)
- JWT无状态认证
- 用户注册、登录、权限管理

### 演讲管理
- 演讲发布、编辑、删除
- 演讲状态管理（进行中、已结束等）
- 演讲文件上传（支持PDF、Excel等格式）
- 演讲数据统计分析

### 互动功能
- 实时问答系统
- 评论和反馈
- 问题收集和管理
- 学生答题系统

### 实时功能
- WebSocket实时通信
- 演讲进度实时更新
- 在线人数统计

### 文件管理
- MinIO对象存储
- 支持大文件上传（最大100MB）
- 多种文件格式支持

## 项目结构

```
wwh/
├── src/main/java/com/example/wwh/
│   ├── Config/                 # 配置类
│   │   ├── SecurityConfig.java    # Spring Security配置
│   │   ├── JwtAuthFilter.java     # JWT认证过滤器
│   │   ├── JwtTokenProvider.java  # JWT令牌提供者
│   │   ├── WebConfig.java         # Web配置（CORS等）
│   │   └── MyBatisConfig.java     # MyBatis配置
│   ├── Controller/            # 控制器层
│   │   ├── AuthController.java    # 认证控制器
│   │   ├── SpeechController.java  # 演讲控制器
│   │   ├── QuestionController.java # 问题控制器
│   │   ├── UserController.java    # 用户控制器
│   │   └── ...                   # 其他控制器
│   ├── service/              # 服务层
│   │   ├── MultiRoleAuthService.java # 多角色认证服务
│   │   ├── SpeechService.java      # 演讲服务
│   │   ├── UserService.java        # 用户服务
│   │   └── ...                     # 其他服务
│   ├── Mapper/               # 数据访问层
│   │   ├── UserMapper.java        # 用户数据访问
│   │   ├── SpeechMapper.java      # 演讲数据访问
│   │   └── ...                    # 其他Mapper
│   ├── pojo/                 # 实体类
│   │   ├── UserType.java          # 用户类型枚举
│   │   ├── Listener.java          # 听众实体
│   │   ├── Speaker.java           # 演讲者实体
│   │   └── ...                    # 其他实体
│   └── WwhApplication.java   # 主启动类
├── src/main/resources/
│   ├── application.properties # 配置文件
│   ├── static/               # 静态资源
│   └── templates/            # 模板文件
├── resourse/
│   └── wwh.sql              # 数据库脚本
└── pom.xml                  # Maven配置
```

## 快速开始

### 环境要求
- JDK 17+
- MySQL 8.0+
- Redis 6.0+
- Maven 3.6+

### 安装步骤

1. **克隆项目**
   ```bash
   git clone [项目地址]
   cd wwh
   ```

2. **配置数据库**
   ```bash
   # 创建数据库
   CREATE DATABASE wwh CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
   
   # 导入数据
   mysql -u root -p wwh < resourse/wwh.sql
   ```

3. **配置应用**
   
   编辑 `src/main/resources/application.properties`：
   ```properties
   # 数据库配置
   spring.datasource.url=jdbc:mysql://localhost:3306/wwh
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   
   # Redis配置
   spring.data.redis.host=localhost
   spring.data.redis.port=6379
   
   # MinIO配置
   minio.endpoint=http://your-minio-server:9000
   minio.access-key=your-access-key
   minio.secret-key=your-secret-key
   ```

4. **启动应用**
   ```bash
   mvn spring-boot:run
   ```

5. **访问应用**
   - 应用地址：http://localhost:8081
   - API文档：http://localhost:8081/swagger-ui.html（如果配置了Swagger）

## API接口

### 认证接口

#### 用户登录
```http
POST /auth/login
Content-Type: application/json

{
  "username": "用户名",
  "password": "密码",
  "userType": "LISTENER"
}
```

#### 用户注册
```http
POST /auth/register
Content-Type: application/json

{
  "uname": "用户名",
  "password": "密码",
  "role": "Listener",
  "sex": "男",
  "mail": "email@example.com",
  "phone": "13800138000"
}
```

### 演讲接口

#### 获取演讲列表
```http
GET /speech/list
Authorization: Bearer {token}
```

#### 创建演讲
```http
POST /speech/create
Authorization: Bearer {token}
Content-Type: application/json

{
  "title": "演讲标题",
  "description": "演讲描述",
  "speakerId": 1
}
```

### 问题接口

#### 提交问题
```http
POST /question/submit
Authorization: Bearer {token}
Content-Type: application/json

{
  "content": "问题内容",
  "speechId": 1
}
```

## 配置说明

### 主要配置项

| 配置项 | 说明 | 默认值 |
|--------|------|--------|
| `server.port` | 服务端口 | 8081 |
| `jwt.secret` | JWT密钥 | 自动生成 |
| `jwt.expiration` | JWT过期时间 | 86400000ms |
| `spring.servlet.multipart.max-file-size` | 最大文件大小 | 100MB |
| `app.code.length` | 验证码长度 | 8 |
| `app.code.charset` | 验证码字符集 | ABCDEFGHJKLMNPQRSTUVWXY23456789 |

### 安全配置

- 启用CORS跨域支持
- JWT无状态认证
- 基于角色的权限控制
- 密码加密存储

## 开发指南

### 添加新的Controller

1. 在 `Controller` 包下创建新的控制器类
2. 添加 `@RestController` 注解
3. 使用 `@RequestMapping` 定义基础路径
4. 添加具体的接口方法

```java
@RestController
@RequestMapping("/api/example")
public class ExampleController {
    
    @GetMapping("/list")
    public ResponseEntity<?> getList() {
        // 实现逻辑
        return ResponseEntity.ok(result);
    }
}
```

### 添加新的Service

1. 在 `service` 包下创建服务类
2. 添加 `@Service` 注解
3. 注入需要的Mapper或Service

```java
@Service
public class ExampleService {
    
    @Autowired
    private ExampleMapper exampleMapper;
    
    public List<Example> getList() {
        return exampleMapper.selectAll();
    }
}
```

## 部署说明

### 打包部署

```bash
# 打包
mvn clean package

# 运行
java -jar target/wwh-0.0.1-SNAPSHOT.jar
```

### Docker部署

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/wwh-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]
```

## 常见问题

### 1. 端口被占用
修改 `application.properties` 中的 `server.port` 配置

### 2. 数据库连接失败
检查数据库配置和网络连接

### 3. Redis连接失败
确保Redis服务正在运行

### 4. 文件上传失败
检查MinIO配置和网络连接



