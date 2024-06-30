


package fact.it.projectolympics.controller;

import fact.it.projectolympics.model.Atleet;
import fact.it.projectolympics.model.Highscore;
import fact.it.projectolympics.model.Land;
import fact.it.projectolympics.model.Record;
import fact.it.projectolympics.repository.AtleetRepository;
import fact.it.projectolympics.repository.HighscoreRepository;
import fact.it.projectolympics.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Random;

import static java.lang.Integer.parseInt;

@Controller
public class RecordController {
    @Autowired
    private RecordRepository recordRepository;
    @Autowired
    private HighscoreRepository highscoreRepository;
    private int jaar;
    private int randomGetal;
    private int aantalJuist;
    private String naam;
    private int juistGeradenRecord = 0;

    @RequestMapping("/record_zoekRecord")
    public String zoekRecord(Model model) {
        ArrayList<String> steden = recordRepository.getAlleSteden();
        model.addAttribute("steden", steden);
        return "record_zoekRecord";
    }


    @RequestMapping("/record_detail1record")
    public String zoekRecord10(Model model) {
        Record record = recordRepository.getRecord(10);
        model.addAttribute("record", record);
        return "record_detail1record";
    }

    @RequestMapping("/record_zoeken")
    public String zoekRecord(Model model, HttpServletRequest request) {
        Record record = null;
        if (request.getParameter("zoekRecordKnop") != null) {
            String zoekRecord = request.getParameter("zoekRecord");
            record = recordRepository.getRecordZoekRecord(zoekRecord);
        } else if (request.getParameter("zoekJaartalKnop") != null) {
            String zoekJaartal = request.getParameter("zoekJaartal");
            ArrayList<Record> records = recordRepository.getJaartal(zoekJaartal);
            model.addAttribute("records", records);
            return "record_lijstRecords";
        } else if (request.getParameter("zoekPlaatsKnop") != null) {
            String zoekPlaats = request.getParameter("zoekPlaats");
            ArrayList<Record> records = recordRepository.getPlaats(zoekPlaats);
            model.addAttribute("records", records);
            return "record_lijstRecords";
        } else if (request.getParameter("zoekStadKnop") != null) {
            int recordStadIndex = Integer.parseInt(request.getParameter("recordStadIndex"));
            ArrayList<String> steden = recordRepository.getAlleSteden();
            ArrayList<Record> records = recordRepository.getPlaats(steden.get(recordStadIndex));
            model.addAttribute("records", records);
            return "record_lijstRecords";
        }

        model.addAttribute("record", record);
        return "record_detail1record";
    }

    @RequestMapping("/record_lijstRecords")
    public String lijstrecords(Model model) {
        ArrayList<Record> records = recordRepository.getAlleRecords();
        model.addAttribute("records", records);
        return "record_lijstRecords";
    }
    @RequestMapping("/record_quiz")
    public String recordQuiz(){
        return "record_quiz";
    }

    @RequestMapping("/record_quizForm")
    public String recordQuizForm(Model model, HttpServletRequest request) {
        naam = request.getParameter("naam");
        model.addAttribute("naam", naam);
        aantalJuist = 0;
        if (request.getParameter("raadJaarKnop") != null) {
            Record record = recordRepository.getRecord(15);
            jaar = record.getDatum().toLocalDate().getYear();
            model.addAttribute("record", record);
            return "record_raadJaar";
        }
        else if(request.getParameter("randomRecordKnop") != null){
            Random random = new Random();
            int willekeurig = random.nextInt(20);
            Record record = recordRepository.getRecord(willekeurig);
            jaar = record.getDatum().toLocalDate().getYear();
            model.addAttribute("record", record);
        }else if (request.getParameter("record_raadAtleetKnop") != null){
            Random random = new Random();
            int willekeurig = random.nextInt(20);
            Record record = recordRepository.getRecord(willekeurig);
            jaar = record.getDatum().toLocalDate().getYear();
            model.addAttribute("record", record);
            randomGetal = willekeurig;
            ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
            model.addAttribute("atleten", atleten);

            return"record_raadAtleet";
        }else if (request.getParameter("highscore") != null){
            ArrayList<Highscore> highscores = highscoreRepository.getAlleHighscores();
            model.addAttribute("highscores", highscores);

            return "record_highscores";
        }
        return "record_raadJaar";
    }

    @Autowired AtleetRepository atleetRepository;

    @RequestMapping("/record_raadAtleet")
    public String recordRaadAtleet(){
        return "record_raadAtleet";
    }
    @RequestMapping("/record_quiz_atleetForm")
    public String recordQuizAtleetForm(Model model, HttpServletRequest request){
        int recordAtleetIndex = Integer.parseInt(request.getParameter("recordAtleetIndex"));
        Record record = recordRepository.getRecord(randomGetal);
        int atleetidindex = record.getAtleetid() -1;
        if ( recordAtleetIndex == atleetidindex ){
            aantalJuist++;
            model.addAttribute("antwoordbericht", "Je antwoord was juist!"  + "\r\n" +  " Juiste aantal antwoorden: " + aantalJuist);
        }else {
            ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
            model.addAttribute("antwoordbericht", "Je antwoord was fout, Het juist antwoord was:  " + atleten.get(atleetidindex).getNaam() +". Juiste aantal antwoorden: " + aantalJuist);

        }

        return "record_antwoord";

    }


    @RequestMapping("/antwoord")
    public String antwoord(Model model, HttpServletRequest request) {
        String jaartal = request.getParameter("antwoord");
        int geradenjaar = Integer.parseInt(jaartal);
        if (geradenjaar == jaar) {
            aantalJuist++;
            model.addAttribute("antwoordbericht", "Je antwoord was juist!"  + "\r\n" +  " Juiste aantal antwoorden: " + aantalJuist);
        } else {
            model.addAttribute("antwoordbericht", "Het antwoord was fout!");
        }
        model.addAttribute("jaar", jaar);
        return "record_antwoord";
    }
    @RequestMapping("/verderSpelenForm")
    public String verderSpelen(Model model, HttpServletRequest request){
        if(request.getParameter("record_nietVerderSpelenKnop") != null){
            return "index";

        }else if(request.getParameter("record_verderSpelenKnop") != null){
            model.addAttribute("naam", naam);
            Random random = new Random();
            int willekeurig = random.nextInt(276);
            Record record = recordRepository.getRecord(willekeurig);
            jaar = record.getDatum().toLocalDate().getYear();
            model.addAttribute("record", record);
            randomGetal = willekeurig;
            ArrayList<Atleet> atleten = atleetRepository.getAlleAtleten();
            model.addAttribute("atleten", atleten);

            return "record_raadAtleet";
        }
        return "index";
    }

    @RequestMapping ("/scoreOpslaan")
    String scoreOpslaan(Model model, HttpServletRequest request){

        if(request.getParameter("scoreOpslaanJa") != null) {
            Highscore highscore = highscoreRepository.scoreOplsaan(naam, aantalJuist);
            model.addAttribute("highscore", highscore);
        }

        return "index";
    }

    @RequestMapping("/record_highscore")
    public String highscore(Model model) {
        ArrayList<Highscore> highscores = highscoreRepository.getAlleHighscores();
        model.addAttribute("highscores", highscores);

        return "record_highscores";
    }


}

