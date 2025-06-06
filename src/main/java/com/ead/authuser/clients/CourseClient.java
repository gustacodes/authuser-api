package com.ead.authuser.clients;

import com.ead.authuser.dtos.CourseDto;
import com.ead.authuser.dtos.ResponsePageDto;
import com.ead.authuser.services.UtilService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Log4j2
@Component
public class CourseClient {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UtilService utilService;

    @Value("${ead.api.url.course}")
    private String REQUEST_URL_COURSE;

    public Page<CourseDto> getAllCoursesByUser(UUID userId, Pageable pageable) {
        List<CourseDto> searchResult = new ArrayList<>();
        String url = utilService.createUrl(userId, pageable);

        log.debug("Request URL: {}", url);
        log.info("Request URL: {}", url);

        try {
            ParameterizedTypeReference<ResponsePageDto<CourseDto>> responseType = new ParameterizedTypeReference<>() {};
            ResponseEntity<ResponsePageDto<CourseDto>> result = restTemplate.exchange(url, HttpMethod.GET, null, responseType);

            if (result.getBody() != null) {
                searchResult = result.getBody().getContent();
                log.debug("Response Number of Elements: {}", searchResult.size());
            }
        } catch (HttpStatusCodeException e) {
            log.error("Error requesting /courses {}", e);
        }

        log.info("Ending request /courses userId {}", userId);

        return new PageImpl<>(searchResult, pageable, searchResult.size());
    }

    @Transactional
    public void deleteUserInCourse(UUID userId) {
        String url = REQUEST_URL_COURSE + "/courses/users/" + userId;
        restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
    }
}
