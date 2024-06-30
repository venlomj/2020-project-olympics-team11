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
public class LandController {
    @Autowired
    private LandRepository landRepository;

    @RequestMapping("/land")
    public String land() {
        return "zoekLand";
    }

    @RequestMapping("/landzoeken")
    public String eenLand(Model model, HttpServletRequest request) {
        Land land = null;
        if (request.getParameter("eersteLandKnop") != null) {
            land = landRepository.getEersteLand();
        } else if (request.getParameter("landZoekKnop") != null) {
            int landid = Integer.parseInt(request.getParameter("landid"));
            land = landRepository.getLand(landid);
        }
        model.addAttribute("land", land);
        return "land";
    }

    //alle landen
    @RequestMapping("/allelanden")
    public String allelanden(Model model) {
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        return "lijstLanden";
    }


}
