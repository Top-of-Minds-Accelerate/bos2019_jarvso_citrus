package com.consol.citrus.simulator.scenario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Eric Eneroth
 */

@Scenario("CurrencyConverter")
//@RequestMapping(value = "/jarvso/currencyconverter", method = RequestMethod.POST)
@RequestMapping(value = "/services/rest/simulator/currencyconverter", method = RequestMethod.POST)

public class CurrencyConverterScenario extends AbstractSimulatorScenario {

    @Override
    public void run(ScenarioDesigner scenario) {
        scenario
                .http()
                .receive()
                .post()
                .payload("<Lenny></Lenny>");

        scenario
                .http()
                .send()
                .response(HttpStatus.OK)
                .payload("<Lucky></Lucky>");
    }
}
