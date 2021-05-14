package edu.bid.course.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * course.CommonController
 *
 * @Autor: Kolja
 * @Date: 05.05.2021
 * @Version: CommonController: 1.0
 */

@Tag(name = "Common Contoller", description = "Introductory GET methods (not connected to the main Library model)")
@RestController
@RequestMapping("common")
public class CommonController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);

    @Operation(summary = " Get Title page",
            description = " Finds and displays Title page")
    @RequestMapping("/titulka")
    public String showTitulka () throws IOException {

        LOGGER.info(" Get Titulka request has been called. ");

        String titulStr = new String(Files.readAllBytes(Paths.get("test.txt")));

        return titulStr;
    }

    @Operation(summary = " Get Title page with custom student name",
            description = " Finds and displays Title page with custom student name")
    @RequestMapping("/titulka/{name}")
    public String showTitulka (@PathVariable String name) throws IOException {
        String titulStr = new String(Files.readAllBytes(Paths.get("test.txt")));

        titulStr = titulStr.replace("Бідюк Микола Валентинович", name);

        LOGGER.info(" Get Titulka request with specified name " + name + " has been called. ");

        return titulStr;
    }
}