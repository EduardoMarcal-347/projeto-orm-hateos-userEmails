package com.iftm.subscription.controllers;

import com.iftm.subscription.data.vo.EmailVO;
import com.iftm.subscription.services.EmailService;
import com.iftm.subscription.utils.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("api/v1/email")
public class EmailController {
    @Autowired
    private EmailService service;

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/email
    @GetMapping(produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML })
    @Operation(summary = "Find all emails", description = "O método findAll do Controller de Email é responsável por " +
            "recuperar todos os emails existentes no sistema e retorná-los como uma lista. Este método pode ser " +
            " para diversas finalidades, como exibir uma lista de emails na interface do usuário, realizar operações " +
            "em massa nos emails, ou mesmo para realizar consultas e análises de dados.", tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                        content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON,
                                    array = @ArraySchema(schema = @Schema(implementation = EmailVO.class))
                            )
                        }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<EmailVO> findAll() {
        return service.findAll();
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/email/ID
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON,
                                              MediaType.APPLICATION_XML,
                                              MediaType.APPLICATION_YML })
    @Operation(summary = "Find Email by Id", description = "O método retorna um objeto Email contendo as informações " +
            "do email correspondente ao ID fornecido.)", tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            schema = @Schema(implementation = EmailVO.class)
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public EmailVO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/email/to

    @GetMapping(value = "/{recipient}", produces = { MediaType.APPLICATION_JSON,
                                                     MediaType.APPLICATION_XML,
                                                     MediaType.APPLICATION_YML })
    @Operation(summary = "Find Email by Recipient", description = "O método findByRecipient do Controller de Email é responsável" +
            " por buscar emails com base no destinatário fornecido. Ele retorna uma lista de emails que" +
            " foram enviados para o destinatário especificado.",
            responses ={
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            array = @ArraySchema(schema = @Schema(implementation = EmailVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<EmailVO> findAllByRecipient(@PathVariable("recipient") String emailTo){
        return service.findAllByRecipient(emailTo);
    }

    // READ - HTTP GET
    // Endpoint: http://localhost:8080/api/v1/email/from
    @GetMapping(value = "/{sender}", produces = { MediaType.APPLICATION_JSON,
                                                  MediaType.APPLICATION_XML,
                                                  MediaType.APPLICATION_YML })
    @Operation(summary = "Find Email by Sender", description = "O método findBySender do Controller de Email é responsável" +
            " por buscar emails com base no remetente fornecido. Ele retorna uma lista de emails que foram enviados pelo" +
            " remetente especificado.", tags = {"Email"},
            responses = {
                    @ApiResponse(description = "Sucess", responseCode = "200",
                            content = {
                                    @Content(mediaType = MediaType.APPLICATION_JSON,
                                            array = @ArraySchema(schema = @Schema(implementation = EmailVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public List<EmailVO> findAllBySender(@PathVariable("sender") String emailFrom){
        return service.findAllBySender(emailFrom);
    }

    // CREATE - HTTP POST
    // Endpoint: http://localhost:8080/api/v1/email
    @PostMapping(consumes = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML},
                 produces = { MediaType.APPLICATION_JSON,
                              MediaType.APPLICATION_XML,
                              MediaType.APPLICATION_YML})
    @Operation(summary = "Save Email", description = "O método save do Controller de Email é responsável por salvar um " +
            "novo email ou atualizar um email existente no sistema. Ele permite criar novos emails ou modificar os " +
            "detalhes de um email existente, como remetente, destinatários, assunto, etc.", tags = {"Email"},
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200",
                        content = {
                                @Content(mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = EmailVO.class)
                                )
                        }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public EmailVO save(@RequestBody EmailVO emailVO) {
        return service.save(emailVO);
    }

    // UPDATE - HTTP PUT
    // Endpoint: http://localhost:8080/api/v1/email
    @PutMapping(consumes = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML},
                produces = { MediaType.APPLICATION_JSON,
                             MediaType.APPLICATION_XML,
                             MediaType.APPLICATION_YML})
    @Operation(summary = "Update Email", description = "O método update do Controller de Email é responsável por " +
            "atualizar os detalhes de um email existente no sistema. Ele permite modificar os atributos de um email",
            tags ={"Email"},
            responses = {
                @ApiResponse(description = "Sucess", responseCode = "200",
                        content = {
                                @Content(mediaType = MediaType.APPLICATION_JSON,
                                        schema = @Schema(implementation = EmailVO.class)
                                )
                        }),
                @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
            })
    public EmailVO update(@RequestBody EmailVO emailVO) {
        return service.update(emailVO);
    }

    // DELETE - HTTP DELETE
    // Endpoint: http://localhost:8080/api/v1/email/ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Email", description = "O método delete do Controller de Email é responsável por excluir" +
            " um email existente no sistema. Ele permite remover permanentemente um email com base no seu ID.",
            tags = {"Email"},
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