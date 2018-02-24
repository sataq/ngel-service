package org.ngel.station.service.resources;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vgarg
 */
@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }
}
