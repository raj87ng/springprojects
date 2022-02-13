package com.rajat.springparallelwebclients.rest;


import com.rajat.springparallelwebclients.bean.StudentBean;
import com.rajat.springparallelwebclients.constant.Constants;
import com.rajat.springparallelwebclients.model.StudentDto;
import com.rajat.springparallelwebclients.response.ClientResponse;
import com.rajat.springparallelwebclients.service.impl.StudentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@Slf4j
@Tag(name ="students", description="Student APIs Controller")
@Validated
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;


    /**
     * Create User
     */
    @Operation(description = "create student",operationId = "addEmployee",summary="Add a student in system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "success",content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ClientResponse.class))
            }),
            @ApiResponse(responseCode = "400",description = "Bad Request",content = {
                    @Content
            }),
            @ApiResponse(responseCode = "500",description = "Internal Server Error",content = {
                    @Content
            })
    })
    @PostMapping(value = Constants.ADD_STUDENT , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<ClientResponse>> addEmployee(@RequestBody @Valid StudentBean student) {
        log.debug(" In addEmployee of {} with {} " , this.getClass(),student.toString());
        return studentService.createStudent(student).map(s ->
                new ResponseEntity<ClientResponse>(
                        new ClientResponse.ResponseBuilder<>("st-01", Constants.SUCCESS)
                                .withClientData(s).build(),
                        HttpStatus.CREATED));
    }
}
