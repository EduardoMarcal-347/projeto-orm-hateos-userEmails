package com.iftm.subscription.controllers;

import com.iftm.subscription.data.vo.EmailVO;
import com.iftm.subscription.data.vo.GroupVO;
import com.iftm.subscription.data.vo.UserVO;
import com.iftm.subscription.services.GroupService;
import com.iftm.subscription.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/group")
public class GroupController {

    @Autowired
    GroupService service;

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group
    @GetMapping(produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML })
    @Operation(summary = "Find All Groups", description = "O método findAll da classe Group é responsável por recuperar " +
            "todos os grupos existentes no sistema e retorná-los como uma lista de objetos Group. Esse método permite " +
            "obter uma lista completa de todos os grupos disponíveis, o que é útil para exibi-los, realizar operações em" +
            " massa ou obter informações sobre todos os grupos.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Sucess.", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            array = @ArraySchema(schema = @Schema(implementation = GroupVO.class)))
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<GroupVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group/ID
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
                                              MediaType.APPLICATION_XML,
                                              MediaType.APPLICATION_YML })
    @Operation(summary = "Find Group By Id", description = "O método retorna um objeto Group contendo as informações " +
            "do Group correspondente ao ID fornecido.)", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Sucess.", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = GroupVO.class))
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public GroupVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group/LINK

    @GetMapping(value = "/{link}", produces = { MediaType.APPLICATION_JSON,
                                                MediaType.APPLICATION_XML,
                                                MediaType.APPLICATION_YML })
    @Operation(summary = "Find Group by Link", description = "O método findByLink da classe Group é responsável por " +
            "buscar um grupo no sistema com base em seu atributo link. Ele retorna o objeto Group correspondente ao " +
            "link fornecido.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Sucess.", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = GroupVO.class))
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public GroupVO findByLink(@PathVariable("link") String link){
        return service.findByLink(link);
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/group/NAME

    @GetMapping(value = "/{name}", produces = { MediaType.APPLICATION_JSON,
                                                MediaType.APPLICATION_XML,
                                                MediaType.APPLICATION_YML })
    @Operation(summary = "Find Groups by Name", description = "O método findByName da classe Group é responsável por " +
            "buscar um grupo no sistema com base no seu atributo name. Ele retorna uma lista Group correspondente ao nome" +
            " fornecido.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Sucess.", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            array = @ArraySchema(schema = @Schema(implementation = GroupVO.class)))
                            }
                    ),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<GroupVO> findByName(@PathVariable("name") String name){
        return service.findByName(name);
    }

    // CREATE - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/group
    @PostMapping(consumes = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML},
                 produces = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML})
    @Operation(summary = "Save Group", description = "O método save do Controller de Group é responsável por salvar um " +
            "novo Group ou atualizar um group existente no sistema. Ele permite criar novos Groups ou modificar os " +
            "detalhes de um Group existente, como nome, code, link, etc.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = GroupVO.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public GroupVO save(@RequestBody GroupVO groupVO) {
        return service.save(groupVO);
    }

    // INSERT USERS - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/group/insert-users
    @PostMapping(value = "insert-users", consumes = { MediaType.APPLICATION_JSON,
                                                      MediaType.APPLICATION_XML,
                                                      MediaType.APPLICATION_YML},
                                         produces = { MediaType.APPLICATION_JSON,
                                                      MediaType.APPLICATION_XML,
                                                      MediaType.APPLICATION_YML})
    @Operation(summary = "Insert new Users", description = "O método insertUsers da classe Group é responsável por " +
            "adicionar um usuário a um grupo existente no sistema. Ele permite associar um usuário específico a um " +
            "grupo, fornecendo o objeto do usuário e do grupo.", tags = {"Group", "User"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = GroupVO.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public GroupVO insertUsers(@RequestBody GroupVO groupVO) {
        return service.insertUsers(groupVO);
    }

    // UPDATE - HTTP PUT
    // Endpoint: http://localhost:8080/api/v1/group

    @PutMapping(consumes = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML})
    @Operation(summary = "Update Group", description = "O método update da classe Group é responsável por atualizar as " +
            "informações de um grupo existente no sistema. Ele permite modificar os atributos do grupo.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public GroupVO update(@RequestBody GroupVO groupVO) {
        return service.update(groupVO);
    }

    // DELETE - HTTP DELETE
    // Endpoint: http://localhost:8080/api/v1/group/ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Group", description = "O método delete da classe Group é responsável por excluir um " +
            "grupo existente no sistema. Ele permite remover completamente um grupo, juntamente com todas as suas " +
            "informações e associações.", tags = {"Group"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public String delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
