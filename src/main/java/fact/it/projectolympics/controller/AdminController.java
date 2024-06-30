package fact.it.projectolympics.controller;

import fact.it.projectolympics.model.Land;
import fact.it.projectolympics.repository.LandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class AdminController {
    @Autowired
    private LandRepository landRepository;

    @RequestMapping("/nieuwLand")
    public String nieuwLand() {
        return "nieuwLand";
    }

    @RequestMapping("/voegtoeLand")
    public String voegtoeLand(Model model, HttpServletRequest request) {
        String naam = request.getParameter("naam");
        landRepository.insertLand(naam);
//      lijst van alle landen terug opvragen
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        return "lijstLanden";
    }

    @RequestMapping("/wijzigLand")
    public String wijzigLand(Model model, HttpServletRequest request) {
        String naam = request.getParameter("naam");
        int landid = Integer.parseInt(request.getParameter("landid"));
        model.addAttribute("landid", landid);
        model.addAttribute("naam", naam);
        return "wijzigLand";
    }

    @RequestMapping("/bevestigWijzigLand")
    public String bevestigWijzigLand(Model model, HttpServletRequest request) {
        String naam = request.getParameter("naam");
        int landid = Integer.parseInt(request.getParameter("landid"));
        landRepository.updateLand(landid, naam);
        //lijst van alle landen terug opvragen
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        return "lijstLanden";
    }

    @RequestMapping("/verwijderLand")
    public String verwijderLand(Model model, HttpServletRequest request) {
        int landid = Integer.parseInt(request.getParameter("landid"));
        landRepository.deleteLand(landid);
        //lijst van alle landen terug opvragen
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        return "lijstLanden";
    }
}
