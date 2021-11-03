package net.iterart.chingolo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/caja")
@SessionAttributes({"caja"})
public class CajaController {

	@GetMapping("/abrir")
	public String abrirCaja(Model model) {
		
		//model.addAttribute("caja", new Caja)
		
		return "cajas/form";
	}
}
