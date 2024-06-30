package fact.it.projectolympics.controller;

import fact.it.projectolympics.model.Atleet;
import fact.it.projectolympics.model.Land;
import fact.it.projectolympics.model.Record;
import fact.it.projectolympics.repository.AtleetRepository;
import fact.it.projectolympics.repository.LandRepository;
import fact.it.projectolympics.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.lang.model.element.NestingKind;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Controller
public class AtleetController {
private int juisteLand;
private int juistGeradenAtleet = 0;

    @Autowired
    private LandRepository landRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private AtleetRepository atleetRepository;

    @RequestMapping("/atleet_zoekAtleet")
    public String zoekatleet(Model model) {
        ArrayList<Record> records = recordRepository.getAlleRecords();
        model.addAttribute("records", records);
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
        model.addAttribute("atleten", atleten);
        return "atleet_zoekAtleet";
    }

    @RequestMapping("/atleet_toonatleet")
    public String toonatleet(Model model) {
        Atleet atleet = atleetRepository.getAtleet(5);
        model.addAttribute("atleet", atleet);
        return "atleet_toonatleet";
    }

    @RequestMapping("/atleet_zoeken")
    public String atleetzoeken(Model model, HttpServletRequest request) {
        Atleet atleet = null;
        String naam = request.getParameter("atleetNaam");
        String geslacht = request.getParameter("atleetGeslacht");
        int atleetLandIndex = parseInt(request.getParameter("atleetLandIndex"));
        atleetLandIndex++;
        if (request.getParameter("zoekNaam") != null) {
            atleet = atleetRepository.getAtleetNaam(naam);
        } else if (request.getParameter("zoekGeslacht") != null){
            ArrayList<Atleet> atleten = atleetRepository.getGeslacht(geslacht);
            model.addAttribute("atleten", atleten);
            return "atleet_lijstAtleten";
        }else if (request.getParameter("zoekLand") != null){
            ArrayList<Atleet> atleten = atleetRepository.getLand(atleetLandIndex);
            model.addAttribute("atleten", atleten);
            return "atleet_lijstAtleten";
        }
        model.addAttribute("atleet", atleet);
        return "atleet_toonatleet";

    }

    @RequestMapping("/atleet_lijstAtleten")
    public String lijstatleten(Model model) {
            ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
            model.addAttribute("atleten", atleten);
        return "atleet_lijstAtleten";
    }

    @RequestMapping ("/recordsAtleet")
    public String recordsAtleet(Model model, HttpServletRequest request){
        int atleetRecordIndex = Integer.parseInt(request.getParameter("atleetRecordIndex"));
        Atleet atleet = atleetRepository.getAtleet(atleetRecordIndex);
        model.addAttribute("atleet", atleet);
        ArrayList<Record> records = recordRepository.getAtleetid(atleetRecordIndex);
        model.addAttribute("records" , records);

        return "atleet_overzichtRecords";
    }

    @RequestMapping ("/atleet_quiz")
    public String atleetQuiz(Model model){

        return "atleet_quiz";
    }

    @RequestMapping ("/atleet_gebruiker_quiz")
    public String atleetGebruikerQuiz(Model model, HttpServletRequest request){
        String atleetGebruikerNaam = request.getParameter("atleetGebruikerNaam");


        if (request.getParameter("atleet_raadLandKnop") != null) {
            model.addAttribute("atleetGebruikerNaam", atleetGebruikerNaam);
            Atleet atleet = atleetRepository.getAtleet(15);
            model.addAttribute("atleet", atleet);
            return "atleet_raadLand";
        } else if (request.getParameter("atleet_raadAtleetKnop") != null) {
            model.addAttribute("atleetGebruikerNaam", atleetGebruikerNaam);
            ArrayList<Land> landen = landRepository.getAlleLanden();
            model.addAttribute("landen", landen);
            Random random = new Random();
            int willekeurig = random.nextInt(34);
            Atleet atleet = atleetRepository.getAtleet(willekeurig);
            juisteLand = willekeurig;
            model.addAttribute("atleet", atleet);
            return "atleet_raadAtleet";
        }
        return "atleet_raadLand";
    }


    @RequestMapping("/atleet_antwoordLand")
    public String atleetAntwoordLand(Model model, HttpServletRequest request){
        String atleet_15deLand = request.getParameter("atleet_15deLand");
        ArrayList<String> landen = landRepository.getAlleLandenString();
        model.addAttribute("landen", landen);

        if (atleet_15deLand.equals(landen.get(15)) ){
            model.addAttribute("antwoord","Je was juist!");
            return "atleet_antwoord";
        }
        else if (!atleet_15deLand.equals(landen.get(15))){
            model.addAttribute("antwoord","Je was fout!");
        }

        return "atleet_antwoord";
    }


    @RequestMapping ("/atleet_raadLand")
    public String atleetRaadLand(Model model, HttpServletRequest request ){
        return "atleet_raadLand";
    }

    @RequestMapping ("/atleet_raadAtleet")
    public String atleetRaadAtleet(Model model, HttpServletRequest request){
        int atleetLandIndex = Integer.parseInt(request.getParameter("atleetLandIndex"));
        Atleet atleet = atleetRepository.getAtleet(juisteLand);
        int atleetIndex = atleet.getLandid()-1;
        if (atleetLandIndex == atleetIndex) {
            model.addAttribute("antwoord","Je was juist!");
            juistGeradenAtleet++;
            model.addAttribute("juistGeradenAtleet", juistGeradenAtleet);
        }else {
            model.addAttribute("antwoord","Je was fout!");
        }
        return "atleet_antwoord";
    }

    @RequestMapping ("/atleet_lijstAdminAtleet")
    public String atleetLijstAdminAtleet(Model model){
        ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
        model.addAttribute("atleten", atleten);

        return "atleet_lijstAdminAtleet";
    }

    @RequestMapping ("/atleet_nieuweAtleet")
    public String atleetNieuweAtleet(Model model){
        ArrayList<Land> landen = landRepository.getAlleLanden();
        model.addAttribute("landen", landen);
        return "atleet_nieuweAtleet";
    }

    @RequestMapping ("/atleet_nieuweAtleetForm")
    public String atleetNieuweAtleetForm(Model model, HttpServletRequest request){
        String naam = request.getParameter("atleetNieuweNaam");
        String geslacht = request.getParameter("atleetNieuweGeslacht");
        int atleetLandIndex = Integer.parseInt(request.getParameter("atleetLandIndex"));
        if (request.getParameter("MaakAtlaat") != null){
            atleetLandIndex++;
            atleetRepository.insertAtleet(naam, geslacht,atleetLandIndex);
            ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
            model.addAttribute("atleten", atleten);
        }
        return "atleet_lijstAdminAtleet";

    }

    @RequestMapping ("/atleet_wijzigAtleet")
    public String atleetWijzigAtleet(Model model, HttpServletRequest request){
        int atleetid = Integer.parseInt(request.getParameter("atleetid"));
        String naam = request.getParameter("naam");
        String geslacht = request.getParameter("geslacht");
        int landid = Integer.parseInt(request.getParameter("landid"));
        model.addAttribute("atleetid", atleetid);
        model.addAttribute("naam", naam);
        model.addAttribute("geslacht", geslacht);
        model.addAttribute("landid", landid);

        return "atleet_wijzigAtleet";
    }

    @RequestMapping ("/atleet_wijzigAtleetForm")
    public String atleetWijzigAtleetForm(Model model, HttpServletRequest request){
        String naam = request.getParameter("wijzigNaam");
        String geslacht = request.getParameter("wijzigGeslacht");
        int atleetid = Integer.parseInt(request.getParameter("wijzigAtleetid"));
        int landid = Integer.parseInt(request.getParameter("wijzigLandid"));
        atleetRepository.updateAtleet(naam, geslacht, landid, atleetid);
        ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
        model.addAttribute("atleten", atleten);

        return "atleet_lijstAdminAtleet";
    }

    @RequestMapping ("atleet_verwijderAtleet")
    public String atleetVerwijderAtleet(Model model, HttpServletRequest request){
        int atleetid = Integer.parseInt(request.getParameter("atleetid"));
        atleetRepository.deleteAtleet(atleetid);
        ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
        model.addAttribute("atleten", atleten);

        return "atleet_lijstAdminAtleet";
    }


}
