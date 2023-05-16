package com.iftm.subscription.controllers;

import com.iftm.subscription.data.vo.UserVO;
import com.iftm.subscription.services.UserService;
import com.iftm.subscription.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user")
@Tag(name = "User", description = "Endpoint for managing users.")
public class UserController {

    @Autowired
    private UserService service;

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user
    @GetMapping(produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML })
    @Operation(
        summary = "Find all users.", description = "Find all users.", tags = {"User"},
        responses = {
                @ApiResponse(description = "Sucess.", responseCode = "200",
                    content = {
                        @Content(mediaType = MediaType.APPLICATION_JSON,
                                array = @ArraySchema(schema = @Schema(implementation = UserVO.class)))
                    }
                ),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public List<UserVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user/ID
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
                                              MediaType.APPLICATION_XML,
                                              MediaType.APPLICATION_YML})
    @Operation(
        summary = "Find a user by ID.", description = "Find a user by ID.", tags = {"User"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UserVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public UserVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // USERS BY GROUP NAME - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/user/group/NOME_DO_GRUPO

    @GetMapping(value = "group/{name}", produces = { MediaType.APPLICATION_JSON,
                                                     MediaType.APPLICATION_XML,
                                                     MediaType.APPLICATION_YML})
    @Operation(
        summary = "Find a user by Group.", description = "Find a user by Group.", tags = {"User"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UserVO.class)
                    )
                }
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public List<UserVO> findUsersByGroupName(@PathVariable("name") String groupName) {
        return service.findByGroupName(groupName);
    }

    // CREATE - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/user
    @PostMapping(consumes = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML},
                 produces = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML})
    @Operation(
        summary = "Create a user.", description = "Create a user.", tags = {"User"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                content = @Content(
                    schema = @Schema(implementation = UserVO.class)
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public UserVO save(@RequestBody UserVO userVO) {
        return service.save(userVO);
    }

    // UPDATE - HTTP PUT
    // Endpoint: http://localhost:8080/api/v1/user
    @PutMapping(consumes = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML})
    @Operation(
        summary = "Update a user.", description = "Update a user.", tags = {"User"},
        responses = {
            @ApiResponse(description = "Success", responseCode = "200",
                content = @Content(
                        schema = @Schema(implementation = UserVO.class)
                )
            ),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public UserVO update(@RequestBody UserVO userVO) {
        return service.update(userVO);
    }

    // DELETE - HTTP DELETE
    // Endpoint: http://localhost:8080/api/v1/user/ID
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletes a User by ID.", description = "Deletes a User by ID.", tags = {"User"},
        responses = {
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
        }
    )
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
