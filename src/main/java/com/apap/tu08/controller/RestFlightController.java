package com.apap.tu08.controller;

import com.apap.tu08.model.FlightModel;
import com.apap.tu08.model.PilotModel;
import com.apap.tu08.service.FlightService;
import com.apap.tu08.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * FlightController
 */
@RestController
public class RestFlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private PilotService pilotService;

//    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
//    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
//        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber).get();
//        pilot.setListFlight(new ArrayList<FlightModel>(){
//            private ArrayList<FlightModel> init(){
//                this.add(new FlightModel());
//                return this;
//            }
//        }.init());
//
//        model.addAttribute("pilot", pilot);
//        return "add-flight";
//    }

    @PostMapping(value = "/flight/add")
    private FlightModel addRow(@RequestBody @Validated FlightModel flightModel) {
        System.out.println(flightModel.getPilot());
        Optional<PilotModel> pilot = pilotService.getPilotDetailById(flightModel.getPilot().getId());
        flightModel.setPilot(pilot.get());
        flightService.addFlight(flightModel);
        return flightModel;
    }

    @GetMapping(value = "/flight/{flightNumber}")
    private FlightModel getRow(@PathVariable(name = "flightNumber") String id) {

        return flightService.getFlightDetailByFlightNumber(id).get();
    }

    @GetMapping(value = "/flight")
    private List<FlightModel> getAllFlights() {

        return flightService.getAll();
    }

//    @RequestMapping(value="/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"removeRow"})
//    public String removeRow(@ModelAttribute PilotModel pilot, Model model, HttpServletRequest req) {
//        Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
//        pilot.getListFlight().remove(rowId.intValue());
//
//        model.addAttribute("pilot", pilot);
//        return "add-flight";
//    }
//
//    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST, params={"save"})
//    private String addFlightSubmit(@ModelAttribute PilotModel pilot) {
//        PilotModel archive = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber()).get();
//        for (FlightModel flight : pilot.getListFlight()) {
//            if (flight != null) {
//                flight.setPilot(archive);
//                flightService.addFlight(flight);
//            }
//        }
//        return "add";
//    }
//
//
//    @RequestMapping(value = "/flight/view", method = RequestMethod.GET)
//    private @ResponseBody FlightModel view(@RequestParam(value = "flightNumber") String flightNumber, Model model) {
//        FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber).get();
//        return archive;
//    }
//
//    @RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
//    private String delete(@ModelAttribute PilotModel pilot, Model model) {
//        for (FlightModel flight : pilot.getListFlight()) {
//            flightService.deleteByFlightNumber(flight.getFlightNumber());
//        }
//        return "delete";
//    }
//
//    @RequestMapping(value = "/flight/update", method = RequestMethod.GET)
//    private String update(@RequestParam(value = "flightNumber") String flightNumber, Model model) {
//        FlightModel archive = flightService.getFlightDetailByFlightNumber(flightNumber).get();
//        model.addAttribute("flight", archive);
//        return "update-flight";
//    }
//
//    @RequestMapping(value = "/flight/update", method = RequestMethod.POST)
//    private @ResponseBody FlightModel updateFlightSubmit(@ModelAttribute FlightModel flight, Model model) {
//        flightService.addFlight(flight);
//        return flight;
//    }
}