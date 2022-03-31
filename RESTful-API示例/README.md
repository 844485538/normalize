## ResponseTemplate.java

RESTFul 接口返回的对象实体类。

返回对象实体类主要包含三个部份：

- code
  - 状态码
  - 定义规则为 6 + HTTP 状态码
  - 6200 成功
  - 6500 失败
  - 6401 未登陆
- message
- data

## RestFulApiController.java

RestFulApi 接口示例。

- GET
  - 负责查询数据
- POST
  - 负责新增数据
- PUT
  - 负责修改数据
- DELETE
  - 负责删除数据

PUT、DELETE 可用 POST 替代。**GET 不可用于查询数据**。
