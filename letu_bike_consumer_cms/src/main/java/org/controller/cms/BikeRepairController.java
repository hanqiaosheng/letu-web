package org.controller.cms;



import javax.annotation.Resource;


import org.entity.dto.BikeRepair;
import org.service.cms.read.BikeRepairServiceRead;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@Scope("prototype")
@RequestMapping("cms/bikeRepair")
public class BikeRepairController {
	
	@Resource
	BikeRepairServiceRead bikeRepairServiceRead;
	
	/**
	 * 查找维护人信息
	 * @param managerId
	 * @return
	 * @throws Exception
	 */
    @RequestMapping("bikeRepairInfo")
    public @ResponseBody BikeRepair bikeRepairInfo(Long managerId) throws Exception{
    	BikeRepair bikeRepair = bikeRepairServiceRead.findBikeRepairById(managerId);
    	return bikeRepair;
    }

}
