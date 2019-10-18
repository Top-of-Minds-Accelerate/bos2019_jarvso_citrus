/*
 * Copyright 2006-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.accelerate.citrus;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestDesigner;
import com.consol.citrus.http.client.HttpClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.testng.annotations.Test;

/**
 * @author Lenny
 */
@Test
public class CurrencyConverterTests_IT extends TestNGCitrusTestDesigner {

    /** Test Http REST client */
    @Autowired
    private HttpClient currencyAPI;

    @CitrusTest
    public void getRatesFromToday() {
    	
    	// ******************************************
    	// Latest rates
    	// ******************************************
    	http().client(currencyAPI)
            .send()
            .get()
            .path("latest")
            ;
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(currencyAPI)
            .receive()
            .response(HttpStatus.OK)
            ;
 		
    }

    @CitrusTest
    public void getRatesFrom20190101() {
    	
    	// ******************************************
    	// Rates from a certain date
    	// ******************************************
    	
    	http().client(currencyAPI)
            .send()
            .get()
            .path("2019-01-01")
            ;
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(currencyAPI)
            .receive()
            .response(HttpStatus.OK)
            ;
 		
    }

    @CitrusTest
    public void getRatesFrom20190101Selected() {
    	
    	// ******************************************
    	// Selected rates USD,GBP from a specific date
    	// ******************************************
    	
    	http().client(currencyAPI)
            .send()
            .get()
            .path("2019-01-01")
            .queryParam("symbols","USD,GBP")
            ;
                
        // Wait for response... Make sure to validate the status code (http -2??) 
    	http().client(currencyAPI)
            .receive()
            .response(HttpStatus.OK)
            ;
 		
    }
    


}
