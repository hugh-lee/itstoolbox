package com.toolbox.configuration;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileUploadConfiguration {
	
	@Value("${upload.file.maxFileSize}")
    private String maxFileSize = "512KB";
	
	@Value("${upload.file.maxRequstSize}")
    private String maxRequestSize = "1MB";
	
	@Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置文件大小限制 ,超出设置页面会抛出异常信息，
        // 这样在文件上传的地方就需要进行异常信息的处理了;
        factory.setMaxFileSize(maxFileSize); // KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize(maxRequestSize);
        // Sets the directory location where files will be stored.
        // factory.setLocation("路径地址");
        return factory.createMultipartConfig();
    }
}
