package org.bmshackathon.client;

import org.bmshackathon.dto.VideoMetadata;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("metadata-store")
public interface VideoMetadataFeignClient {

    @RequestMapping(value = "/videoMetadatas", method = RequestMethod.GET)
    List<VideoMetadata> findAll();

}
