# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.7.2/maven-plugin/reference/html/#build-image)

public void getOrder() { 
    List<OrderDto> list = orderMapper.selectJoinList(OrderDto.class, new MPJLambdaWrapper<Order>()
        .selectAll(Order.class)      
        .select(Product::getUnitPrice)      
        .selectAs(User::getName,OrderDto::getUserName)
        .selectAs(Product::getName,OrderDto::getProductName)      
        .leftJoin(User.class, User::getId, Order::getUserId)
        .leftJoin(Product.class, Product::getId, Order::getProductId)      
        .eq(Order::getStatus,3)); list.forEach(System.out::println);}