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
/*
        scenario
                .http()
                .receive()
                .post()
                .payload("<Lenny></Lenny>");
*/

        scenario
                .http()
                .send()
                .response(HttpStatus.OK)
                .payload("{\n" +
                        "\t\"rates\": {\n" +
                        "\t\t\"USD\": 1.1025,\n" +
                        "\t\t\"GBP\": 0.8656\n" +
                        "\t},\n" +
                        "\t\"base\": \"EUR\",\n" +
                        "\t\"date\": \"2019-10-16\"\n" +
                        "}");
    }
}
