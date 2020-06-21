package com.gxs.springcloud.Controller;

import com.gxs.springcloud.entities.DeptEntity;
import com.gxs.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author GongXings
 * @createTime 30 15:07
 * @description
 */
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean addDept(@RequestBody DeptEntity deptEntity) {
        return deptService.addDept(deptEntity);
    }

    @RequestMapping(value = "/dept/findById/{deptNo}", method = RequestMethod.GET)
    //一旦调用服务方法失败并抛出了异常信息之后，
    // 会自动调用@HystrixCommand标注好的fallbackMethod，调用类中指定的方法
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public DeptEntity findById(@PathVariable("deptNo") Long deptNo) {
        DeptEntity dept = this.deptService.findById(deptNo);
        if(dept == null){
            throw new RuntimeException("该Id：" + deptNo + "没有对应的信息");
        }
        return dept;
    }

    public DeptEntity processHystrix_Get(@PathVariable("deptNo") Long deptNo) {
        return new DeptEntity().setDeptNo(deptNo).setDeptName("该Id"+ deptNo +"没有对应的信息,null -- @HystrixCommand")
                    .setDbSource("no this database in MySql");
    }

    @RequestMapping(value = "/dept/findAll", method = RequestMethod.GET)
    public List<DeptEntity> findAll() {
        return deptService.findAll();
    }

    /**
     * 增加自己服务描述的接口
     *
     * @return
     */
    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
    public Object discovery() {
        List<String> list = discoveryClient.getServices();
        System.out.println("总共有多少个微服务" + list.size());

        //查询STUDY-SPRINGCLOUD-DEPT 服务
        List<ServiceInstance> instances = discoveryClient.getInstances("STUDY-SPRINGCLOUD-DEPT");

        //打印STUDY-SPRINGCLOUD-DEPT服务信息
        for (ServiceInstance element : instances) {
            System.out.println(element.getServiceId());
            System.out.println(element.getHost());
            System.out.println(element.getPort());
            System.out.println(element.getUri());
        }
        return this.discoveryClient;

    }

}
